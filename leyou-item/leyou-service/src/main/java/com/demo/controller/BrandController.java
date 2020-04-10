package com.demo.controller;

import com.demo.common.pojo.PageResult;
import com.demo.pojo.Brand;
import com.demo.service.IBrandService;
import com.demo.service.impl.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private IBrandService brandService;

    /**
     * 分页获取品牌列表
     * @param key 搜索条件
     * @param page 当前页
     * @param rows 每页行数
     * @param sortBy 排序字段
     * @param desc 是否降序
     * @return
     */
    @GetMapping
    public ResponseEntity<PageResult<Brand>> findBrandsByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc
    ) {
        PageResult<Brand> brandPageResult = brandService.findBrandsByPage(key, page, rows, sortBy, desc);
        if (CollectionUtils.isEmpty(brandPageResult.getItems())) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(brandPageResult);
    }

    @PostMapping
    public ResponseEntity<Void> addBrand(Brand brand, @RequestParam("cids")List<Long>cids) {
        this.brandService.addBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
