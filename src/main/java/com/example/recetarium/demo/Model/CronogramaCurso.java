package com.example.recetarium.demo.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;

@Entity
public class CronogramaCurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCronograma;

    @Column
    private String diaSemana;
    private Date fechaInicio;
    private Date fechaFin;
    private int vacantesDisponibles;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="sede",referencedColumnName = "idSede")
    private Sede sede;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="curso",referencedColumnName = "idCurso")
    private Curso curso;

    @OneToMany(mappedBy ="cronogramaCurso",cascade = CascadeType.ALL)
    private ArrayList<AsistenciaCurso> asistenciaCursos;



}
