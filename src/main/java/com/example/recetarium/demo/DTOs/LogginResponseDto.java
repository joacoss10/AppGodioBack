package com.example.recetarium.demo.DTOs;

public class LogginResponseDto {
    private String token;
    private int codigo;
    private Long alumno;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public Long getAlumno() {
        return alumno;
    }

    public void setAlumno(Long alumno) {
        this.alumno = alumno;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }


}
