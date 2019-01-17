package com.shop.pojo;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_order_item")
public class TbOrderItem extends BasePojo implements Serializable {
    @Column
    private String id;
    @Column(name = "item_id")
    private String itemId;
    @Column(name = "order_id")
    private String orderId;
    @Column
    private Integer num;
    @Column
    private String title;
    @Column
    private Long price;
    @Column(name = "total_fee")
    private Long totalFee;
    @Column(name = "pic_path")
    private String picPath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }

    @Override
    public String toString() {
        return "TbOrderItem{" +
                "id='" + id + '\'' +
                ", itemId='" + itemId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", num=" + num +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", totalFee=" + totalFee +
                ", picPath='" + picPath + '\'' +
                '}';
    }
}