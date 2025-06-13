package com.example.recetarium.demo.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class FotoReceta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFotoReceta;
    @Lob
    @Column
    private byte[] foto;
    private boolean principal;
    @OneToOne
    @JoinColumn(name = "Receta",referencedColumnName = "idReceta")
    private Receta receta;

    public void setIdFotoReceta(Long idFotoReceta) {
        this.idFotoReceta = idFotoReceta;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Long getIdFotoReceta() {
        return idFotoReceta;
    }

    public byte[] getFoto() {
        return foto;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public Receta getReceta() {
        return receta;
    }
}
