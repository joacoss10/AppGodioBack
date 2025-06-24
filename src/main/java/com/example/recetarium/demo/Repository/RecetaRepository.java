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
           //SIN CANTIDAD//
    //POR NOMBRE RECETA SEGUN ORDEN (orden y nombre es opcional)//
    @Query("""
    SELECT r FROM Receta r
    WHERE LOWER(r.nombreReceta) LIKE LOWER(CONCAT('%', :palabraClave, '%'))
      AND r.estado = com.example.recetarium.demo.Model.Enums.EstadoReceta.Validada
""")
    Page<Receta> buscarRecetasPorNombreRecetaFiltroMain(
            @Param("palabraClave") String palabraClave,
            Pageable pageable
    );
    //POR NOMBRE RECETA SEGUN FILTRO (ingrediente y cantidad) Y/O ORDEN (filtro es obligatorio, nombre y orden son opcionales)//
    //filtro: ingrediente (incluye) y cantidad
    @Query("""
        SELECT r FROM Receta r
        JOIN r.utilizado ir
        JOIN ir.ingrediente i
        JOIN ir.unidad uOrigen
        JOIN Conversion c ON c.unidadOrigen = uOrigen
        JOIN Unidad uDestino ON c.unidadDestino = uDestino
        WHERE LOWER(r.nombreReceta) LIKE LOWER(CONCAT('%', :palabraClave, '%'))
        AND r.estado = com.example.recetarium.demo.Model.Enums.EstadoReceta.Validada
        AND LOWER(i.nombre) = LOWER(:nombreIngrediente)
        AND LOWER(uDestino.descripcion) = LOWER(:unidadDeseada)
        AND (ir.cantidad * c.factorConversiones) <= :cantidadMaxima + 0.0001
    """)
    Page<Receta> buscarRecetasConFiltroIngredienteConConversion(
            @Param("palabraClave") String palabraClave,
            @Param("nombreIngrediente") String nombreIngrediente,
            @Param("unidadDeseada") String unidadDeseada,
            @Param("cantidadMaxima") Double cantidadMaxima,
            Pageable pageable
    );
    //
    //filtro:ingrediente (incluye)
    @Query("""
        SELECT r FROM Receta r
        JOIN r.utilizado u
        JOIN u.ingrediente i
        WHERE LOWER(r.nombreReceta) LIKE LOWER(CONCAT('%', :palabraClave, '%'))
          AND LOWER(i.nombre) = LOWER(:nombreIngrediente)
          AND r.estado = com.example.recetarium.demo.Model.Enums.EstadoReceta.Validada
    """)
    Page<Receta> buscarRecetasPorIngredienteSinCantidad(
            @Param("palabraClave") String palabraClave,
            @Param("nombreIngrediente") String nombreIngrediente,
            Pageable pageable
    );

    //
    //filtro: sin ingrediente (lo exluye)
    @Query("""
        SELECT r FROM Receta r
        WHERE LOWER(r.nombreReceta) LIKE LOWER(CONCAT('%', :palabraClave, '%'))
        AND r.estado = com.example.recetarium.demo.Model.Enums.EstadoReceta.Validada
        AND r.idReceta NOT IN (
            SELECT u.receta.idReceta FROM Utilizado u
            JOIN u.ingrediente i
            WHERE LOWER(i.nombre) = LOWER(:nombreIngrediente)
        )
    """)
    Page<Receta> buscarRecetasExcluyendoIngrediente(
            @Param("palabraClave") String palabraClave,
            @Param("nombreIngrediente") String nombreIngrediente,
            Pageable pageable
    );
    ////////////////////////////////////////
    //CON CANTIDAD//
    //POR NOMBRE RECETA SEGUN ORDEN (orden y nombre es opcional) + cantidad de personas//
    @Query("""
        SELECT r FROM Receta r
        WHERE LOWER(r.nombreReceta) LIKE LOWER(CONCAT('%', :palabraClave, '%'))
          AND r.estado = com.example.recetarium.demo.Model.Enums.EstadoReceta.Validada
          AND r.cantidadPersonas = :cantidadPersonas
    """)
    Page<Receta> buscarRecetasPorNombreRecetaFiltroMainPersonas(
            @Param("palabraClave") String palabraClave,
            @Param("cantidadPersonas") Integer cantidadPersonas,
            Pageable pageable
    );

    //POR NOMBRE RECETA SEGUN FILTRO (ingrediente y cantidad) Y/O ORDEN (filtro es obligatorio, nombre y orden son opcionales) + cantidad de personas//
    //filtro: ingrediente (incluye) y cantidad
    @Query("""
        SELECT r FROM Receta r
        JOIN r.utilizado ir
        JOIN ir.ingrediente i
        JOIN ir.unidad uOrigen
        JOIN Conversion c ON c.unidadOrigen = uOrigen
        JOIN Unidad uDestino ON c.unidadDestino = uDestino
        WHERE LOWER(r.nombreReceta) LIKE LOWER(CONCAT('%', :palabraClave, '%'))
          AND r.estado = com.example.recetarium.demo.Model.Enums.EstadoReceta.Validada
          AND LOWER(i.nombre) = LOWER(:nombreIngrediente)
          AND LOWER(uDestino.descripcion) = LOWER(:unidadDeseada)
          AND (ir.cantidad * c.factorConversiones) <= :cantidadMaxima + 0.0001
          AND r.cantidadPersonas = :cantidadPersonas
    """)
    Page<Receta> buscarRecetasConFiltroIngredienteConConversionPersona(
            @Param("palabraClave") String palabraClave,
            @Param("nombreIngrediente") String nombreIngrediente,
            @Param("unidadDeseada") String unidadDeseada,
            @Param("cantidadMaxima") Double cantidadMaxima,
            @Param("cantidadPersonas") Integer cantidadPersonas,
            Pageable pageable
    );
    //
    //filtro:ingrediente (incluye), sin unidad + cantidad de personas
    @Query("""
    SELECT r FROM Receta r
    JOIN r.utilizado u
    JOIN u.ingrediente i
    WHERE LOWER(r.nombreReceta) LIKE LOWER(CONCAT('%', :palabraClave, '%'))
      AND LOWER(i.nombre) = LOWER(:nombreIngrediente)
      AND r.estado = com.example.recetarium.demo.Model.Enums.EstadoReceta.Validada
      AND r.cantidadPersonas = :cantidadPersonas
""")
    Page<Receta> buscarRecetasPorIngredienteSinCantidadPersona(
            @Param("palabraClave") String palabraClave,
            @Param("nombreIngrediente") String nombreIngrediente,
            @Param("cantidadPersonas") Integer cantidadPersonas,
            Pageable pageable
    );

    //
    //filtro: sin ingrediente (lo exluye)+ cantidad de personas
    @Query("""
        SELECT r FROM Receta r
        WHERE LOWER(r.nombreReceta) LIKE LOWER(CONCAT('%', :palabraClave, '%'))
          AND r.estado = com.example.recetarium.demo.Model.Enums.EstadoReceta.Validada
          AND r.cantidadPersonas = :cantidadPersonas
          AND r.idReceta NOT IN (
              SELECT u.receta.idReceta FROM Utilizado u
              JOIN u.ingrediente i
              WHERE LOWER(i.nombre) = LOWER(:nombreIngrediente)
          )
    """)
    Page<Receta> buscarRecetasExcluyendoIngredientePersona(
            @Param("palabraClave") String palabraClave,
            @Param("nombreIngrediente") String nombreIngrediente,
            @Param("cantidadPersonas") Integer cantidadPersonas,
            Pageable pageable
    );
///////////////////////////////////////////////


}
