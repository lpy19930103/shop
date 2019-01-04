package com.shop.sso.service;

import com.shop.common.base.BaseService;
import com.shop.pojo.TbUser;


public interface UserService extends BaseService<TbUser> {
    public boolean userCheck(String param, Integer type);

    public TbUser queryUserByToken(String token);

    public TbUser register(TbUser tbUser);

    public TbUser login(TbUser tbUser);
}
