package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Paso;
import com.example.recetarium.demo.Model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasoRepository extends JpaRepository<Paso,Long> {
    void deleteByReceta(Receta receta);
}
