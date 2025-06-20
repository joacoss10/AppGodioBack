package com.example.recetarium.demo.DTOs;

public class IngredienteUsadoResponseDto {//utilizado
    private String nombreIngrediente;//mach con ingrediente
    private String unidad;//mach con unidad
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
