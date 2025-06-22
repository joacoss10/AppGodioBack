package com.example.recetarium.demo.DTOs;

public class BusquedaMainRecetaRequestDto {
    private String palabraClave;//valor de la lupa
    private boolean porAutor;//orden
    private boolean ordenAlfabetico;//orden
    private boolean ordenFecha;//orden
    private Long idUsuario;

    public String getPalabraClave() {
        return palabraClave;
    }

    public boolean isPorAutor() {
        return porAutor;
    }

    public boolean isOrdenAlfabetico() {
        return ordenAlfabetico;
    }

    public boolean isOrdenFecha() {
        return ordenFecha;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setPalabraClave(String palabraClave) {
        this.palabraClave = palabraClave;
    }

    public void setPorAutor(boolean porAutor) {
        this.porAutor = porAutor;
    }

    public void setOrdenAlfabetico(boolean ordenAlfabetico) {
        this.ordenAlfabetico = ordenAlfabetico;
    }

    public void setOrdenFecha(boolean ordenFecha) {
        this.ordenFecha = ordenFecha;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
