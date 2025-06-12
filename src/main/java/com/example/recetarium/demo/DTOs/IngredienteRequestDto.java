package com.example.recetarium.demo.DTOs;

public class IngredienteRequestDto {
    private String nombreIngrediente; // debe existir en la BD
    private String unidad;            // debe existir en la BD
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
}
