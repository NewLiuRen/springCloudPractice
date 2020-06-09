package com.demo.api;

import com.demo.pojo.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("category")
public interface CategoryApi {
    @GetMapping("{pid}")
    List<Category> findCategories(@PathVariable("pid") Long pid);

    @GetMapping("bid/{bid}")
    List<Category> findCategoriesByBrandId(@PathVariable("bid") Long bid);

    @PostMapping("cid")
    List<Category> findCategoryById(@RequestBody List<Long> ids);
}
