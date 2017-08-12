package com.yznu.jxda.controller;

import com.yznu.jxda.domain.User;
import com.yznu.jxda.service.UserService;
import com.yznu.jxda.utils.CaptchaUtils;
import com.yznu.jxda.utils.Constant;
import com.yznu.jxda.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by 刘剑银 on 2017/8/9.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

@Controller
@RequestMapping("/")
public class RootController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping({"/", "/index"})
    public String index() {
        if (Utils.isNull(getSession().getAttribute(Constant.SessionKey.CURRENT_USER))) {
            return "redirect:/login";
        } else {
            return "/index";
        }
    }

    @GetMapping(value = "/login")
    public String login() {
        if (Utils.isNull(getSession().getAttribute(Constant.SessionKey.CURRENT_USER))) {
            return "/login";
        } else {
            return "redirect:/index";
        }
    }

    @PostMapping("/login")
    public String login(String username, String password, String captcha, final RedirectAttributes redirectAttributes) {

        /**
         * 用户名或密码有一个为空
         */
        /*if (Utils.isEmpty(username) || Utils.isEmpty(password)) {
            redirectAttributes.addFlashAttribute("ERROR_MESSAGE", "error.login.empty");
            return "redirect:/login";
        }*/

        /**
         * 判断验证码
         */
        String rcaptcha = (String) getSession().getAttribute("captcha");
        if (!rcaptcha.equals(captcha.toUpperCase())) {
            redirectAttributes.addFlashAttribute("ERROR_MESSAGE", "error.login.captcha");
            return "redirect:/login";
        }

        User user = userService.login(username, password);

        /*User user = userService.login("admin", "admin");*/


        if (Utils.isNull(user)) {
            redirectAttributes.addFlashAttribute("ERROR_MESSAGE", "error.login.fail");
            return "redirect:/login";
        } else {
            if (user.getStatus() == 1) {
                try {
                    getSession().invalidate();
                } catch (Exception e) {

                }
                getSession().setAttribute(Constant.SessionKey.CURRENT_USER, user);
                getSession().setAttribute("username", username);

                String referer = getRequest().getHeader("referer");
                if (Utils.isEmpty(referer)) {
                    return "redirect:/index";
                } else {
                    return "redirect:" + referer;
                }
            } else {
                redirectAttributes.addFlashAttribute("ERROR_MESSAGE", "error.login.user.invalid");
                return "redirect:/login";
            }

        }


    }


    /**
     * 生成验证码
     */
    @GetMapping("/captcha")
    /*@ResponseBody*/
    public void captcha(HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字串
        String captcha = CaptchaUtils.generateVerifyCode(4);
        //存入会话session
        HttpSession session = getSession();
        //删除以前的
        session.removeAttribute("captcha");
        session.setAttribute("captcha", captcha.toUpperCase());
        //生成图片
        CaptchaUtils.outputImage(180, 40, response.getOutputStream(), captcha);
    }

    /**
     * 关于我们
     *
     * @return
     */
    @GetMapping("/about_us")
    public String aboutUs() {
        return "/about_us";
    }

    /**
     * 联系我们
     *
     * @return
     */
    @GetMapping("/link_us")
    public String linkUs() {
        return "/link_us";
    }

    /**
     * 注销
     *
     * @return
     */
    @GetMapping("/loginOut")
    public String loginOut() {
        getSession().invalidate();
        return "redirect:/login";
    }


    /*@GetMapping("/test")
    public String test(){
        return "forward:https://www.baidu.com";
    }*/


}
