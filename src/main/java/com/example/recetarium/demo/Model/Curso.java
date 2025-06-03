package com.example.recetarium.demo.Model;


import com.example.recetarium.demo.Model.Enums.ModalidadClase;
import jakarta.persistence.*;

import java.sql.Time;
import java.util.ArrayList;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;

    @Column
    private String descripcion;
    private String nombreCurso;
    private ArrayList<String> contenidos;
    private ArrayList<String> requerimientos;
    private int duracion;
    private float precio;
    private ModalidadClase modalidad;
    private int clasesDictadas;
    private Time hora;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private ArrayList<CronogramaCurso> cronogramaCurso;


    public Long getIdCurso() {
        return idCurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public ArrayList<String> getContenidos() {
        return contenidos;
    }

    public ArrayList<String> getRequerimientos() {
        return requerimientos;
    }

    public int getDuracion() {
        return duracion;
    }

    public float getPrecio() {
        return precio;
    }

    public ModalidadClase getModalidad() {
        return modalidad;
    }

    public int getClasesDictadas() {
        return clasesDictadas;
    }

    public Time getHora() {
        return hora;
    }

    public ArrayList<CronogramaCurso> getCronogramaCurso() {
        return cronogramaCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public void setContenidos(ArrayList<String> contenidos) {
        this.contenidos = contenidos;
    }

    public void setRequerimientos(ArrayList<String> requerimientos) {
        this.requerimientos = requerimientos;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setModalidad(ModalidadClase modalidad) {
        this.modalidad = modalidad;
    }

    public void setClasesDictadas(int clasesDictadas) {
        this.clasesDictadas = clasesDictadas;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setCronogramaCurso(ArrayList<CronogramaCurso> cronogramaCurso) {
        this.cronogramaCurso = cronogramaCurso;
    }
}
