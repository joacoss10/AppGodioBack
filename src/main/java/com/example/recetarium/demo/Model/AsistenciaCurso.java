package com.example.recetarium.demo.Model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;

@Entity
public class AsistenciaCurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsistencia;

    @Column
    private Date fecha;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cronograma",referencedColumnName = "idCronograma")
    private CronogramaCurso cronogramaCurso;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="alumno",referencedColumnName = "idAlumno")
    private Alumno alumno;
}
