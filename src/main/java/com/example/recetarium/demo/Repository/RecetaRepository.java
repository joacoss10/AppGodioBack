package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
}
