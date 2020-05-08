package com.demo.service;

import com.demo.bo.SpuBO;
import com.demo.common.pojo.PageResult;
import com.demo.pojo.Sku;
import com.demo.pojo.Spu;
import com.demo.pojo.SpuDetail;

import java.util.List;

public interface IGoodsService {
    PageResult<SpuBO> findByPage(String key, Boolean saleable, Integer page, Integer rows);

    SpuDetail findSpuBySpuId(Long spuId);

    List<Sku> findSkusBySpuId(Long spuId);

    void addGoods(SpuBO spuBO);

    void updateGoods(SpuBO spuBO);
    
    void updateGoodsValid(SpuBO spuBO);
    
    void updateGoodsSaleable(SpuBO spuBO);
}
