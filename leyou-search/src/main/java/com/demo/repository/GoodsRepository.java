package com.demo.repository;

import com.demo.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {
}
