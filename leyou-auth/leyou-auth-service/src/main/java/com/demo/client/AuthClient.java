package com.demo.client;

import client.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("user-service")
public interface AuthClient extends UserApi {
}
