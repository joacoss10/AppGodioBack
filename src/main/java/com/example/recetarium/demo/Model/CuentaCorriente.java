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

    @OneToMany(mappedBy = "cuentaCorriente", cascade = CascadeType.ALL)
    private List<MedioDePago> medioDePago;

    public void setIdCuentaCorriente(Long idCuentaCorriente) {
        this.idCuentaCorriente = idCuentaCorriente;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }


    public void setMedioDePago(List<MedioDePago> medioDePago) {
        this.medioDePago = medioDePago;
    }

    public Long getIdCuentaCorriente() {
        return idCuentaCorriente;
    }

    public float getSaldo() {
        return saldo;
    }


    public List<MedioDePago> getMedioDePago() {
        return medioDePago;
    }
}
