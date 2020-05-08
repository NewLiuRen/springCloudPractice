package com.demo.mapper;

import com.demo.pojo.Sku;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ISkuMapper extends Mapper<Sku> {
}
