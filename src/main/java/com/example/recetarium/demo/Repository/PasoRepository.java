package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Paso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasoRepository extends JpaRepository<Paso,Long> {
}
