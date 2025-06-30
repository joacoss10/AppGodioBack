package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Ingrediente;
import com.example.recetarium.demo.Model.Receta;
import com.example.recetarium.demo.Model.Utilizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilizadoRepository extends JpaRepository<Utilizado,Long> {
    void deleteByReceta(Receta receta);
    List<Utilizado> findByReceta(Receta receta);
    List<Utilizado> findByIngrediente(Ingrediente ingrediente);
}
