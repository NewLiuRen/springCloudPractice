package com.demo.controller;

import com.demo.bo.SpuBO;
import com.demo.common.pojo.PageResult;
import com.demo.pojo.Sku;
import com.demo.pojo.SpuDetail;
import com.demo.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class GoodsController {
    @Autowired
    private IGoodsService goodsService;

    // String key, Boolean saleable, Integer page, Integer rows
    @GetMapping("spu/page")
    public ResponseEntity<PageResult<SpuBO>> findSpuBOByPage(
            @RequestParam(required = false) String key,
            @RequestParam(required = false) Boolean saleable,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer rows
    ) {
        PageResult<SpuBO> result = this.goodsService.findByPage(key, saleable, page, rows);
        if (CollectionUtils.isEmpty(result.getItems())) ResponseEntity.noContent().build();
        return ResponseEntity.ok(result);
    }

    @GetMapping("spu/detail/{id}")
    public ResponseEntity<SpuDetail> findSpuBOById(@PathVariable("id") Long id) {
        if (id == null || id.longValue() < 0) return ResponseEntity.badRequest().build();

        SpuDetail spuDetail = this.goodsService.findSpuBySpuId(id);
        if (spuDetail == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(spuDetail);
    }

    @GetMapping("sku/list/{id}")
    public ResponseEntity<List<Sku>> findSkuListBySpuId(@PathVariable("id") Long id) {
        if (id == null || id.longValue() < 0) return ResponseEntity.badRequest().build();

        List<Sku> skus = this.goodsService.findSkusBySpuId(id);
        if (CollectionUtils.isEmpty(skus)) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(skus);
    }

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
    public ResponseEntity<Void> addGoods(@RequestBody SpuBO spuBO) {
        this.goodsService.addGoods(spuBO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("goods")
    public ResponseEntity<Void> updateGoods(@RequestBody SpuBO spuBO) {
        this.goodsService.updateGoods(spuBO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
