package com.shop.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "tb_item")
public class TbItem extends BasePojo implements Serializable {

    private static final long serialVersionUID = -88567956885673897L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String sell_point;
    @Column
    private BigDecimal price;
    @Column
    private Integer stock_count;
    @Column
    private Integer num;
    @Column
    private String barcode;
    @Column
    private String image;
    @Column
    private Long category_id;
    @Column
    private String status;
    @Column
    private Date create_time;
    @Column
    private Date update_time;
    @Column
    private String item_sn;
    @Column
    private BigDecimal cost_pirce;
    @Column
    private BigDecimal market_price;
    @Column
    private String is_default;
    @Column
    private Long goods_id;
    @Column
    private String seller_id;
    @Column
    private String cart_thumbnail;
    @Column
    private String category;
    @Column
    private String brand;
    @Column
    private String spec;
    @Column
    private String seller;
    @Transient
    private String desc;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSell_point() {
        return sell_point;
    }

    public void setSell_point(String sell_point) {
        this.sell_point = sell_point;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock_count() {
        return stock_count;
    }

    public void setStock_count(Integer stock_count) {
        this.stock_count = stock_count;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getItem_sn() {
        return item_sn;
    }

    public void setItem_sn(String item_sn) {
        this.item_sn = item_sn;
    }

    public BigDecimal getCost_pirce() {
        return cost_pirce;
    }

    public void setCost_pirce(BigDecimal cost_pirce) {
        this.cost_pirce = cost_pirce;
    }

    public BigDecimal getMarket_price() {
        return market_price;
    }

    public void setMarket_price(BigDecimal market_price) {
        this.market_price = market_price;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public Long getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Long goods_id) {
        this.goods_id = goods_id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getCart_thumbnail() {
        return cart_thumbnail;
    }

    public void setCart_thumbnail(String cart_thumbnail) {
        this.cart_thumbnail = cart_thumbnail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
