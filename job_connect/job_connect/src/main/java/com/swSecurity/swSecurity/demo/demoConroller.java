package com.swSecurity.swSecurity.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/demo-controller")
public class demoConroller {
    @GetMapping
    public ResponseEntity<String> sayHello()
    {
        return ResponseEntity.ok("hello secured endpoint");
    }
}
