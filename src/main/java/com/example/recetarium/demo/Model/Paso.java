package com.example.recetarium.demo.Model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Paso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaso;
    @Column
    private String texto;
    private int numeroDePaso;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Receta",referencedColumnName = "idReceta")
    private Receta receta;
    @OneToMany(mappedBy = "paso",cascade = CascadeType.ALL)
    private Multimedia multimedias;

    public void setIdPaso(Long idPaso) {
        this.idPaso = idPaso;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setNumeroDePaso(int numeroDePaso) {
        this.numeroDePaso = numeroDePaso;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public void setMultimedias(Multimedia multimedias) {
        this.multimedias = multimedias;
    }

    public Long getIdPaso() {
        return idPaso;
    }

    public String getTexto() {
        return texto;
    }

    public int getNumeroDePaso() {
        return numeroDePaso;
    }

    public Receta getReceta() {
        return receta;
    }

    public Multimedia getMultimedias() {
        return multimedias;
    }
}
