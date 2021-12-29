package com.demo.client;

import com.demo.api.SpecApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("leyou-service")
public interface SpecClient extends SpecApi {}
