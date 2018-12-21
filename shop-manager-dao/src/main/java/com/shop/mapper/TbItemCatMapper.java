package com.shop.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.shop.pojo.TbItemCat;

public interface TbItemCatMapper {
    int insert(@Param("tbItemCat") TbItemCat tbItemCat);

    int insertSelective(@Param("tbItemCat") TbItemCat tbItemCat);

    int insertList(@Param("tbItemCats") List<TbItemCat> tbItemCats);

    int updateByPrimaryKeySelective(@Param("tbItemCat") TbItemCat tbItemCat);
}
