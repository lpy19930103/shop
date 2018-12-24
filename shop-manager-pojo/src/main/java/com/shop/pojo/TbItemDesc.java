package com.shop.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_item_desc")
public class TbItemDesc extends BasePojo implements Serializable {
    private static final long serialVersionUID = -6793291918751639592L;
    @Id
    @Column(name = "item_id")
    private Long itemId;
    @Column(name = "item_desc")
    private String itemDesc;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }
}