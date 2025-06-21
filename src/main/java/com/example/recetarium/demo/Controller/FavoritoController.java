package com.example.recetarium.demo.Controller;

import com.example.recetarium.demo.DTOs.FavoritoRequestDto;
import com.example.recetarium.demo.DTOs.FavoritoRespond;
import com.example.recetarium.demo.DTOs.RecetaPreviewRespondDto;
import com.example.recetarium.demo.Repository.FavoritoRepository;
import com.example.recetarium.demo.Service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receta/favorito")
public class FavoritoController {
    @Autowired
    FavoritoService serviceFavorito;

    @PostMapping
    FavoritoRespond favOEliminarFavs(@RequestBody FavoritoRequestDto dto){
        return serviceFavorito.agregarEliminarFavoritosReceta(dto);
    }
    /////preview/////////
    @GetMapping
    Page<RecetaPreviewRespondDto> obtenerFavoritos(@RequestParam Long idUsuario, @RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        return serviceFavorito.obtenerPreviewFavs(idUsuario,pageable);

    }
}
