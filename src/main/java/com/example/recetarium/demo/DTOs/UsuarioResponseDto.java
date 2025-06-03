package com.example.recetarium.demo.DTOs;

public class UsuarioResponseDto {
    private String contrasenia;
    private String alias;
    private String mail;
    private Long id;
    private int codigo;

    public String getContrasenia() {
        return contrasenia;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public String getMail() {
        return mail;
    }

    public Long getId() {
        return id;
    }

    public UsuarioResponseDto() {
    }
}
