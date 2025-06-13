package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.CronogramaCurso;
import com.example.recetarium.demo.Model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CronogramaCursoRepository extends JpaRepository<CronogramaCurso, Long> {

    @Query("""
    SELECT cc FROM CronogramaCurso cc
    WHERE cc.vacantes > 0 AND cc.fechaInicio > CURRENT_DATE
""")
    Page<CronogramaCurso> findCronogramasDisponibles(Pageable pageable);

    @Query("SELECT DISTINCT cc FROM CronogramaCurso cc WHERE cc.sede.id = :idSede")
    Page<CronogramaCurso> findDistinctCursosBySede(@Param("idSede") Long idSede, Pageable pageable);


}
