package com.example.recetarium.demo.Controller;

import com.example.recetarium.demo.DTOs.RecetaRequestDto;
import com.example.recetarium.demo.DTOs.RecetaResponseDto;
import com.example.recetarium.demo.Service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recetas")
public class RecetaController {
    @Autowired
    RecetaService recetaService;

    @PostMapping
    public RecetaResponseDto crearReceta(@RequestBody RecetaRequestDto dto){
        return recetaService.crearReceta(dto);
    }
}
