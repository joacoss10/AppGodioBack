package com.example.recetarium.demo.Model;


import com.example.recetarium.demo.Model.Enums.ModalidadClase;
import jakarta.persistence.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;

    @Column
    private String descripcion;
    private String nombreCurso;
    @ElementCollection
    @Column (name="contenido")
    private List<String> contenidos;
    @ElementCollection
    @Column (name="requerimiento")
    private List<String> requerimientos;
    private int duracion;
    private float precio;
    private ModalidadClase modalidad;

    private Time hora;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<CronogramaCurso> cronogramaCurso;



    public Long getIdCurso() {
        return idCurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public List<String> getContenidos() {
        return contenidos;
    }

    public List<String> getRequerimientos() {
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



    public Time getHora() {
        return hora;
    }

    public List<CronogramaCurso> getCronogramaCurso() {
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



    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setCronogramaCurso(List<CronogramaCurso> cronogramaCurso) {
        this.cronogramaCurso = cronogramaCurso;
    }



    public void setContenidos(List<String> contenidos) {
        this.contenidos = contenidos;
    }

    public void setRequerimientos(List<String> requerimientos) {
        this.requerimientos = requerimientos;
    }


}
