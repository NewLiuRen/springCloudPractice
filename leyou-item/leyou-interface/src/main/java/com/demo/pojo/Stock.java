package com.demo.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * sku_id bigint(20) NOT NULL库存对应的商品sku id
 * seckill_stock int(9) NULL可秒杀库存
 * seckill_total int(9) NULL秒杀总数量
 * stock int(9) NOT NULL库存数量
 */
@Table(name = "tb_stock")
public class Stock implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    private Long skuId;
    private Integer seckillStock;
    private Integer seckillTotal;
    private Integer stock;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getSeckillStock() {
        return seckillStock;
    }

    public void setSeckillStock(Integer seckillStock) {
        this.seckillStock = seckillStock;
    }

    public Integer getSeckillTotal() {
        return seckillTotal;
    }

    public void setSeckillTotal(Integer seckillTotal) {
        this.seckillTotal = seckillTotal;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
