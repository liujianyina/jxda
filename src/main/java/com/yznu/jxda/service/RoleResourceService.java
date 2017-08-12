package com.yznu.jxda.service;

import com.yznu.jxda.domain.Resource;
import com.yznu.jxda.domain.RoleResource;
import com.yznu.jxda.repository.RoleResourceRepository;
import com.yznu.jxda.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 刘剑银 on 2017/8/11.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */
@Service
public class RoleResourceService {

    @Autowired
    private RoleResourceRepository roleResourceRepository;

    /**
     * 保存角色-文件关联
     *
     * @return
     */
    @Transactional
    public RoleResource save(Long roleSid, Long resourceSid) {
        RoleResource roleResource = new RoleResource();
        roleResource.setRoleSid(roleSid);
        roleResource.setResourceSid(resourceSid);
        return roleResourceRepository.save(roleResource);
    }

    /**
     * 保存角色-文件关联
     *
     * @return
     */
    @Transactional
    public List<RoleResource> save(Long[] roleSids, Long resourceSid) {
        List<RoleResource> list = new ArrayList<>();

        if (!Utils.isEmpty(roleSids)) {
            for (Long roleSid : roleSids) {
                list.add(this.save(roleSid, resourceSid));
            }
        }

        return list;
    }

    /**
     * 按照角色sid查询可访问的资源sid
     *
     * @param roleSids
     * @return
     */
    public Set<Long> findCanVisitByRoleSids(List<Long> roleSids) {

        Set<Long> resourceSids = new HashSet<>();

        List<RoleResource> resources = roleResourceRepository.findAllByRoleSidIn(roleSids);
        if (!Utils.isNull(resources) && !resources.isEmpty()) {
            for (RoleResource roleResource : resources) {
                resourceSids.add(roleResource.getResourceSid());
            }
        }

        return resourceSids;
    }


}
