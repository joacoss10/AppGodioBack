package com.example.recetarium.demo.Model;

import jakarta.persistence.*;

@Entity
public class Utilizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilizado;

    @Column
    private int cantidad;
    private String observaciones;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="receta",referencedColumnName = "idReceta")
    private Receta receta;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="unidad",referencedColumnName = "idUnidad")
    private Unidad unidad;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ingrediente",referencedColumnName = "idIngrediente")
    private Ingrediente ingrediente;

    public void setIngrediente(Ingrediente ingrediente2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIngrediente'");
    }

    public void setCantidad(String cantidad2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCantidad'");
    }

    public void setReceta(Receta receta2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setReceta'");
    }
}
