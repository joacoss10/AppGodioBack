package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnidadRepository extends JpaRepository<Unidad,Long> {
    Optional<Unidad> findByDescripcion(String descripcion);
}
