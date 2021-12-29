package com.demo.client;

import com.demo.LeyouSearchService;
import com.demo.pojo.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LeyouSearchService.class)
public class ElasticSearchTest {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testCreateIndex() {
        // 创建索引
        this.elasticsearchTemplate.createIndex(Goods.class);
        // 创建映射
        this.elasticsearchTemplate.putMapping(Goods.class);
    }

    public void testImportData() {

    }
}
