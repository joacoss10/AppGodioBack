package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.TipoReceta; // Corregí el nombre si la clase existe como 'tipoReceta'
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRecetaRepository extends JpaRepository<TipoReceta, Long> {
}
