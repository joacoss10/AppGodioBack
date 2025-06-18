package com.example.recetarium.demo.DTOs;

public class SedePreviewRespondDto {
    private Long idSede;
    private String nombreSede;
    private String ubicacion;

    public SedePreviewRespondDto(Long idSede, String nombreSede, String ubicacion) {
        this.idSede = idSede;
        this.nombreSede = nombreSede;
        this.ubicacion = ubicacion;
    }

    public Long getIdSede() {
        return idSede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setIdSede(Long idSede) {
        this.idSede = idSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
