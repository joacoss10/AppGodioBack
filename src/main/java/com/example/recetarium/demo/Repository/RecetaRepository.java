package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Enums.EstadoReceta;
import com.example.recetarium.demo.Model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {
    @Query("""
        SELECT r FROM Receta r
        WHERE r.estado = com.example.recetarium.demo.Model.Enums.EstadoReceta.Validada
        ORDER BY r.fechaCreacion DESC
    """)
    Page<Receta> findRecetasPublicadas(Pageable pageable);
}
