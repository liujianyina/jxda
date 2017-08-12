package com.yznu.jxda.service;

import com.yznu.jxda.domain.User;
import com.yznu.jxda.repository.UserRepository;
import com.yznu.jxda.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 刘剑银 on 2017/8/9.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 加密前的密码字符串
     * @return
     */
    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, Utils.getMD5String(password));
    }


}
