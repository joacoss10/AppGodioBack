package com.example.recetarium.demo.DTOs;

import java.util.Date;
import java.util.List;

public class RecetaDetalleResponseDto {
    private String nombreReceta;
    private String descripcionReceta;
    private String porciones;
    private int cantidadPersonas;
    private String aliasCreador;
    private byte[] imagenPrincipal;
    private List<byte[]> imagenesAdicionales;
    private List<PasoResponseDto> pasos;
    private List<IngredienteUsadoResponseDto> ingredientesUsados;
    private List<ComentarioResponseDto> comentarios;
    private double promedioCalificacion;
    private boolean favorito;
    private Long idReceta;//le tengo que pasar el id por si la agrega como favs, para que el front me lo pase
    private Date fechaCreacion;
    private Integer clasificacionIndividualUsuario;//en caso de que este logeado y halla clasificado la receta.

    public Integer getClasificacionIndividualUsuario() {
        return clasificacionIndividualUsuario;
    }

    public void setClasificacionIndividualUsuario(Integer clasificacionIndividualUsuario) {
        this.clasificacionIndividualUsuario = clasificacionIndividualUsuario;
    }

    public Long getIdReceta() {
        return idReceta;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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

    public void setAliasCreador(String aliasCreador) {
        this.aliasCreador = aliasCreador;
    }

    public void setImagenPrincipal(byte[] imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }

    public void setImagenesAdicionales(List<byte[]> imagenesAdicionales) {
        this.imagenesAdicionales = imagenesAdicionales;
    }

    public void setPasos(List<PasoResponseDto> pasos) {
        this.pasos = pasos;
    }

    public void setIngredientesUsados(List<IngredienteUsadoResponseDto> ingredientesUsados) {
        this.ingredientesUsados = ingredientesUsados;
    }

    public void setComentarios(List<ComentarioResponseDto> comentarios) {
        this.comentarios = comentarios;
    }

    public void setPromedioCalificacion(double promedioCalificacion) {
        this.promedioCalificacion = promedioCalificacion;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
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

    public String getAliasCreador() {
        return aliasCreador;
    }

    public byte[] getImagenPrincipal() {
        return imagenPrincipal;
    }

    public List<byte[]> getImagenesAdicionales() {
        return imagenesAdicionales;
    }

    public List<PasoResponseDto> getPasos() {
        return pasos;
    }

    public List<IngredienteUsadoResponseDto> getIngredientesUsados() {
        return ingredientesUsados;
    }

    public List<ComentarioResponseDto> getComentarios() {
        return comentarios;
    }

    public double getPromedioCalificacion() {
        return promedioCalificacion;
    }

    public boolean isFavorito() {
        return favorito;
    }
}
