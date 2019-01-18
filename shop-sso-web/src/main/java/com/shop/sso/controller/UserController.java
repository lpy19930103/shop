package com.shop.sso.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.common.pojo.EasyResult;
import com.shop.common.utils.CookieUtils;
import com.shop.pojo.TbUser;
import com.shop.sso.redis.RedisPool;
import com.shop.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
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

    @Autowired
    private RedisPool poolJedisClient;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Value(value = "LOGIN_COOKIE")
    private String LOGIN_COOKIE;

    @Value(value = "SHOP_SSO_KEY")
    private String SHOP_SSO_KEY;
    @Value(value = "USER_TOKEN")
    private String USER_TOKEN;

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
//    @CrossOrigin(origins = "http://localhost:82")
    public EasyResult register(TbUser user) {
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


    @RequestMapping("login")
    @ResponseBody
//    @CrossOrigin(origins = "http://localhost:82")
    public EasyResult login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TbUser user) {
        EasyResult<TbUser> easyResult = new EasyResult<>();
        TbUser result = userService.login(user);
        if (result != null) {
            try {
                easyResult.setDTO(result);
                easyResult.setStatus(200);
                easyResult.setMsg("登录成功");
                // 生成唯一数ticket,可是使用redis的唯一数+用户id
                String ticket = "" + poolJedisClient.incr(this.LOGIN_COOKIE) + result.getId();
                // 把ticket和用户数据放到redis中,模拟session，原来的session有效时间是半小时
                poolJedisClient.set(SHOP_SSO_KEY + ticket, MAPPER.writeValueAsString(result), 24 * 60 * 60);
                System.out.println("ticket = " + ticket);
                CookieUtils.setCookie(httpServletRequest, httpServletResponse, USER_TOKEN, ticket, 60 * 60 * 24, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            easyResult.setStatus(0);
            easyResult.setMsg("登录失败");
        }
        return easyResult;

    }

}
