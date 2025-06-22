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
    Page<Receta> findByUsuario_IdUsuarioOrderByFechaCreacionDesc(Long idUsuario, Pageable pageable);
////////////////////////////////////FILTROS DE BSUQUEDA///////////////////////////////////////////////////
    ///////MIS RECETAS////////
@Query("""
    SELECT r FROM Receta r
    WHERE r.usuario.idUsuario = :idUsuario
      AND LOWER(r.nombreReceta) LIKE LOWER(CONCAT('%', :nombre, '%'))
    ORDER BY r.fechaCreacion DESC
""")
Page<Receta> buscarMisRecetasPorNombre(
        @Param("idUsuario") Long idUsuario,
        @Param("nombre") String nombre,
        Pageable pageable
);
///////MAIN///////////////////////////////////////
    //POR NOMBRE RECETA
    @Query("""
    SELECT r FROM Receta r
    WHERE LOWER(r.nombreReceta) LIKE LOWER(CONCAT('%', :palabraClave, '%'))
      AND r.estado = com.example.recetarium.demo.Model.Enums.EstadoReceta.Validada
""")
    Page<Receta> buscarRecetasPorNombreRecetaFiltroMain(
            @Param("palabraClave") String palabraClave,
            Pageable pageable
    );
///////////////////////////////////////////////


}
