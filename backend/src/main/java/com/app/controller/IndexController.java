package com.app.controller;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class IndexController {

    @GetMapping("/")
    public @ResponseBody String index() {
        return "이곳은API 서버입니다.";
    }

    @GetMapping("/api")
    public @ResponseBody String apiIndex() {
        return "이곳은API 서버입니다.";
    }

    @GetMapping("/healthcheck")
    public @ResponseBody String healthCheck() {
        return "이곳은API 서버입니다.";
    }
}
