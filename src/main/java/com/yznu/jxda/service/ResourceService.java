package com.yznu.jxda.service;

import com.yznu.jxda.domain.Resource;
import com.yznu.jxda.repository.ResourceRepository;
import com.yznu.jxda.utils.Convert;
import com.yznu.jxda.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by 刘剑银 on 2017/8/9.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    /**
     * 保存文件信息
     *
     * @param resource
     * @return
     */
    @Transactional
    public Resource save(Resource resource) {
        return resourceRepository.save(resource);
    }

    /**
     * 分页查询
     *
     * @param page     页数  从0开始
     * @param size     每页显示的记录数
     * @param username 查询条件：用户名
     * @param type     排序方式
     * @param keyword  排序关键字
     * @param status   状态值
     * @return
     */
    public Page<Resource> resourcePage(int page, int size, String username, Sort.Direction type, String keyword, int status) {

        Sort.Order order = new Sort.Order(type, keyword);
        Sort sort = new Sort(order);

        Pageable pageable = new PageRequest(page, size, sort);

        /**
         * 查询条件
         */
        Specification<Resource> specification = (root, criteriaQuery, criteriaBuilder) -> {
            Path path1 = root.get("username");
            Path path2 = root.get("status");
            criteriaQuery.where(criteriaBuilder.equal(path1, username), criteriaBuilder.equal(path2, status));
            /*return criteriaBuilder.equal(path1, username).equal(path2,1);*/
            return null;
        };

        Page<Resource> pageResource = resourceRepository.findAll(specification, pageable);

        return pageResource;
    }

    /**
     * 默认分页查询：每页显示10个数据，按照sid升序排列
     *
     * @param page     页数
     * @param username 用户名
     * @return
     */
    public Page<Resource> resourcePageDefault(int page, int pageSize, String username, int status) {
        return this.resourcePage(page, pageSize, username, Sort.Direction.ASC, "sid", status);
    }

    /**
     * 查询为tempName的资源是否为usernmae所有
     *
     * @param tempName
     * @param username
     * @return
     */
    public boolean isCurrentUsers(String tempName, String username) {
        return !Utils.isNull(resourceRepository.findByTempNameAndUsername(tempName, username));
    }


    List<Resource> findAllBySidIn(List<Long> sids) {
        return resourceRepository.findAllBySidIn(sids);
    }

    public Resource findByTempName(String tempName) {
        return resourceRepository.findByTempName(tempName);
    }


    /**
     * 设置状态
     *
     * @param status 状态值
     */
    @Transactional
    public void setStatus(int status, String taken) {
        resourceRepository.setStatus(status, taken);
    }

    /**
     * 将该状态设为无效
     *
     * @param taken
     */
    @Transactional
    public void setStatusNotEnable(String taken) {
        this.setStatus(0, taken);
    }

    /**
     * 将该状态设为有效
     *
     * @param taken
     */
    @Transactional
    public void setStatusEnable(String taken) {
        this.setStatus(1, taken);
    }
}
