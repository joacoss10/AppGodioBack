package com.example.recetarium.demo.Model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"alumno", "cronograma"})
        }
)
public class AsistenciaCurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsistencia;

    @Column
    private int asistencias;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cronograma",referencedColumnName = "idCronograma")
    private CronogramaCurso cronogramaCurso;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="alumno",referencedColumnName = "idAlumno")
    private Alumno alumno;

    public void setIdAsistencia(Long idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public void setCronogramaCurso(CronogramaCurso cronogramaCurso) {
        this.cronogramaCurso = cronogramaCurso;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Long getIdAsistencia() {
        return idAsistencia;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public CronogramaCurso getCronogramaCurso() {
        return cronogramaCurso;
    }

    public Alumno getAlumno() {
        return alumno;
    }
}
