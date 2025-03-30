package com.example.storehouse.rest_api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping("/hello")
    public void sayHello() {
        System.out.println("dernuly hallo");
    }

}