package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente,Long> {
    Optional<Ingrediente> findByNombre(String nombreIngrediente);
}
