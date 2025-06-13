package com.example.recetarium.demo.DTOs;

public class CursoPreviewRespondDto {
    private Long idCronograma;
    private Long idCurso;
    private String titulo;
    private String descripcion;



    public CursoPreviewRespondDto(Long id, String titulo, String descripcion,Long idCurso) {
        this.idCronograma = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idCurso=idCurso;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public Long getIdCronograma() {
        return idCronograma;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
