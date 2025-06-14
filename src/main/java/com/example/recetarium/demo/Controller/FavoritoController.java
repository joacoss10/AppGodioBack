package com.example.recetarium.demo.Controller;

import com.example.recetarium.demo.DTOs.FavoritoRequestDto;
import com.example.recetarium.demo.Repository.FavoritoRepository;
import com.example.recetarium.demo.Service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receta/favorito")
public class FavoritoController {
    @Autowired
    FavoritoService serviceFavorito;

    @PostMapping void favOEliminarFavs(@RequestBody FavoritoRequestDto dto){
        serviceFavorito.agregarEliminarFavoritosReceta(dto);
    }
}
