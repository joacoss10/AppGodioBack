package com.example.recetarium.demo.DTOs;

public class FavoritoRequestDto {
    private Long idUsuario;
    private Long idReceta;
    private boolean favorito;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public Long getIdReceta() {
        return idReceta;
    }

    public boolean isFavorito() {
        return favorito;
    }
}
