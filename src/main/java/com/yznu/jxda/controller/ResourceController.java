package com.yznu.jxda.controller;

import com.yznu.jxda.domain.Resource;
import com.yznu.jxda.domain.User;
import com.yznu.jxda.handle.RequestException;
import com.yznu.jxda.service.ResourceService;
import com.yznu.jxda.service.RoleResourceService;
import com.yznu.jxda.utils.Constant;
import com.yznu.jxda.utils.Convert;
import com.yznu.jxda.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * Created by 刘剑银 on 2017/8/9.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    @Value("${upload_path}")
    private String uploadPath;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RoleResourceService roleResourceService;

    /**
     * 个人文件列表
     *
     * @return
     */
    @GetMapping("/resource_list")
    public ModelAndView resourceList(
            @RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = Constant.DEFAULT_PAGE_SIZE) int pageSize) {

        if (pageIndex < 1) {  //不能出现第0页
            return new ModelAndView("redirect:/resource/resource_list?pageIndex=1&pageSize=" + pageSize);
        }

        //每一页显示的范围
        if (pageSize > 10 || pageSize < 5) {
            return new ModelAndView("redirect:/resource/resource_list?pageIndex=" + pageIndex + "&pageSize=10");
        }

        Page<Resource> resourcePage = resourceService.resourcePageDefault(pageIndex - 1, pageSize, (String) getSession().getAttribute("username"), 1);

        Map<String, Object> map = new HashMap<>();
        map.put("pageIndex", pageIndex);  //当前页的页数
        map.put("totalPages", resourcePage.getTotalPages());  //总页数
        map.put("pageSize", pageSize);  //每一页显示的记录数
        List<Resource> resources = resourcePage.getContent();
        if (resources.size() == 0) {
            return new ModelAndView("redirect:/resource/resource_list?pageIndex=" + (pageIndex - 1) + "&pageSize=10");
        }
        map.put("resources", resources);//当前页面的数据
        return new ModelAndView("/resource/resource_list", map);
    }

    /**
     * 处理文件上传
     * <p>
     * 文件存储位置：${upload_path}/年份/当前月份/当前日期
     *
     * @return
     */
    @PostMapping("/upload")
    public String upload(MultipartFile file, Long[] roleSids) {

        /**
         * 获取当前时间
         */
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);

        String dirPath = uploadPath + "/" + year + "/" + month + "/" + day;

        File saveDir = new File(dirPath);

        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        /**
         * 如果文件为空
         */
        if (file.isEmpty()) {
            return "redirect:/resource/resource_list";
        } else {
            //文件不为空
            String fileName = Constant.PREFIX + System.nanoTime() + "_" + file.getOriginalFilename();
            BufferedOutputStream bos;

            try {
                bos = new BufferedOutputStream(new FileOutputStream(new File(saveDir, fileName)));
                bos.write(file.getBytes());
                bos.flush();
                bos.close();

                String taken = Utils.getNextToken();

                String type = fileName.substring(fileName.lastIndexOf('.') + 1);

                /**
                 * 开启转换线程
                 * 该方式的优点为：上传的时候不用等待转换结果可直接跳转至上传成功界面
                 * 缺点为：如果上传完毕后立即在线预览，有可能在这过程中，转换并为完成，产生404错误
                 *
                 * 如果不开启多线程转换，在上传的同时，需要等待转换成功后，页面才可以跳转
                 */
                new Thread(() -> {
                }) {
                    @Override
                    public void run() {
                        if (type.equals("docx") || type.equals("DOCX")) {
                            Convert.word07ToHtml(dirPath, fileName, taken + ".ftl");
                        }
                    }
                }.start();

                //如果不开启多线程转换
                /*Convert.word07ToHtml(dirPath, fileName, taken + ".ftl");*/

                Resource resource = new Resource();
                resource.setCname(file.getOriginalFilename());
                resource.setRname(fileName);
                resource.setType(type);
                resource.setSize(file.getSize() / 1024);
                resource.setUsername((String) getSession().getAttribute("username"));
                resource.setTempName(taken);
                Long resourcesid = resourceService.save(resource).getSid();

                roleResourceService.save(roleSids, resourcesid);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return "redirect:/resource/resource_list";
    }


    /**
     * 预览
     *
     * @param taken 暴露在外的taken值
     * @return
     */
    @GetMapping("/preview")
    public String preview(@RequestParam(defaultValue = "") String taken) {

        /**
         * 如果参数的长度不为26，则说明参数非法
         */
        if (taken.length() != 26) {
            throw new RequestException();
        }

        /**
         * 判断当前用户是否有权访问此文件
         */
        User user = (User) getSession().getAttribute(Constant.SessionKey.CURRENT_USER);
        if (isVisited(taken, user)) {
            return "/temp/" + taken;
        } else {
            throw new RequestException();
        }
    }

    /**
     * 删除资源文件
     *
     * @param taken
     * @return
     */
    @GetMapping("/delete")
    public String delete(@RequestParam(defaultValue = "") String taken) {
        /**
         * 如果参数的长度不为26，则说明参数非法
         */
        if (taken.length() != 26) {
            throw new RequestException();
        }

        /**
         * 判断当前用户是否有权删除此文件
         */
        User user = (User) getSession().getAttribute(Constant.SessionKey.CURRENT_USER);
        if (this.isOperation(taken, user)) {  //该用户为当前用户上传，有权限删除
            resourceService.setStatusNotEnable(taken);
            return "redirect:/resource/resource_list";
        } else {
            throw new RequestException();
        }
    }

    @ResponseBody
    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> download(@RequestParam(defaultValue = "") String taken) throws Exception {
        /**
         * 如果参数的长度不为26，则说明参数非法
         */
        if (taken.length() != 26) {
            throw new RequestException();
        }

        /**
         * 判断当前用户是否有权访问此文件
         */
        User user = (User) getSession().getAttribute(Constant.SessionKey.CURRENT_USER);
        if (isVisited(taken, user)) {
            Resource resource = resourceService.findByTempName(taken);
            Date date = resource.getCreateTime();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            int mon = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);


            FileSystemResource file = new FileSystemResource(uploadPath + "/" + year + "/" + mon + "/" + day + "/" + resource.getRname());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", new String(resource.getCname().getBytes("UTF-8"), "ISO8859-1"));

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(file.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(file.getInputStream()));
        } else {
            throw new RequestException();
        }
    }

    /**
     * 用户是否可以访问
     *
     * @param taken 暴露的资源taken
     * @param user  用户
     * @return
     */
    public boolean isVisited(String taken, User user) {
        /**
         * 如果该文件为该用户上传
         */
        if (this.isOperation(taken, user)) {
            return true;
        }

        /**
         * 如果该用户被授权访问
         */
        List<Long> roleSids = user.getRoleSids();
        Resource resource = resourceService.findByTempName(taken);

        if (Utils.isNull(resource) || resource.getStatus() != 1) {
            return false;
        }

        Long resourceSid = resource.getSid();


        Set<Long> resourceSids = roleResourceService.findCanVisitByRoleSids(roleSids);

        if (resourceSids.contains(resourceSid)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 用户是否可以对当前资源进行增删改查的操作
     *
     * @return
     */
    public boolean isOperation(String taken, User user) {
        return resourceService.isCurrentUsers(taken, user.getUsername());
    }

}
