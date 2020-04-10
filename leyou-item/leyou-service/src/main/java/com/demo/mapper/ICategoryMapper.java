package com.demo.mapper;

import com.demo.pojo.Category;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ICategoryMapper extends Mapper<Category> {
}
