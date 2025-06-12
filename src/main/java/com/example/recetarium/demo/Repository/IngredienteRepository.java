package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredienteRepository extends JpaRepository<Ingrediente,Long> {
    Optional<Ingrediente> findByNombre(String nombreIngrediente);
}
