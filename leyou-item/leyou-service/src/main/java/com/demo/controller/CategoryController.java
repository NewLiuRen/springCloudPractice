package com.demo.controller;

import com.demo.pojo.Category;
import com.demo.service.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("{pid}")
    public ResponseEntity<List<Category>> findCategories(@PathVariable("pid") Long pid) {
        if (pid == null || pid < 0) return ResponseEntity.badRequest().build();

        List<Category> categories = categoryService.findCategoriesByPid(pid);

        if (CollectionUtils.isEmpty(categories)) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> findCategoriesByBrandId(@PathVariable("bid") Long bid) {
        if (bid == null || bid < 0) return ResponseEntity.badRequest().build();

        List<Category> categories = categoryService.findCategoriesByBrandId(bid);

        if (CollectionUtils.isEmpty(categories)) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(categories);
    }

    @PostMapping("cid")
    public ResponseEntity<List<Category>> findCategoryById(@RequestBody List<Long> ids) {
        if (ids == null || CollectionUtils.isEmpty(ids)) return ResponseEntity.badRequest().build();
        List<Category> categories = this.categoryService.findCategoryNamesByIds(ids);
        return ResponseEntity.ok(categories);
    }
}
