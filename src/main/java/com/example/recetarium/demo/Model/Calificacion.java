package com.example.recetarium.demo.Model;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_receta"}))
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCalificacion;

    @Column
    private int calificacion;
    private String comentarios;

    @ManyToOne
    @JoinColumn(name="usuario",referencedColumnName="idUsuario")
    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @ManyToOne
    @JoinColumn(name="receta",referencedColumnName = "idReceta")
    private Receta receta;

    public void setIdCalificacion(Long idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Long getIdCalificacion() {
        return idCalificacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public Receta getReceta() {
        return receta;
    }
}
