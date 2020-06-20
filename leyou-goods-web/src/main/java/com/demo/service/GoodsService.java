package com.demo.service;

import com.demo.client.BrandClient;
import com.demo.client.CategoryClient;
import com.demo.client.GoodsClient;
import com.demo.client.SpecClient;
import com.demo.pojo.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GoodsService {
    private final GoodsClient goodsClient;
    private final BrandClient brandClient;
    private final CategoryClient categoryClient;
    private final SpecClient specClient;

    public GoodsService(GoodsClient goodsClient, BrandClient brandClient, CategoryClient categoryClient, SpecClient specClient) {
        this.goodsClient = goodsClient;
        this.brandClient = brandClient;
        this.categoryClient = categoryClient;
        this.specClient = specClient;
    }

    public Map<String, Object> loadModel(Long id) {
        HashMap<String, Object> map = new HashMap<>();
        // 根据id查询spu对象
        Spu spu = this.goodsClient.findSpuById(id);
        // 查询spudetail
        SpuDetail spuDetail = this.goodsClient.findSpuBOById(id);
        // 查询sku集合
        List<Sku> skus = this.goodsClient.findSkuListBySpuId(id);
        // 查询分类
        List<Category> categoryList = this.categoryClient.findCategoryById(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
        List<HashMap<String, Object>> categories = categoryList.stream().map(category -> {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("id", category.getId());
            hashMap.put("name", category.getName());
            return hashMap;
        }).collect(Collectors.toList());
        // 查询品牌
        Brand brand = this.brandClient.findBrandById(spu.getBrandId());
        // 查询规格参数组
        List<SpecGroup> groups = this.specClient.findSpecWithParamByCid(spu.getCid3());
        // 查询特殊的规格参数
        List<SpecParam> params = this.specClient.findParams(null, spu.getCid3(), null, null);
        Map<Long, String> paramMap = params.stream().collect(Collectors.toMap(SpecParam::getId, SpecParam::getName, (key1, key2) -> key2));

        map.put("spu", spu);
        map.put("spuDetail", spuDetail);
        map.put("skus", skus);
        map.put("categories", categories);
        map.put("brand", brand);
        map.put("groups", groups);
        map.put("paramMap", paramMap);

        return map;
    }
}
