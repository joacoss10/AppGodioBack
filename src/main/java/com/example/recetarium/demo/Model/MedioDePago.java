package com.example.recetarium.demo.Model;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class MedioDePago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMedioDePago;

    @Column
    private String numTarjeta;
    private String nombreTitular;
    private String vencimiento;
    private int codSeguridad;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "CuentaCorriente", referencedColumnName = "idCuentaCorriente")
    private CuentaCorriente cuentaCorriente;

    public void setIdMedioDePago(long idMedioDePago) {
        this.idMedioDePago = idMedioDePago;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public void setCodSeguridad(int codSeguridad) {
        this.codSeguridad = codSeguridad;
    }

    public long getIdMedioDePago() {
        return idMedioDePago;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public int getCodSeguridad() {
        return codSeguridad;
    }

    public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }
}
