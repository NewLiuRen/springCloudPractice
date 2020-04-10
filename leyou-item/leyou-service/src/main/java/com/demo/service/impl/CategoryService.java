package com.demo.service.impl;

import com.demo.mapper.ICategoryMapper;
import com.demo.pojo.Category;
import com.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryMapper categoryMapper;

    @Override
    public List<Category> findCategoriesByPid(Long pid) {
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("parentId",pid);
        List<Category> list = this.categoryMapper.selectByExample(example);
//        Category category = new Category();
//        category.setParentId(pid);
//        return categoryMapper.select(category);
        return list;
    }
}
