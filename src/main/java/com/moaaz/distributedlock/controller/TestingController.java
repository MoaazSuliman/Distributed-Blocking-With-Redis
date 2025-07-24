package com.moaaz.distributedlock.controller;

import com.moaaz.distributedlock.service.TestingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class TestingController {
    private final TestingService testingService;

    public TestingController(TestingService testingService) {
        this.testingService = testingService;
    }

    @GetMapping
    public String hello() {
        testingService.doYourMagic();
        return "Redis is the best";
    }
}
