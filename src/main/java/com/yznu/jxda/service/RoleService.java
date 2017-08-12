package com.yznu.jxda.service;

import com.yznu.jxda.domain.Role;
import com.yznu.jxda.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by 刘剑银 on 2017/8/11.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> findByStatus(int status) {
        return roleRepository.findAllByStatus(status);
    }

    public List<Role> findEnable() {
        return roleRepository.findAllByStatus(1);
    }

}
