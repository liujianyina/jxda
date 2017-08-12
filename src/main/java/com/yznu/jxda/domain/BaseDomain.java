package com.yznu.jxda.domain;

import com.yznu.jxda.utils.Utils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by 刘剑银 on 2017/8/9.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseDomain implements Serializable {

    /**
     * 标识sid
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    protected Long sid;

    /**
     * 创建时间
     */
    @Column(nullable = false, updatable = false)
    protected Date createTime;

    /**
     * 更新时间
     */
    @Column(nullable = false)
    protected Date updateTime;

    /**
     * 状态
     */
    @Column(nullable = false, length = 1)
    protected int status = 1;

    public BaseDomain() {
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Date getCreateTime() {
        if (Utils.isNull(createTime)) {
            return new Date();
        } else {
            DateTime date = new DateTime(createTime, DateTimeZone.UTC);
            return date.toDate();
        }
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        DateTime date = new DateTime(updateTime, DateTimeZone.UTC);
        return date.toDate();
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
