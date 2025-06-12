package com.example.recetarium.demo.DTOs;

public class CursoPreviewRespondDto {
    private Long idCurso;
    private String titulo;
    private String descripcion;
    private Long idSede;

    public CursoPreviewRespondDto(Long id, String titulo, String descripcion,Long idSede) {
        this.idCurso = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idSede=idSede;
    }

    public Long getIdSede() {
        return idSede;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
