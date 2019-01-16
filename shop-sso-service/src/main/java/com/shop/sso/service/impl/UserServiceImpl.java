package com.shop.sso.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.pojo.TbUser;
import com.shop.sso.service.UserService;
import com.shop.sso.service.redis.RedisPool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends BaseServiceImpl<TbUser> implements UserService {
    @Value("${SHOP_SSO_KEY}")
    private String SHOP_SSO_KEY;

    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    private RedisPool poolJedisClient;

    @Override
    public boolean userCheck(String param, Integer type) {
        TbUser tbUser = new TbUser();
        switch (type) {
            case 1:
                tbUser.setUsername(param);
                break;
            case 2:
                tbUser.setPhone(param);
                break;
            case 3:
                tbUser.setEmail(param);
                break;
        }
        Integer integer = queryCountByWhere(tbUser);
        if (integer > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public TbUser queryUserByToken(String token) {
        String json = poolJedisClient.get(SHOP_SSO_KEY + token);
        try {
            if (StringUtils.isNotEmpty(json)) {
                poolJedisClient.expire(SHOP_SSO_KEY + token, 60 * 30);
                return MAPPER.readValue(json, TbUser.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public TbUser register(TbUser tbUser) {
        saveSelective(tbUser);
        return tbUser;
    }

    @Override
    public TbUser login(TbUser tbUser) {
        return queryOne(tbUser);
    }
}
