package com.yznu.jxda.repository;

import com.yznu.jxda.domain.RoleResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 刘剑银 on 2017/8/11.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

@Repository
public interface RoleResourceRepository extends BaseRepository<RoleResource> {

    List<RoleResource> findAllByRoleSidIn(List<Long> roleSids);
}
