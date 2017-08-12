package com.yznu.jxda.repository;

import com.yznu.jxda.domain.Role;
import org.springframework.stereotype.Repository;

import javax.validation.OverridesAttribute;
import java.util.List;

/**
 * Created by 刘剑银 on 2017/8/11.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

@Repository
public interface RoleRepository extends BaseRepository<Role> {

    /**
     * 按状态查询角色
     *
     * @param status 状态值
     * @return
     */
    List<Role> findAllByStatus(int status);
}
