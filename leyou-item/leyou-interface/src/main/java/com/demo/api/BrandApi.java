package com.demo.api;

import com.demo.common.pojo.PageResult;
import com.demo.pojo.Brand;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("brand")
public interface BrandApi {

    /**
     * 分页获取品牌列表
     * @param key 搜索条件
     * @param page 当前页
     * @param rows 每页行数
     * @param sortBy 排序字段
     * @param desc 是否降序
     * @return 分页的Brand对象集合
     */
    @GetMapping
    PageResult<Brand> findBrandsByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc
    );

    @GetMapping("bid/{bid}")
    Brand findBrandById(@PathVariable("bid") Long bid);

    @GetMapping("cid/{cid}")
    List<Brand> findBrandsByCid(@PathVariable("cid") Long cid);

    @PostMapping
    Void addBrand(Brand brand, @RequestParam("cids") List<Long> cids);

    @PutMapping
    Void updateBrand(Brand brand, @RequestParam("cids") List<Long> cids);

    @DeleteMapping("bid/{bid}")
    Void deleteBrand(@PathVariable("bid") Long bid);
}
