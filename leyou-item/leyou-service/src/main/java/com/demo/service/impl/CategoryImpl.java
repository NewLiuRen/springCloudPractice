package com.demo.service.impl;

import com.demo.mapper.ICategoryMapper;
import com.demo.pojo.Category;
import com.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImpl implements ICategoryService {
    @Autowired
    private ICategoryMapper categoryMapper;

    @Override
    public List<Category> findCategoriesByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        return this.categoryMapper.select(category);
    }

    @Override
    public List<Category> findCategoriesByBrandId(Long bid) {
        return this.categoryMapper.findCategoriesByBrandId(bid);
    }

    @Override
    public List<Category> findCategoryNamesByIds(List<Long> ids) {
        return this.categoryMapper.findNamesByIds(ids);
    }
}
