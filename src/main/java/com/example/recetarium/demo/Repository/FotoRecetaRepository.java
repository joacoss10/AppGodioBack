package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.FotoReceta;
import com.example.recetarium.demo.Model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRecetaRepository extends JpaRepository<FotoReceta,Long> {
    void deleteByReceta(Receta receta);
}
