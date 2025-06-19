package com.example.recetarium.demo.DTOs;

import java.util.List;

public class RecetaDetalleResponseDto {
    private String nombreReceta;
    private String descripcionReceta;
    private String porciones;
    private int cantidadPersonas;
    private String aliasCreador;
    private byte[] imagenPrincipal;
    private List<byte[]> imagenesAdicionales;
    private List<PasoResponseDto> pasos;
    private List<IngredienteUsadoResponseDto> ingredientesUsados;
    private List<ComentarioResponseDto> comentarios;
    private double promedioCalificacion;
}
