package com.yznu.jxda.utils;

import com.yznu.jxda.domain.AjaxResult;

/**
 * Created by 刘剑银 on 2017/8/11.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

public class AjaxUtils {

    /**
     * 请求成功
     *
     * @return
     */
    public static AjaxResult succeed(Object data) {
        return new AjaxResult(AjaxResult.AjaxEnum.SUCCEED, data);
    }

    /**
     * 请求成功
     */
    public static AjaxResult succeed() {
        return succeed(null);
    }

    /**
     * 请求失败
     *
     * @return
     */
    public static AjaxResult failure() {
        return new AjaxResult(AjaxResult.AjaxEnum.FAILURE, null);
    }

}
