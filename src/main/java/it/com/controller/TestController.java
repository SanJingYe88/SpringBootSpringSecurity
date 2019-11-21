package it.com.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PreAuthorize("hasAuthority('user')")
    @RequestMapping("/hello")
    public String hello(){
        return "spring security";
    }

    @GetMapping("/signout/success")
    public String signout() {
        return "退出成功，请重新登录";
    }
}
