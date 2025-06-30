package com.example.recetarium.demo.Controller;

import com.example.recetarium.demo.DTOs.ModificarIngredienteRequestDto;
import com.example.recetarium.demo.DTOs.RecetaAjustadaDto;
import com.example.recetarium.demo.DTOs.RecetaResponseDto;
import com.example.recetarium.demo.Service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recetaModificada")
public class ModificarController {
    @Autowired
    RecetaService recetaService;
    @PostMapping("ingrediente")
    public RecetaAjustadaDto ajustarPorIngrediente(@RequestParam Long idReceta, @RequestBody ModificarIngredienteRequestDto request){
        return recetaService.ajustarPorIngrediente(idReceta,request);
    }
}
