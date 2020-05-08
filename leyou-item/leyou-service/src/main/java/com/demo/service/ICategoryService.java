package com.demo.service;

import com.demo.pojo.Category;

import java.util.List;

public interface ICategoryService {
    public List<Category> findCategoriesByPid(Long pid);

    public List<Category> findCategoriesByBrandId(Long bid);

    public List<Category> findCategoryNamesByIds(List<Long> ids);
}
