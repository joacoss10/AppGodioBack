package com.example.recetarium.demo.DTOs;

public class LogginResponseDto {
    private String token;
    private int codigo;
    private boolean alumno;


    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public boolean isAlumno() {
        return alumno;
    }

    public void setAlumno(boolean alumno) {
        this.alumno = alumno;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }


}
