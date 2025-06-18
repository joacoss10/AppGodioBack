package com.example.recetarium.demo.DTOs;

public class MedioDePagoRequestDto {
    private Long idAlumno;
    private String numeroTarjeta;
    private String nombreTitular;
    private String vencimiento;
    private int codigoSeguridad;

    public MedioDePagoRequestDto(Long idAlumno, String numeroTarjeta, String nombreTitular, String vencimiento, int codigoSeguridad) {
        this.idAlumno = idAlumno;
        this.numeroTarjeta = numeroTarjeta;
        this.nombreTitular = nombreTitular;
        this.vencimiento = vencimiento;
        this.codigoSeguridad = codigoSeguridad;
    }

    public void setIdAlumno(Long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public void setCodigoSeguridad(int codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public Long getIdAlumno() {
        return idAlumno;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public int getCodigoSeguridad() {
        return codigoSeguridad;
    }
}
