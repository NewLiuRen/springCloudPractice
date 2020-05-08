package com.demo.mapper;

import com.demo.pojo.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ICategoryMapper extends Mapper<Category> {
    public List<Category> findCategoriesByBrandId(@Param("bid") Long bid);

    public List<Category> findNamesByIds(@Param("ids") List<Long> ids);
}
