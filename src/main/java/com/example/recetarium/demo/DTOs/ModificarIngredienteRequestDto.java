package com.example.recetarium.demo.DTOs;

public class ModificarIngredienteRequestDto {
    private String nombreIngrediente;
    private float nuevaCantidad;
    private String nuevaUnidad;

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public float getNuevaCantidad() {
        return nuevaCantidad;
    }

    public String getNuevaUnidad() {
        return nuevaUnidad;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public void setNuevaCantidad(float nuevaCantidad) {
        this.nuevaCantidad = nuevaCantidad;
    }

    public void setNuevaUnidad(String nuevaUnidad) {
        this.nuevaUnidad = nuevaUnidad;
    }
}
