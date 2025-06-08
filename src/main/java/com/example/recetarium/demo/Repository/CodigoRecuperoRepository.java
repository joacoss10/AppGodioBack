package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.CodigoRecupero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodigoRecuperoRepository extends JpaRepository<CodigoRecupero,Long> {
    Optional<CodigoRecupero> findByUsuario_IdUsuario(Long idUsuario);
    Optional<CodigoRecupero> findByCodigo(int codigo);

}
