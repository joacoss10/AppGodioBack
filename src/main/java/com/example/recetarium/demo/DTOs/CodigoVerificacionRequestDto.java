package com.example.recetarium.demo.DTOs;

public class CodigoVerificacionRequestDto {
    private String mailUsuario;
    private String aliasUsuario;
    private int codigo;

    public CodigoVerificacionRequestDto(String mailUsuario, String aliasUsuario, int codigo) {
        this.mailUsuario = mailUsuario;
        this.aliasUsuario = aliasUsuario;
        this.codigo = codigo;
    }

    public String getMailUsuario() {
        return mailUsuario;
    }

    public String getAliasUsuario() {
        return aliasUsuario;
    }

    public int getCodigo() {
        return codigo;
    }
}
