package com.demo.service.impl;

import com.demo.common.pojo.PageResult;
import com.demo.mapper.IBrandMapper;
import com.demo.pojo.Brand;
import com.demo.service.IBrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService implements IBrandService {
    @Autowired
    private IBrandMapper brandMapper;
    /**
     * 根据查询条件分页并排序查询品牌信息
     *
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @Override
    public PageResult<Brand> findBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }

        PageHelper.startPage(page, rows);

        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }

        List<Brand> brands = brandMapper.selectByExample(example);

        PageInfo<Brand> pageInfo = new PageInfo<>(brands);

        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public void addBrand(Brand brand, List<Long> cids) {
        this.brandMapper.insertSelective(brand);

        cids.forEach(cid -> this.brandMapper.saveCategoryAndBrand(cid, brand.getId()));
    }
}
