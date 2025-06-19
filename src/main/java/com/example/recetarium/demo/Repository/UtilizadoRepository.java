package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Receta;
import com.example.recetarium.demo.Model.Utilizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilizadoRepository extends JpaRepository<Utilizado,Long> {
    void deleteByReceta(Receta receta);
}
