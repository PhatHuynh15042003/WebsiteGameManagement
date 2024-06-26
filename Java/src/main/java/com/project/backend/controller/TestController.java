package com.project.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test-controller")
public class TestController {

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Tài khoản của bạn đã được thông qua bảo mật :>");
    }

    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
