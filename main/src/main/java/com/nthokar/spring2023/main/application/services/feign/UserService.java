package com.nthokar.spring2023.main.application.services.feign;

import com.nthokar.spring2023.main.application.entities.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "userService", url = "${spring.gateway.url}")
public interface UserService {
    @RequestMapping(method = RequestMethod.POST, value = "/api/auth/validate")
    UserEntity validateToken(String token);

    @RequestMapping(method = RequestMethod.GET, value = "/api/auth/get")
    UserEntity getUser(String userId);
}
