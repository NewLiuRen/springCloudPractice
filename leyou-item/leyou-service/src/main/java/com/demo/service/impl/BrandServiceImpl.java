package com.demo.service.impl;

import com.demo.common.pojo.PageResult;
import com.demo.mapper.IBrandMapper;
import com.demo.pojo.Brand;
import com.demo.service.IBrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements IBrandService {
    private final IBrandMapper brandMapper;

    public BrandServiceImpl(IBrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    @Override
    public PageResult<Brand> findBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotBlank(key)) criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);

        PageHelper.startPage(page, rows);

        if (StringUtils.isNotBlank(sortBy)) example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));

        List<Brand> brands = this.brandMapper.selectByExample(example);
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);

        return new PageResult<>(pageInfo.getTotal(), brands);
    }

    @Override
    public Brand findBrandById(Long bid) {
        return this.brandMapper.selectByPrimaryKey(bid);
    }

    @Override
    public List<Brand> findBrandsByCid(Long cid) {
        return this.brandMapper.findBrandsByCid(cid);
    }

    @Override
    public void addBrand(Brand brand, List<Long> cids) {
        this.brandMapper.insertSelective(brand);

        cids.forEach(cid -> this.brandMapper.saveCategoryAndBrand(cid, brand.getId()));
    }

    @Override
    public void updateBrand(Brand brand, List<Long> cids) {
        this.brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void deleteBrand(Long bid) {
        this.brandMapper.deleteCategoryAndBrandByBrandId(bid);
        this.brandMapper.deleteByPrimaryKey(bid);
    }
}
