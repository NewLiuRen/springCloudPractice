package com.demo.pojo;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 id bigint(20) NOT NULLsku id
 spu_id bigint(20) NOT NULLspu id
 title varchar(256) NOT NULL商品标题
 images varchar(1024) NULL商品的图片，多个图片以‘,’分割
 price bigint(15) NOT NULL销售价格，单位为分
 indexes varchar(32) NULL特有规格属性在spu属性模板中的对应下标组合
 own_spec varchar(1024) NULLsku的特有规格参数键值对，json格式，反序列化时请使用linkedHashMap，保证有序
 enable tinyint(1) NOT NULL是否有效，0无效，1有效
 create_time datetime NOT NULL添加时间
 last_update_time datetime NOT NULL最后修改时间
 */
@Table(name = "tb_sku")
public class Sku implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long spuId;
    private String title;
    private String images;
    private Long price;
    private String indexes;
    private String ownSpec;
    private Boolean enable;
    private Date createTime;
    private Date lastUpdateTime;
    @Transient
    private Integer stock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getIndexes() {
        return indexes;
    }

    public void setIndexes(String indexes) {
        this.indexes = indexes;
    }

    public String getOwnSpec() {
        return ownSpec;
    }

    public void setOwnSpec(String ownSpec) {
        this.ownSpec = ownSpec;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
