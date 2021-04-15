package com.trilogyed.tasker.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ad-server-service")
public interface AdServiceFeignClient {

    @RequestMapping(value = "/ad", method = RequestMethod.GET)
    String getAd();

}
