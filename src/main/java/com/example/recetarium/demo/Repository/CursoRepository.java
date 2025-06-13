package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CursoRepository extends JpaRepository<Curso,Long> {
}
