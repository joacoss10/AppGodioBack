package com.example.recetarium.demo.DTOs;

public class MedioDePagoRespondDto {
    private Long id;
    private String titular;
    private String numeroTarjeta;
    private String vencimiento;

    public Long getId() {
        return id;
    }

    public String getTitular() {
        return titular;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }
}
