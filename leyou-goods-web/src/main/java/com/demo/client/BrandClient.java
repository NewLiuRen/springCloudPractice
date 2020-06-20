package com.demo.client;

import com.demo.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("leyou-service")
public interface BrandClient extends BrandApi {}
