package com.example.recetarium.demo.DTOs;

public class MisCursosInformacionRespondDto {
    private Long idCurso;
    private String nombreDelCurso;
    private int porcentaje;
    private int clasesDictadas;
    private int asistencias;
    private String descripcion;

    public Long getIdCurso() {
        return idCurso;
    }

    public String getNombreDelCurso() {
        return nombreDelCurso;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public int getClasesDictadas() {
        return clasesDictadas;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public void setNombreDelCurso(String nombreDelCurso) {
        this.nombreDelCurso = nombreDelCurso;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public void setClasesDictadas(int clasesDictadas) {
        this.clasesDictadas = clasesDictadas;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
