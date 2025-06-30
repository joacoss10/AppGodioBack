package com.example.recetarium.demo.DTOs;


import java.util.List;

public class RecetaAjustadaDto {
    private String titulo;
    private List<IngredienteUsadoResponseDto> ingredientes;

    public RecetaAjustadaDto(String titulo, List<IngredienteUsadoResponseDto> ingredientes) {
        this.titulo = titulo;
        this.ingredientes = ingredientes;
    }
}
