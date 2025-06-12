package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.AsistenciaCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AsistenciaACursoRepository extends JpaRepository<AsistenciaCurso,Long> {
    Optional<AsistenciaCurso> findByCronogramaCurso_IdCronogramaAndAlumno_IdAlumno(Long idCronograma, Long idAlumno);
}
