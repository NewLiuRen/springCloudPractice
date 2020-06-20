package com.demo.client;

import com.demo.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("leyou-service")
public interface GoodsClient extends GoodsApi {
}
