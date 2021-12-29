package com.demo.client;

import com.demo.LeyouSearchService;
import com.demo.bo.SpuBO;
import com.demo.common.pojo.PageResult;
import com.demo.pojo.Brand;
import com.demo.pojo.Goods;
import com.demo.pojo.Sku;
import com.demo.repository.GoodsRepository;
import com.demo.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(classes = LeyouSearchService.class)
@RunWith(SpringRunner.class)
public class CategoryClientTest {
    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private GoodsClient goodsClient;
    @Autowired
    private BrandClient brandClient;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private SearchService searchService;

    @Test
    public void testGetCategoryNames() {
        Brand brand = this.brandClient.findBrandById(1528L);
        System.out.println(brand.getName());
    }

    @Test
    public void testApi() {
        List<Sku> skus = this.goodsClient.findSkuListBySpuId(199L);
        skus.forEach(sku -> {
            System.out.println(sku.getTitle());
        });
    }

    @Test
    public void testImportGoodsData() {
        int page = 1;
        int rows = 100;

        do {
            PageResult<SpuBO> result = this.goodsClient.findSpuBOByPage("", true, page, rows);
            List<Goods> goods = result.getItems().stream().map(spuBO -> {
                try {
                    return this.searchService.buildGoods(spuBO);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());

            this.goodsRepository.saveAll(goods);
            rows = result.getItems().size();
            page++;
        } while (rows == 100);
    }
}