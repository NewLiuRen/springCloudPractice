package com.demo.mapper;

import com.demo.pojo.Brand;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface IBrandMapper extends Mapper<Brand> {
    public List<Brand> findBrandsByCid(@Param("cid") Long cid);

    public void saveCategoryAndBrand(@Param("cid") Long cid, @Param("bid") Long bid);

    public void deleteCategoryAndBrandByBrandId(@Param("bid") Long bid);
}
