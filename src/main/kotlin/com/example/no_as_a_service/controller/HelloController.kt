package com.example.no_as_a_service.controller

import com.example.no_as_a_service.dto.HelloWorldDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/")
    fun hello(): HelloWorldDto {
        return HelloWorldDto("Hello World!", "Timo")
    }

    @GetMapping("/test")
    fun test(): HelloWorldDto {
        return HelloWorldDto("Hello World (from test)!", "Timo")
    }
}