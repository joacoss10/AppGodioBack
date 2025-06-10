package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.tipoReceta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoRecetaRepository extends JpaRepository<tipoReceta, Long> {
    Optional<tipoReceta> findByDescripcion(String descripcion);
}
