package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Conversion;
import com.example.recetarium.demo.Model.Sede;
import com.example.recetarium.demo.Model.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface repoConversion extends JpaRepository<Conversion,Long> {
    Optional<Conversion> findByUnidadOrigenAndUnidadDestino(Unidad unidadOrigen, Unidad unidadDestino);

}
