package com.example.recetarium.demo.DTOs;

public class PasoRequestDto {
    private int numeroPaso;
    private String texto;
    private byte[] imagen; // puede ser null o vacío

    public int getNumeroPaso() {
        return numeroPaso;
    }

    public String getTexto() {
        return texto;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setNumeroPaso(int numeroPaso) {
        this.numeroPaso = numeroPaso;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
