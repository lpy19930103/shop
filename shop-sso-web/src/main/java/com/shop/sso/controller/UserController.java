package com.shop.sso.controller;

import com.shop.common.pojo.EasyResult;
import com.shop.pojo.TbUser;
import com.shop.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("check/{param}/{type}")
    @ResponseBody
    public EasyResult userCheck(@PathVariable(value = "param") String param, @PathVariable(value = "type") Integer type) {
        boolean userCheck = userService.userCheck(param, type);
        EasyResult<Boolean> booleanEasyResult = new EasyResult<>();
        if (userCheck) {
            booleanEasyResult.setMsg("参数可以使用");
        } else {
            booleanEasyResult.setMsg("参数不可以使用");
        }
        return booleanEasyResult;

    }

    @RequestMapping("{token}")
    @ResponseBody
    public EasyResult queryUserByToken(@PathVariable(value = "token") String token) {
        TbUser tbUser = userService.queryUserByToken(token);
        EasyResult<TbUser> easyResult = new EasyResult<>();
        if (tbUser == null) {
            easyResult.setStatus(1);
            easyResult.setMsg("未查询到用户");
        } else {
            easyResult.setStatus(0);
            easyResult.setDTO(tbUser);
        }

        return easyResult;
    }

}
