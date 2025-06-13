package com.example.recetarium.demo.DTOs;

import com.example.recetarium.demo.Model.Enums.DiaDeSemana;

import java.sql.Time;

public class MisCursosPreviewRespondDto {
    private Long idCronograma;
    private String sede;
    private String nombreCurso;
    private Time hora;
    private DiaDeSemana dia;
    private int codigo; //900 mo hay cursos 200 todo ok

    public Long getIdCronograma() {
        return idCronograma;
    }

    public String getSede() {
        return sede;
    }

    public Time getHora() {
        return hora;
    }

    public DiaDeSemana getDia() {
        return dia;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public void setIdCronograma(Long idCronograma) {
        this.idCronograma = idCronograma;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setDia(DiaDeSemana dia) {
        this.dia = dia;
    }
}
