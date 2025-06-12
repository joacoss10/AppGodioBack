package com.example.recetarium.demo.Model;


import com.example.recetarium.demo.Model.Enums.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSede;

    @Column
    private String nombreSede;
    private String direccionSede;
    private String telefonoSede;
    private String mailSede;
    private String whatsApp;
    private float bonificacionCurso;
    private TipoPromocion tipoPromocion;

    public void setIdSede(Long idSede) {
        this.idSede = idSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public void setDireccionSede(String direccionSede) {
        this.direccionSede = direccionSede;
    }

    public void setTelefonoSede(String telefonoSede) {
        this.telefonoSede = telefonoSede;
    }

    public void setMailSede(String mailSede) {
        this.mailSede = mailSede;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }

    public void setBonificacionCursos(float bonificacionCursos) {
        this.bonificacionCurso = bonificacionCursos;
    }

    public void setTipoPromocion(TipoPromocion tipoPromocion) {
        this.tipoPromocion = tipoPromocion;
    }


    public Long getIdSede() {
        return idSede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public String getDireccionSede() {
        return direccionSede;
    }

    public String getTelefonoSede() {
        return telefonoSede;
    }

    public String getMailSede() {
        return mailSede;
    }

    public String getWhatsApp() {
        return whatsApp;
    }

    public float getBonificacionCursos() {
        return bonificacionCurso;
    }

    public TipoPromocion getTipoPromocion() {
        return tipoPromocion;
    }

    public void setBonificacionCurso(float bonificacionCurso) {
        this.bonificacionCurso = bonificacionCurso;
    }


}
