package com.yznu.jxda.domain;

import com.yznu.jxda.utils.Constant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 刘剑银 on 2017/8/9.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

/**
 * 角色与资源关联
 */

@Entity
@Table(name = Constant.PREFIX + "role_resource")
public class RoleResource extends BaseDomain {
    private static final long serialVersionUID = -7688685458576779338L;

    @Column(nullable = false)
    private Long roleSid;

    @Column(nullable = false)
    private Long resourceSid;

    public RoleResource() {
    }

    public Long getRoleSid() {
        return roleSid;
    }

    public void setRoleSid(Long roleSid) {
        this.roleSid = roleSid;
    }

    public Long getResourceSid() {
        return resourceSid;
    }

    public void setResourceSid(Long resourceSid) {
        this.resourceSid = resourceSid;
    }
}
