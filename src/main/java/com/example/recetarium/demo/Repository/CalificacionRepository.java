package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Calificacion;
import com.example.recetarium.demo.Model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion,Long> {
    List<Calificacion> findByReceta(Receta receta);
}
