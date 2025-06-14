package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Receta;
import com.example.recetarium.demo.Model.RecetaFavorito;
import com.example.recetarium.demo.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoritoRepository extends JpaRepository<RecetaFavorito,Long> {
    boolean existsByUsuarioAndReceta(Usuario usuario, Receta receta);
    Optional<RecetaFavorito> findByUsuarioAndReceta(Usuario usuario,Receta receta);
}
