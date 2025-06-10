package com.example.recetarium.demo.DTOs;

import java.util.Date;

public class AlumnoRequestDto {
    private String token;
    private byte[] dniFrente;
    private byte[] dniFondo;
    private String numeroTramite;
    private String numeroTarjeta;
    private int Codigo;
    private String vencimiento;
    private String titular;

    public AlumnoRequestDto(String token, byte[] dniFrente, byte[] dniFondo, String numeroTramite, String numeroTarjeta, int codigo, String vencimiento, String titular) {
        this.token = token;
        this.dniFrente = dniFrente;
        this.dniFondo = dniFondo;
        this.numeroTramite = numeroTramite;
        this.numeroTarjeta = numeroTarjeta;
        Codigo = codigo;
        this.vencimiento = vencimiento;
        this.titular = titular;
    }

    public String getToken() {
        return token;
    }

    public byte[] getDniFrente() {
        return dniFrente;
    }

    public byte[] getDniFondo() {
        return dniFondo;
    }

    public String getNumeroTramite() {
        return numeroTramite;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public int getCodigo() {
        return Codigo;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public String getTitular() {
        return titular;
    }
}
