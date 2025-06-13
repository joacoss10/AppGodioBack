package com.example.recetarium.demo.DTOs;

public class InformacionSedeRespondDto {
    private String ubicacion;
    private String telefono;
    private String wpp;
    private String mail;

    public String getUbicacion() {
        return ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getWpp() {
        return wpp;
    }

    public String getMail() {
        return mail;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setWpp(String wpp) {
        this.wpp = wpp;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
