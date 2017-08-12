package com.yznu.jxda.domain;

import com.yznu.jxda.utils.Constant;
import org.springframework.stereotype.Controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 刘剑银 on 2017/8/9.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

/**
 * 资源
 */

@Entity
@Table(name = Constant.PREFIX + "resource")
public class Resource extends BaseDomain {
    private static final long serialVersionUID = 5931687780702342558L;

    /**
     * 原文件名
     */
    @Column(length = 45, nullable = false)
    private String cname;

    /**
     * 实际保存在本地的文件名
     */
    @Column(length = 45, nullable = false, updatable = false)
    private String rname;

    /**
     * 文件类别
     */
    @Column(length = 10, nullable = false, updatable = false)
    private String type;


    /**
     * 文件大小 单位：kb
     */
    @Column(length = 10, nullable = false, updatable = false)
    private float size;

    /**
     * 上传的用户
     */
    @Column(nullable = false, updatable = false, length = 45)
    private String username;

    /**
     * 父级sid
     */
    @Column(nullable = false)
    private Long parentSid = -1L;

    /**
     * 中间文件的文件名以及暴露在外的随机taken
     */
    @Column(nullable = false, updatable = false, length = 45)
    private String tempName;


    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getParentSid() {
        return parentSid;
    }

    public void setParentSid(Long parentSid) {
        this.parentSid = parentSid;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sid == null) ? 0 : sid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Resource)) {
            return false;
        }
        Resource other = (Resource) obj;
        if (sid == null) {
            if (other.sid != null) {
                return false;
            }
        } else if (!sid.equals(other.sid)) {
            return false;
        }
        return true;
    }

}
