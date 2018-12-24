package com.shop.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "tb_item")
public class TbItem extends BasePojo implements Serializable {

    private static final long serialVersionUID = -88567956885673897L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String seller;
    @Column
    private String image;
    @Column
    private String stock_count;
    @Column
    private String create_time;
    @Column
    private String item_sn;
    @Column
    private int num;
    @Column
    private int goods_id;
    @Column
    private String title;
    @Column
    private String cost_pirce;
    @Column
    private String is_default;
    @Column
    private String spec;
    @Column
    private String update_time;
    @Column
    private double price;
    @Column
    private String market_price;
    @Column
    private String sell_point;
    @Column
    private String cart_thumbnail;
    @Column
    private String category;
    @Column
    private String barcode;
    @Column
    private String brand;
    @Column
    private int category_id;
    @Column
    private String seller_id;
    @Column
    private String status;
    @Transient
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStock_count(String stock_count) {
        this.stock_count = stock_count;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public void setItem_sn(String item_sn) {
        this.item_sn = item_sn;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost_pirce(String cost_pirce) {
        this.cost_pirce = cost_pirce;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSell_point(String sell_point) {
        this.sell_point = sell_point;
    }

    public void setCart_thumbnail(String cart_thumbnail) {
        this.cart_thumbnail = cart_thumbnail;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCategoryId(int categoryId) {
        this.category_id = categoryId;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSeller() {
        return seller;
    }

    public String getImage() {
        return image;
    }

    public String getStock_count() {
        return stock_count;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getItem_sn() {
        return item_sn;
    }

    public int getNum() {
        return num;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public String getTitle() {
        return title;
    }

    public String getCost_pirce() {
        return cost_pirce;
    }

    public String getIs_default() {
        return is_default;
    }

    public String getSpec() {
        return spec;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public double getPrice() {
        return price;
    }

    public String getMarket_price() {
        return market_price;
    }

    public Long getId() {
        return id;
    }

    public String getSell_point() {
        return sell_point;
    }

    public String getCart_thumbnail() {
        return cart_thumbnail;
    }

    public String getCategory() {
        return category;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getBrand() {
        return brand;
    }

    public int getCategoryId() {
        return category_id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "TbItem{" +
                "id=" + id +
                ", seller='" + seller + '\'' +
                ", image='" + image + '\'' +
                ", stock_count='" + stock_count + '\'' +
                ", create_time='" + create_time + '\'' +
                ", item_sn='" + item_sn + '\'' +
                ", num=" + num +
                ", goods_id=" + goods_id +
                ", title='" + title + '\'' +
                ", cost_pirce='" + cost_pirce + '\'' +
                ", is_default='" + is_default + '\'' +
                ", spec='" + spec + '\'' +
                ", update_time='" + update_time + '\'' +
                ", price=" + price +
                ", market_price='" + market_price + '\'' +
                ", sell_point='" + sell_point + '\'' +
                ", cart_thumbnail='" + cart_thumbnail + '\'' +
                ", category='" + category + '\'' +
                ", barcode='" + barcode + '\'' +
                ", brand='" + brand + '\'' +
                ", category_id=" + category_id +
                ", seller_id='" + seller_id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
