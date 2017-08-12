package com.yznu.jxda.repository;

import com.yznu.jxda.domain.Resource;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 刘剑银 on 2017/8/9.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */
@Repository
public interface ResourceRepository extends BaseRepository<Resource> {

    /**
     * 查找当前用户上传的文件
     *
     * @param username
     * @return
     */
    List<Resource> findByUsername(String username);

    /**
     * 按照状态查询用户资源
     *
     * @param username
     * @param status
     * @return
     */
    List<Resource> findAllByUsernameAndStatus(String username, int status);

    /**
     * 判断该文件是否为该用户所有
     *
     * @param tempName
     * @param username
     * @return
     */
    Resource findByTempNameAndUsername(String tempName, String username);

    /**
     * 查询sid在sids里面的所有资源
     *
     * @param sids
     * @return
     */
    List<Resource> findAllBySidIn(List<Long> sids);

    /**
     * 按照tempName查询
     *
     * @param tempName
     * @return
     */
    Resource findByTempName(String tempName);

    @Modifying
    @Query("UPDATE Resource r SET r.status = ?1 WHERE r.tempName = ?2")
    void setStatus(int status, String tempName);

}
