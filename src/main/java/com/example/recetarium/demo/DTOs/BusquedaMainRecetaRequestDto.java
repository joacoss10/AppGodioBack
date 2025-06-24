package com.example.recetarium.demo.DTOs;

import java.util.List;

public class BusquedaMainRecetaRequestDto {
    private String palabraClave;//valor de la lupa
    private boolean porAutor;//orden
    private boolean ordenAlfabetico;//orden
    private boolean ordenFecha;//orden
    private Long idUsuario;//traer en favs o no
    private FiltroIngredienteDto filtrosIngredientes;// excluir o incluir cierto/s ingrediente/s
    private Integer cantidadPersona;

    public Integer getCantidadPersona() {
        return cantidadPersona;
    }

    public void setCantidadPersona(Integer cantidadPersona) {
        this.cantidadPersona = cantidadPersona;
    }

    public FiltroIngredienteDto getFiltrosIngredientes() {
        return filtrosIngredientes;
    }

    public void setFiltrosIngredientes(FiltroIngredienteDto filtrosIngredientes) {
        this.filtrosIngredientes = filtrosIngredientes;
    }

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
