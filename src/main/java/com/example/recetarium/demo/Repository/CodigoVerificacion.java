package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.CodigoDeVerificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CodigoVerificacion extends JpaRepository<CodigoDeVerificacion,Long> {
    Optional<CodigoDeVerificacion> findByUsuario_IdUsuario(Long idUsuario);

}
