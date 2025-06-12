package com.example.recetarium.demo.DTOs;

import java.time.LocalDateTime;
import java.util.List;

public class RecetaRequestDto {
    private String nombreReceta;
    private String descripcionReceta;
    private String porciones;
    private int cantidadPersonas;
    private String aliasUsuario; // alias o mail
    private byte[] imagenPrincipal;

    private List<byte[]> imagenes; // otras fotos para FotoReceta
    private List<PasoRequestDto> pasos;
    private List<IngredienteRequestDto> ingredientesUsados;

    public RecetaRequestDto(String nombreReceta, String descripcionReceta, String porciones, int cantidadPersonas, String aliasUsuario, byte[] imagenPrincipal, List<byte[]> imagenes, List<PasoRequestDto> pasos, List<IngredienteRequestDto> ingredientesUsados) {
        this.nombreReceta = nombreReceta;
        this.descripcionReceta = descripcionReceta;
        this.porciones = porciones;
        this.cantidadPersonas = cantidadPersonas;
        this.aliasUsuario = aliasUsuario;
        this.imagenPrincipal = imagenPrincipal;
        this.imagenes = imagenes;
        this.pasos = pasos;
        this.ingredientesUsados = ingredientesUsados;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public String getDescripcionReceta() {
        return descripcionReceta;
    }

    public String getPorciones() {
        return porciones;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public String getAliasUsuario() {
        return aliasUsuario;
    }

    public byte[] getImagenPrincipal() {
        return imagenPrincipal;
    }

    public List<byte[]> getImagenes() {
        return imagenes;
    }

    public List<PasoRequestDto> getPasos() {
        return pasos;
    }

    public List<IngredienteRequestDto> getIngredientesUsados() {
        return ingredientesUsados;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public void setDescripcionReceta(String descripcionReceta) {
        this.descripcionReceta = descripcionReceta;
    }

    public void setPorciones(String porciones) {
        this.porciones = porciones;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public void setAliasUsuario(String aliasUsuario) {
        this.aliasUsuario = aliasUsuario;
    }

    public void setImagenPrincipal(byte[] imagenPrincipalBase64) {
        this.imagenPrincipal = imagenPrincipalBase64;
    }

    public void setImagenes(List<byte[]> imagenesBase64) {
        this.imagenes = imagenesBase64;
    }

    public void setPasos(List<PasoRequestDto> pasos) {
        this.pasos = pasos;
    }

    public void setIngredientesUsados(List<IngredienteRequestDto> ingredientesUsados) {
        this.ingredientesUsados = ingredientesUsados;
    }
}
