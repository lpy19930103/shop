package com.shop.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "tb_item_cat")
public class TbItemCat implements Serializable {

    private static final long serialVersionUID = -2163749175232957219L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")// 注解声明该表的字段名
    private Long id;
    @Column(name = "parent_id")
    private Long parentId;
    @Column(name = "name")
    private String name;
    @Column(name = "type_id")
    private Integer typeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}