package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UnidadRepository extends JpaRepository<Unidad,Long> {
    Optional<Unidad> findByDescripcion(String descripcion);
}
