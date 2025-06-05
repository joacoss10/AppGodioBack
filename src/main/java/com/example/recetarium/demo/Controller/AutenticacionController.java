package com.example.recetarium.demo.Controller;

import com.example.recetarium.demo.DTOs.LogginRequestDto;
import com.example.recetarium.demo.DTOs.LogginResponseDto;
import com.example.recetarium.demo.Service.AutenticacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/loggin")
public class AutenticacionController {
    @Autowired
    AutenticacionService service;

    @PostMapping
    public LogginResponseDto loggin(@RequestBody LogginRequestDto requestDto){
        return service.logginCheck(requestDto);
    }
}
