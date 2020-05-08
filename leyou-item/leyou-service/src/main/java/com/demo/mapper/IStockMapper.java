package com.demo.mapper;

import com.demo.pojo.Stock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface IStockMapper extends Mapper<Stock> {
    public void deleteSotckByids(@Param("ids") List<Long> ids);
}
