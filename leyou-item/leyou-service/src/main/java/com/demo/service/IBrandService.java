package com.demo.service;

import com.demo.common.pojo.PageResult;
import com.demo.pojo.Brand;

import java.util.List;

public interface IBrandService {
    PageResult<Brand> findBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc);

    Brand findBrandById(Long bid);

    List<Brand> findBrandsByCid(Long cid);

    void addBrand(Brand brand, List<Long> cids);

    void updateBrand(Brand brand, List<Long> cids);

    void deleteBrand(Long bid);
}
