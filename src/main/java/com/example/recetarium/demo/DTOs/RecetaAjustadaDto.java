package com.example.recetarium.demo.DTOs;


import java.util.List;

public class RecetaAjustadaDto {
    private String titulo;
    private List<IngredienteUsadoResponseDto> ingredientes;
    public RecetaAjustadaDto(){}

    public RecetaAjustadaDto(String titulo, List<IngredienteUsadoResponseDto> ingredientes) {
        this.titulo = titulo;
        this.ingredientes = ingredientes;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<IngredienteUsadoResponseDto> getIngredientes() {
        return ingredientes;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIngredientes(List<IngredienteUsadoResponseDto> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
