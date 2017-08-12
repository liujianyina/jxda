package com.yznu.jxda.handle;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by 刘剑银 on 2017/8/12.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "对不起，您还没有权限访问此页面")
public class RequestException extends RuntimeException {
}
