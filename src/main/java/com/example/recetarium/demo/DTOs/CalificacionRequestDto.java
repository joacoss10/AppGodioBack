package com.example.recetarium.demo.DTOs;

public class CalificacionRequestDto {
    private Long idUsuario;
    private Long idReceta;
    private int calificacion;
    private String comentarios;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public Long getIdReceta() {
        return idReceta;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
