package com.yznu.jxda.domain;

/**
 * Created by 刘剑银 on 2017/8/11.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

/**
 * AJAX请求返回的封装
 */

public class AjaxResult {

    public enum AjaxEnum {

        //成功
        SUCCEED("200", "成功"),
        //失败
        FAILURE("500", "失败");

        private String status;

        private String msg;

        AjaxEnum(String status, String msg) {
            this.status = status;
            this.msg = msg;
        }

        public String getStatus() {
            return status;
        }

        public String getMsg() {
            return msg;
        }
    }


    /**
     * 状态码
     */
    private String status;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据
     */
    private Object data;

    public AjaxResult(AjaxEnum ajaxEnum, Object data) {
        this.status = ajaxEnum.status;
        this.msg = ajaxEnum.msg;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
