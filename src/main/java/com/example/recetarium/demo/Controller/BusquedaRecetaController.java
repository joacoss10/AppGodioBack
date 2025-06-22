package com.example.recetarium.demo.Controller;

import com.example.recetarium.demo.DTOs.BusquedaMainRecetaRequestDto;
import com.example.recetarium.demo.DTOs.MisRecetasPreviewRespondDto;
import com.example.recetarium.demo.DTOs.RecetaPreviewRespondDto;
import com.example.recetarium.demo.Service.BusquedaRecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @GetMapping("/main")
    public Page<RecetaPreviewRespondDto> buscarEnMain(@RequestBody BusquedaMainRecetaRequestDto dto,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
        List<Sort.Order> ordenes = new ArrayList<>();
        //
        //primero ordena por autor, si un autor tiene mas de una receta las ordena por fecha, si hay mas de una receta con la misma fecha las ordena por orden alfabetico (ese es el orden en caso de que marque las tres)
        if(dto.isPorAutor()){
            ordenes.add(new Sort.Order(Sort.Direction.ASC,"usuario.alias"));
        }
        if(dto.isOrdenFecha()){
            ordenes.add(new Sort.Order(Sort.Direction.DESC, "fechaCreacion"));
        }
        if(dto.isOrdenAlfabetico()){
            ordenes.add(new Sort.Order(Sort.Direction.ASC,"nombreReceta"));
        }
        //
        Pageable pageable=PageRequest.of(page,size,Sort.by(ordenes));
        return serviceBusqueda.buscarRecetasMainFiltro(dto,pageable);
    }
}
