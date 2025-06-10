package com.example.recetarium.demo.DTOs;

import java.time.LocalDateTime;
import java.util.List;

public class RecetaRequestDto {
    private String nombreReceta;
    private String descripcionReceta;
    private String porciones;
    private int cantidadPersonas;

    private String aliasUsuario;//PARA MACHEAR CON Usuario
    //Imagenes
    private String imagenPrincipalBase64;
    private List<String> imagenesBase64;
    //

    public RecetaRequestDto(String nombreReceta, String descripcionReceta, String porciones, int cantidadPersonas, String aliasUsuario) {
        this.nombreReceta = nombreReceta;
        this.descripcionReceta = descripcionReceta;
        this.porciones = porciones;
        this.cantidadPersonas = cantidadPersonas;
        this.aliasUsuario = aliasUsuario;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public String getDescripcionReceta() {
        return descripcionReceta;
    }

    public void setDescripcionReceta(String descripcionReceta) {
        this.descripcionReceta = descripcionReceta;
    }

    public String getPorciones() {
        return porciones;
    }

    public void setPorciones(String porciones) {
        this.porciones = porciones;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public String getAliasUsuario() {
        return aliasUsuario;
    }

    public void setAliasUsuario(String aliasUsuario) {
        this.aliasUsuario = aliasUsuario;
    }
}
