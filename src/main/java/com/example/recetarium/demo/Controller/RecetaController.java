package com.example.recetarium.demo.Controller;

import com.example.recetarium.demo.DTOs.RecetaPreviewRespondDto;
import com.example.recetarium.demo.DTOs.RecetaRequestDto;
import com.example.recetarium.demo.DTOs.RecetaResponseDto;
import com.example.recetarium.demo.Service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/recetas")
public class RecetaController {
    @Autowired
    RecetaService recetaService;

    @PostMapping
    public RecetaResponseDto crearReceta(@RequestBody RecetaRequestDto dto){
        return recetaService.crearReceta(dto);
    }



    @GetMapping("/previews")
        public List<RecetaPreviewRespondDto> obtenerPreviews(
            @RequestParam(required = false) Long idUsuario,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return recetaService.obtenerPreviews(idUsuario,pageable);
    }
}
