package com.example.recetarium.demo.Model;

import jakarta.persistence.*;

@Entity
public class Conversion {
    public void setIdConversion(Long idConversion) {
        this.idConversion = idConversion;
    }

    public void setFactorConversiones(float factorConversiones) {
        this.factorConversiones = factorConversiones;
    }

    public void setUnidadOrigen(Unidad unidadOrigen) {
        this.unidadOrigen = unidadOrigen;
    }

    public void setUnidadDestino(Unidad unidadDestino) {
        this.unidadDestino = unidadDestino;
    }

    public Long getIdConversion() {
        return idConversion;
    }

    public float getFactorConversiones() {
        return factorConversiones;
    }

    public Unidad getUnidadOrigen() {
        return unidadOrigen;
    }

    public Unidad getUnidadDestino() {
        return unidadDestino;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConversion;

    @Column
    private float factorConversiones;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UnidadOrigen",referencedColumnName = "idUnidad" )
    private Unidad unidadOrigen;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UnidadDestino",referencedColumnName = "idUnidad" )
    private Unidad unidadDestino;



}
