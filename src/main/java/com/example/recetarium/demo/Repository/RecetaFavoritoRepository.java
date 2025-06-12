package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.RecetaFavorito;
import com.example.recetarium.demo.Model.Usuario;
import com.example.recetarium.demo.Model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecetaFavoritoRepository extends JpaRepository<RecetaFavorito, Long> {

    Optional<RecetaFavorito> findByUsuarioAndReceta(Usuario usuario, Receta receta);

    boolean existsByUsuarioAndReceta(Usuario usuario, Receta receta);

    void deleteByUsuarioAndReceta(Usuario usuario, Receta receta);
}
