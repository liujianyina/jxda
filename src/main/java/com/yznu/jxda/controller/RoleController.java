package com.yznu.jxda.controller;

import com.yznu.jxda.domain.AjaxResult;
import com.yznu.jxda.service.RoleService;
import com.yznu.jxda.utils.AjaxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 刘剑银 on 2017/8/11.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @GetMapping("/get_all_role")
    public AjaxResult getAllRole() {
        return AjaxUtils.succeed(roleService.findEnable());
    }

}
