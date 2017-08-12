package com.yznu.jxda.utils;

/**
 * Created by 刘剑银 on 2017/8/9.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

/**
 * 全局静态常量
 */

public class Constant {


    public final static String PREFIX = "jxda_";

    /**
     * 默认每页显示记录数
     */
    public final static String DEFAULT_PAGE_SIZE = "10";

    public final class SessionKey {

        /**
         * 当前登录用户
         */
        public static final String CURRENT_USER = "com.yznu.jxda.session.user";

        /**
         * 当前用户对应的角色sid集合
         */
        public static final String CURRENT_ROLE_SIDS = "com.yznu.jxda.session.user.roles";

    }

}
