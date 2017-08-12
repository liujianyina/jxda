package com.yznu.jxda.domain;

/**
 * Created by 刘剑银 on 2017/8/9.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

import com.yznu.jxda.utils.Constant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色
 */
@Entity
@Table(name = Constant.PREFIX + "role")
public class Role extends BaseDomain {
    private static final long serialVersionUID = 8752391533848523107L;
    /**
     * 角色名
     */
    @Column(length = 45, nullable = false)
    private String cname;

    /**
     * 备注
     */
    @Column(length = 45)
    private String remarks;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
