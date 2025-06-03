package com.example.recetarium.demo.DTOs;

public class UsuarioRequestDto {
    private String nombre;
    private String apellido;
    private String direccion;
    private String alias;
    private String mail;
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioRequestDto(String nombre, String apellido, String direccion,String mail, String alias) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.mail=mail;
        this.alias=alias;
    }

    public Long getId() {
        return id;
    }

    public UsuarioRequestDto() {}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getAlias() {
        return alias;
    }

    public String getMail() {
        return mail;
    }
}
