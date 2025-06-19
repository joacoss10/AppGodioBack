package com.example.recetarium.demo.DTOs;

public class ComentarioResponseDto {
    private String aliasUsuario;
    private String comentario;

    public String getAliasUsuario() {
        return aliasUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setAliasUsuario(String aliasUsuario) {
        this.aliasUsuario = aliasUsuario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
