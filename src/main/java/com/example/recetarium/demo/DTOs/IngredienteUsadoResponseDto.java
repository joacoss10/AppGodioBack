package com.example.recetarium.demo.DTOs;

public class IngredienteUsadoResponseDto {//utilizado
    private String nombreIngrediente;//mach con ingrediente
    private String unidad;//mach con unidad
    private float cantidad;
    private String observacion;

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public String getUnidad() {
        return unidad;
    }

    public float getCantidad() {
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

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
