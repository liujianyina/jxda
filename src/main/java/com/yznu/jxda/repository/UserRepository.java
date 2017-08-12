package com.yznu.jxda.repository;

import com.yznu.jxda.domain.User;
import org.springframework.stereotype.Repository;

/**
 * Created by 刘剑银 on 2017/8/9.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

@Repository
public interface UserRepository extends BaseRepository<User> {

    User findByUsernameAndPassword(String username,String password);

}
