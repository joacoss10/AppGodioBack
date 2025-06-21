package com.example.recetarium.demo.Controller;

import com.example.recetarium.demo.DTOs.MisRecetasPreviewRespondDto;
import com.example.recetarium.demo.DTOs.RecetaPreviewRespondDto;
import com.example.recetarium.demo.Service.BusquedaRecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buscar")
public class BusquedaRecetaController {
    @Autowired
    BusquedaRecetaService serviceBusqueda;

    @GetMapping("/misrecetas")
    public Page<MisRecetasPreviewRespondDto> buscarEnMisRecetas(@RequestParam Long idUsuario,@RequestParam String palabraClave,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        return serviceBusqueda.buscarMisRecetas(idUsuario,palabraClave,pageable);
    }
    @GetMapping("misfavoritos")
    public Page<RecetaPreviewRespondDto> buscarEnMisFavoritos(@RequestParam Long idUsuario,@RequestParam String palabraClave,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        return serviceBusqueda.buscarFavoritosPorNombre(idUsuario,palabraClave,pageable);
    }
}
