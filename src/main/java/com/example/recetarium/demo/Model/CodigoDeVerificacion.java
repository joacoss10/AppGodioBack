package com.example.recetarium.demo.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class CodigoDeVerificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCodigoDeverificacion;
    @Column(nullable = false)
    private LocalDateTime fechaDeCreacion;
    @Column(unique = true)
    private int codigoVerificacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idUsuario",referencedColumnName = "idUsuario")
    private Usuario usuario;

    public void setIdCodigoDeverificacion(Long idCodigoDeverificacion) {
        this.idCodigoDeverificacion = idCodigoDeverificacion;
    }

    public int getCodigoVerificacion() {
        return codigoVerificacion;
    }

    public void setCodigoVerificacion(int codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getIdCodigoDeverificacion() {
        return idCodigoDeverificacion;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
