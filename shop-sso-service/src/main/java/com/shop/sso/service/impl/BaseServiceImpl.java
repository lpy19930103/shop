package com.shop.sso.service.impl;

import com.github.pagehelper.PageHelper;
import com.shop.common.base.BaseService;
import com.shop.pojo.BasePojo;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class BaseServiceImpl<T extends BasePojo> implements BaseService<T> {
    @Autowired
    private Mapper<T> mapper;
    private Class<T> clazz;

    public BaseServiceImpl() {
        // 获取父类的type
        Type genericSuperclass = getClass().getGenericSuperclass();
        // 强转为ParameterizedType，可以使用获取泛型类型的方法
        ParameterizedType pType = (ParameterizedType) genericSuperclass;
        clazz = (Class<T>) pType.getActualTypeArguments()[0];
    }

    @Override
    public T queryById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> queryAll() {
        return mapper.select(null);
    }

    @Override
    public Integer queryCountByWhere(T t) {
        return mapper.selectCount(t);
    }

    @Override
    public List<T> queryListByWhere(T t) {
        return mapper.select(t);
    }

    @Override
    public List<T> queryByPage(Integer page, Integer rows) {
        return queryByPage(null, page, rows);
    }

    @Override
    public List<T> queryByPage(T t, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        return mapper.select(t);
    }

    @Override
    public T queryOne(T t) {
        return mapper.selectOne(t);
    }

    @Override
    public void save(T t) {
        if (t.getCreated() == null) {
            t.setCreated(new Date());
            t.setUpdated(t.getCreated());
        } else if (t.getUpdated() == null) {
            t.setUpdated(t.getCreated());
        }
        mapper.insert(t);
    }

    @Override
    public void saveSelective(T t) {
        if (t.getCreated() == null) {
            t.setCreated(new Date());
            t.setUpdated(t.getCreated());
        } else if (t.getUpdated() == null) {
            t.setUpdated(t.getCreated());
        }
        mapper.insertSelective(t);
    }

    @Override
    public void updateById(T t) {
        t.setUpdated(new Date());
        mapper.updateByPrimaryKey(t);
    }

    @Override
    public void updateByIdSelective(T t) {
        t.setUpdated(new Date());
        mapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public void deleteById(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        // 声明条件
        Example example = new Example(clazz);
        example.createCriteria().andIn("id", ids);
        mapper.deleteByExample(example);
    }
}
