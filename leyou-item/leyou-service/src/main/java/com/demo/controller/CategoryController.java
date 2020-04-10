package com.demo.controller;

import com.demo.pojo.Category;
import com.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("{pid}")
    public ResponseEntity<List<Category>> findCategories(@PathVariable("pid") Long pid) {
        if (pid == null || pid.longValue() < 0) return ResponseEntity.badRequest().build();

        List<Category> categories = categoryService.findCategoriesByPid(pid);
        System.out.println(categories);
        if (CollectionUtils.isEmpty(categories)) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(categories);
    }
}
