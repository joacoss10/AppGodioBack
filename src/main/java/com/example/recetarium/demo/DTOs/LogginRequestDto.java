package com.example.recetarium.demo.DTOs;

public class LogginRequestDto {
    public String aliasOMail;
    public String contrasenia;

    public LogginRequestDto(String aliasOMail, String contrasenia) {
        this.aliasOMail = aliasOMail;
        this.contrasenia = contrasenia;
    }

    public String getAliasOMail() {
        return aliasOMail;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setAliasOMail(String aliasOMail) {
        this.aliasOMail = aliasOMail;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
