package com.example.recetarium.demo.Model;

import com.example.recetarium.demo.Model.Enums.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class CronogramaCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCronograma;

    @ManyToOne
    @JoinColumn(name = "idCurso")
    private Curso curso;
    @ManyToOne
    @JoinColumn(name="idSede")
    private Sede sede;


    @Column
    private int vacantes;

    @Enumerated(EnumType.STRING)
    private DiaDeSemana dia;

    private int clasesDictadas;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public Long getId() {
        return idCronograma;
    }

    public Curso getCurso() {
        return curso;
    }

    public Long getIdCronograma() {
        return idCronograma;
    }

    public int getClasesDictadas() {
        return clasesDictadas;
    }

    public void setClasesDictadas(int clasesDictadas) {
        this.clasesDictadas = clasesDictadas;
    }

    public void setIdCronograma(Long idCronograma) {
        this.idCronograma = idCronograma;
    }

    public void setVacantes(int vacantes) {
        this.vacantes = vacantes;
    }

    public int getVacantes() {
        return vacantes;
    }

    public DiaDeSemana getDia() {
        return dia;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setId(Long id) {
        this.idCronograma = id;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }



    public void setDia(DiaDeSemana dia) {
        this.dia = dia;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}
