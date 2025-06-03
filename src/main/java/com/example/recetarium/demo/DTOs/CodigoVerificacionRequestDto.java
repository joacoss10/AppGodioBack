package com.example.recetarium.demo.DTOs;

public class CodigoVerificacionRequestDto {
    private String mailUsuario;
    private String AliasUsuario;
    private int codigo;

    public CodigoVerificacionRequestDto(String mailUsuario, String aliasUsuari, int codigo) {
        this.mailUsuario = mailUsuario;
        AliasUsuario = aliasUsuari;
        this.codigo = codigo;
    }

    public String getMailUsuario() {
        return mailUsuario;
    }

    public String getAliasUsuario() {
        return AliasUsuario;
    }

    public int getCodigo() {
        return codigo;
    }
}
