package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Receta;
import com.example.recetarium.demo.Model.Usuario;
import com.example.recetarium.demo.Model.Enums.EstadoReceta;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {

    boolean existsByNombreRecetaAndUsuario_IdUsuario(String nombreReceta, Long idUsuario);

    Optional<Usuario> findByEstado(EstadoReceta aprobada);
}
