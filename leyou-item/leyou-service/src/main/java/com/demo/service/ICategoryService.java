package com.demo.service;

import com.demo.pojo.Category;

import java.util.List;

public interface ICategoryService {
    public List<Category> findCategoriesByPid(Long pid);
}
