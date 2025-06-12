package com.example.recetarium.demo.DTOs;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public class InfoGeneralCursoRespondDto {
    private String titulo;
    private String descripcion;
    private Time hora;
    private Long idCronograma;
    private List<String> recomendaciones;
    private int duracion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int vacantes;
    private String ubicacion;
    private float precio;
    private float bonificacion;
    private float precioFinal;


    public String getDescripcion() {
        return descripcion;
    }

    public Time getHora() {
        return hora;
    }

    public List<String> getRecomendaciones() {
        return recomendaciones;
    }

    public int getDuracion() {
        return duracion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public float getPrecio() {
        return precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getIdCronograma() {
        return idCronograma;
    }

    public void setIdCronograma(Long idCronograma) {
        this.idCronograma = idCronograma;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setBonificacion(float bonificacion) {
        this.bonificacion = bonificacion;
    }

    public void setPrecioFinal(float previoFinal) {
        this.precioFinal = previoFinal;
    }

    public float getBonificacion() {
        return bonificacion;
    }

    public float getPrecioFinal() {
        return precioFinal;
    }

    public int getVacantes() {
        return vacantes;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setRecomendaciones(List<String> recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setVacantes(int vacantes) {
        this.vacantes = vacantes;
    }
}
