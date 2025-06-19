package com.example.recetarium.demo.Controller;

import com.example.recetarium.demo.DTOs.*;
import com.example.recetarium.demo.Service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/recetas")
public class RecetaController {
    /////////////operaciones con recetas/////////////////
    @Autowired
    RecetaService recetaService;

    @PostMapping
    public RecetaResponseDto crearReceta(@RequestBody RecetaRequestDto dto){
        return recetaService.crearReceta(dto);
    }
    @DeleteMapping
    public RecetaResponseDto eliminarReceta(@RequestParam Long id){
        return recetaService.eliminarReceta(id);
    }
    @PutMapping
    public RecetaResponseDto editarReceta(@RequestParam Long id,@RequestBody RecetaRequestDto dto){
        return  recetaService.editarReceta(id,dto);
    }
    @PostMapping("/calificacion")
    public CalificacionResponseDto estrellitasReceta(@RequestBody CalificacionRequestDto dto){
        return recetaService.calificarReceta(dto);
    }
    @PostMapping("/comentar")
    public CalificacionResponseDto comentarReceta(@RequestBody CalificacionRequestDto dto){
        return recetaService.comentarReceta(dto);
    }

//////////previews/////////////
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
