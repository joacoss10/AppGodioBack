package com.example.recetarium.demo.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CuentaCorriente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuentaCorriente;

    @Column
    private float saldo;

    @OneToOne(mappedBy = "cuentaCorriente", cascade = CascadeType.ALL, orphanRemoval = true)
    private MedioDePago medioDePago;

    public void setIdCuentaCorriente(Long idCuentaCorriente) {
        this.idCuentaCorriente = idCuentaCorriente;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }




    public Long getIdCuentaCorriente() {
        return idCuentaCorriente;
    }

    public float getSaldo() {
        return saldo;
    }


    public void setMedioDePago(MedioDePago medioDePago) {
        this.medioDePago = medioDePago;
    }

    public MedioDePago getMedioDePago() {
        return medioDePago;
    }
}
