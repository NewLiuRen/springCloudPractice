package com.demo.mapper;

import com.demo.pojo.Brand;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface IBrandMapper extends Mapper<Brand> {
    public void saveCategoryAndBrand(@Param("cid") Long cid, @Param("bid") Long bid);
}
