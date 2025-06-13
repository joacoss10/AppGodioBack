package com.example.recetarium.demo.Model;

import jakarta.persistence.*;

@Entity
public class Multimedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMultimedia;
    @Column
    private String tipoDeContenido;
    private String extencion;
    @Lob
    @Column
    private byte[] contenido;

    public byte[] getContenido() {
        return contenido;
    }

    public void setIdMultimedia(Long idMultimedia) {
        this.idMultimedia = idMultimedia;
    }

    public void setTipoDeContenido(String tipoDeContenido) {
        this.tipoDeContenido = tipoDeContenido;
    }

    public void setExtencion(String extencion) {
        this.extencion = extencion;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public void setPaso(Paso paso) {
        this.paso = paso;
    }

    @OneToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "Paso",referencedColumnName = "idPaso")
    private Paso paso;

    public Long getIdMultimedia() {
        return idMultimedia;
    }

    public String getTipoDeContenido() {
        return tipoDeContenido;
    }

    public String getExtencion() {
        return extencion;
    }

    public Paso getPaso() {
        return paso;
    }
}
