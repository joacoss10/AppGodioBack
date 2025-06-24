package com.example.recetarium.demo.DTOs;

public class FiltroIngredienteDto {
    private String nombreIngrediente;
    private String unidadDeseada;
    private Double canitidadMaxima;
    private boolean incluir;


    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public void setUnidadDeseada(String unidadDeseada) {
        this.unidadDeseada = unidadDeseada;
    }

    public void setCanitidadMaxima(Double canitidadMaxima) {
        this.canitidadMaxima = canitidadMaxima;
    }

    public void setIncluir(boolean incluir) {
        this.incluir = incluir;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public String getUnidadDeseada() {
        return unidadDeseada;
    }

    public Double getCanitidadMaxima() {
        return canitidadMaxima;
    }

    public boolean isIncluir() {
        return incluir;
    }
}
