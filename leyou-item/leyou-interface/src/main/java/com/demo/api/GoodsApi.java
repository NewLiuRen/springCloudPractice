package com.demo.api;

import com.demo.bo.SpuBO;
import com.demo.common.pojo.PageResult;
import com.demo.pojo.Sku;
import com.demo.pojo.SpuDetail;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
public interface GoodsApi {
    @GetMapping("spu/page")
    PageResult<SpuBO> findSpuBOByPage(
            @RequestParam(required = false) String key,
            @RequestParam(required = false) Boolean saleable,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer rows
    );

    @GetMapping("spu/detail/{id}")
    SpuDetail findSpuBOById(@PathVariable("id") Long id);

    @GetMapping("sku/list/{id}")
    List<Sku> findSkuListBySpuId(@PathVariable("id") Long id);

    /**
     * brandId: 1528
     * cid1: 74
     * cid2: 75
     * cid3: 76
     * skus: [{price: 11100, stock: 0, indexes: "0_0_0", enable: true, title: "1 1 1 1", images: "",…}]
     * spuDetail: {packingList: "3", afterService: "4", description: "<p>5</p>",…}
     * subTitle: "2"
     * title: "1"
     */
    @PostMapping("goods")
    Void addGoods(@RequestBody SpuBO spuBO);

    @PutMapping("goods")
    Void updateGoods(@RequestBody SpuBO spuBO);

    @PutMapping("goods/valid/{id}")
    Void deleteGoods(@PathVariable("id") Long id);

    @PutMapping("goods/saleable/{id}")
    Void saleableGoods(@PathVariable("id") Long id);

    @PutMapping("goods/unsaleable/{id}")
    Void unsaleableGoods(@PathVariable("id") Long id);
}
