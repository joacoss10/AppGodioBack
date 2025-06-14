package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.CodigoRecupero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CodigoRecuperoRepository extends JpaRepository<CodigoRecupero,Long> {
    Optional<CodigoRecupero> findByUsuario_IdUsuario(Long idUsuario);
    Optional<CodigoRecupero> findByCodigo(int codigo);
    void deleteByUsuario_IdUsuario(Long idUsuario);

}
