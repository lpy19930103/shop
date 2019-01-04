package com.shop.sso.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.common.pojo.EasyResult;
import com.shop.pojo.TbUser;
import com.shop.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @RequestMapping("check/{param}/{type}")
    @ResponseBody
    public String userCheck(HttpServletRequest httpServletrequest, @PathVariable(value = "param") String param, @PathVariable(value = "type") Integer type) {
        boolean userCheck = userService.userCheck(param, type);
        String callback = httpServletrequest.getParameter("callback");
        String result = ("{result:" + userCheck + "}");
        System.out.println(userCheck);
        if (StringUtils.isNotBlank(callback)) {
            return callback + "(" + result + ")";
        }
        return result;

    }

    @RequestMapping("{token}")
    @ResponseBody
    public EasyResult queryUserByToken(@PathVariable(value = "token") String token) {
        TbUser tbUser = userService.queryUserByToken(token);
        EasyResult<TbUser> easyResult = new EasyResult<>();
        if (tbUser == null) {
            easyResult.setStatus(200);
            easyResult.setMsg("未查询到用户");
        } else {
            easyResult.setStatus(0);
            easyResult.setDTO(tbUser);
        }

        return easyResult;
    }

    @RequestMapping("register")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:82")
    public EasyResult register(TbUser user,  HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,token");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");

        TbUser register = userService.register(user);
        EasyResult<TbUser> easyResult = new EasyResult<>();
        if (register != null) {
            easyResult.setDTO(register);
            easyResult.setStatus(200);
            easyResult.setMsg("注册成功");
        } else {
            easyResult.setStatus(0);
            easyResult.setMsg("注册失败");
        }
        return easyResult;
    }

}
