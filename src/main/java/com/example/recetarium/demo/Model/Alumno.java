package com.example.recetarium.demo.Model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlumno;

    @Lob
    @Column
    private byte[] dniFrente;
    private byte[] dniFondo;
    @Column
    private int nroTramite;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="usuario",referencedColumnName = "idUsuario")
    private Usuario usuario;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cuentaCorriente",referencedColumnName = "idCuentaCorriente")
    private CuentaCorriente cuentaCorriente;

    @OneToMany(mappedBy ="alumno",cascade = CascadeType.ALL)
    private ArrayList<AsistenciaCurso> asistenciaCursos;

    public Long getIdAlumno() {
        return idAlumno;
    }

    public byte[] getDniFrente() {
        return dniFrente;
    }

    public byte[] getDniFondo() {
        return dniFondo;
    }

    public int getNroTramite() {
        return nroTramite;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public void setAsistenciaCursos(ArrayList<AsistenciaCurso> asistenciaCursos) {
        this.asistenciaCursos = asistenciaCursos;
    }

    public CuentaCorriente getCuentaCorriente() {
        return cuentaCorriente;
    }

    public ArrayList<AsistenciaCurso> getAsistenciaCursos() {
        return asistenciaCursos;
    }

    public void setIdAlumno(Long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public void setDniFrente(byte[] dniFrente) {
        this.dniFrente = dniFrente;
    }

    public void setDniFondo(byte[] dniFondo) {
        this.dniFondo = dniFondo;
    }

    public void setNroTramite(int nroTramite) {
        this.nroTramite = nroTramite;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}
