package com.example.recetarium.demo.DTOs;
import java.util.List;

public class RecetaDto {
    public String nombreReceta;
    public String descripcionReceta;
    public String porciones;
    public int cantidadPersonas;
    public Long idTipoReceta;
    public List<String> pasos;
    public List<byte[]> fotos;
    public byte[] imagenPrincipal;

    public List<IngredienteCantidadDTO> ingredientes;

    public static class IngredienteCantidadDTO {
        public Long idIngrediente;
        public String cantidad;
    }
}
