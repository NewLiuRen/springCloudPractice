package com.demo.service;

import com.demo.common.pojo.PageResult;
import com.demo.pojo.Brand;

import java.util.List;

public interface IBrandService {
    public PageResult<Brand> findBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc);

    void addBrand(Brand brand, List<Long> cids);
}
