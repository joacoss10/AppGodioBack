package com.example.recetarium.demo.DTOs;

import com.example.recetarium.demo.Model.Enums.EstadoReceta;

public class MisRecetasPreviewRespondDto {
    private Long idReceta;
    private String titulo;
    private byte[] imagenPrincipal;
    private String autor; // alias o nombre de usuario
    private Double clasificacionPromedio;
    private boolean enFavoritos;
    private EstadoReceta estado;

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setImagenPrincipal(byte[] imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setClasificacionPromedio(Double clasificacionPromedio) {
        this.clasificacionPromedio = clasificacionPromedio;
    }

    public void setEnFavoritos(boolean enFavoritos) {
        this.enFavoritos = enFavoritos;
    }

    public void setEstado(EstadoReceta estado) {
        this.estado = estado;
    }

    public Long getIdReceta() {
        return idReceta;
    }

    public String getTitulo() {
        return titulo;
    }

    public byte[] getImagenPrincipal() {
        return imagenPrincipal;
    }

    public String getAutor() {
        return autor;
    }

    public Double getClasificacionPromedio() {
        return clasificacionPromedio;
    }

    public boolean isEnFavoritos() {
        return enFavoritos;
    }

    public EstadoReceta getEstado() {
        return estado;
    }
}
