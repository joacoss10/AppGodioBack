package com.example.recetarium.demo.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class CodigoRecupero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCodioRecupero;
    @Column(nullable = false)
    private LocalDateTime fechaDeCreacion;
    @Column (unique = true)
    private  int codigo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idUsuario")
    private Usuario usuario;

    public void setIdCodioRecupero(Long idCodioRecupero) {
        this.idCodioRecupero = idCodioRecupero;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getIdCodioRecupero() {
        return idCodioRecupero;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public int getCodigo() {
        return codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
