package com.example.recetarium.demo.DTOs;

public class IngredienteUsadoResponseDto {
    private String nombreIngrediente;
    private String unidad;
    private int cantidad;
    private String observacion;

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public String getUnidad() {
        return unidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
