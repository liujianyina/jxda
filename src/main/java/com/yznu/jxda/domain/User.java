package com.yznu.jxda.domain;

import com.yznu.jxda.utils.Constant;
import com.yznu.jxda.utils.Utils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 刘剑银 on 2017/8/9.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

/**
 * 用户
 */

@Entity
@Table(name = Constant.PREFIX + "user")
public class User extends BaseDomain {
    private static final long serialVersionUID = -5608131091407856106L;

    /**
     * 用户名
     */
    @Column(length = 45, nullable = false, updatable = false,unique = true)
    private String username;

    /**
     * 密码
     */
    @Column(length = 32, nullable = false)
    private String password;

    /**
     * 部门
     */
    @Column(length = 45)
    private String department;

    /**
     * 职位
     */
    @Column(length = 45)
    private String position;

    /**
     * 电话号码
     */
    @Column(length = 11)
    private String phoneNumber;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = Constant.PREFIX + "user_role",
            joinColumns = {@JoinColumn(name = "user_sid")},
            inverseJoinColumns = {@JoinColumn(name = "role_sid")}
    )
    private List<Role> roleList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    /**
     * 取得用户关联的所有角色sid
     *
     * @return
     */
    public List<Long> getRoleSids() {
        List<Long> sids = new ArrayList<>();

        if (!Utils.isNull(roleList) && !roleList.isEmpty()) {
            for (Role role : roleList) {
                sids.add(role.getSid());
            }
        }
        return sids;
    }
}
