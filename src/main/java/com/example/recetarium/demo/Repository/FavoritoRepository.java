package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Receta;
import com.example.recetarium.demo.Model.RecetaFavorito;
import com.example.recetarium.demo.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritoRepository extends JpaRepository<RecetaFavorito,Long> {
    boolean existsByUsuarioAndReceta(Usuario usuario, Receta receta);
    Optional<RecetaFavorito> findByUsuarioAndReceta(Usuario usuario,Receta receta);
    @Query("""
    SELECT rf FROM RecetaFavorito rf
    WHERE rf.usuario.idUsuario = :idUsuario
    ORDER BY rf.receta.fechaCreacion DESC
""")
    Page<RecetaFavorito> findFavoritosOrdenadosPorFecha(@Param("idUsuario") Long idUsuario, Pageable pageable);

}
