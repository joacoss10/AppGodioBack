package com.example.recetarium.demo.Model;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column
    private String nombre;
    private boolean habilitado;
    private String apellido;
    private String direccion;
    private int estadoVerificacion; //se mueve entre 0,1,2 y indica si se verifico o no el codigo que se le manda
    private String contrasenia;
    private boolean datos; //esto es para indicar si quiero o no usar los datos moviles
    @Column(unique = true)
    private String alias;
    private String mail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idAlumno",referencedColumnName = "idAlumno")
    private Alumno alumno;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idCodigoDeVerificacion",referencedColumnName = "idCodigoDeverificacion")
    private CodigoDeVerificacion codVerificacion;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<CodigoRecupero> codigos;
    //JOIN CON LA TABLA QUE CREA LAS RECETAS
    @OneToMany(mappedBy ="usuario",cascade = CascadeType.ALL)
    private List<Receta> recetasCreadas;
    //JOIN CON LA TABLA DE RECETAS FAVS
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<RecetaFavorito> recetaFavoritos;
    //JOIN CALIFIACION
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Calificacion> calificaciones;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public String getApellido() {
        return apellido;
    }

    public void setRecetasCreadas(List<Receta> recetasCreadas) {
        this.recetasCreadas = recetasCreadas;
    }

    public void setRecetaFavoritos(List<RecetaFavorito> recetaFavoritos) {
        this.recetaFavoritos = recetaFavoritos;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public List<Receta> getRecetasCreadas() {
        return recetasCreadas;
    }

    public List<RecetaFavorito> getRecetaFavoritos() {
        return recetaFavoritos;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getEstadoVerificacion() {
        return estadoVerificacion;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public boolean isDatos() {
        return datos;
    }

    public String getAlias() {
        return alias;
    }

    public String getMail() {
        return mail;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public CodigoDeVerificacion getCodVerificacion() {
        return codVerificacion;
    }

    public List<CodigoRecupero> getCodigos() {
        return codigos;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEstadoVerificacion(int estadoVerificacion) {
        this.estadoVerificacion = estadoVerificacion;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setDatos(boolean datos) {
        this.datos = datos;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public void setCodVerificacion(CodigoDeVerificacion codVerificacion) {
        this.codVerificacion = codVerificacion;
    }

    public void setCodigos(List<CodigoRecupero> codigos) {
        this.codigos = codigos;
    }

    public Long getIdReceta() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIdReceta'");
    }

    public String getDescripcionReceta() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDescripcionReceta'");
    }

    public String getNombreReceta() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNombreReceta'");
    }

    public byte[] getImagen() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getImagen'");
    }
}
