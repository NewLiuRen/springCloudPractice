package com.demo.client;

import com.demo.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("leyou-service")
public interface CategoryClient extends CategoryApi {}
