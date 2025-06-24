package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.AsistenciaCurso;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsistenciaACursoRepository extends JpaRepository<AsistenciaCurso,Long> {

    @Query("""
    SELECT ac
    FROM AsistenciaCurso ac
    WHERE ac.alumno.idAlumno = :idAlumno
      AND ac.cronogramaCurso.fechaFin >= CURRENT_DATE
""")
    List<AsistenciaCurso> findByAlumno_IdAlumno(Long idAlumno);
    @Query("""
    SELECT COUNT(ac) > 0
    FROM AsistenciaCurso ac
    WHERE ac.alumno.idAlumno = :idAlumno
      AND ac.cronogramaCurso.curso.idCurso = :idCurso
""")
    boolean existeInscripcionACurso(@Param("idAlumno") Long idAlumno, @Param("idCurso") Long idCurso);
    @Query("""
    SELECT ac.asistencias
    FROM AsistenciaCurso ac
    WHERE ac.alumno.idAlumno = :idAlumno
      AND ac.cronogramaCurso.idCronograma = :idCronograma
""")
    Integer obtenerCantidadAsistencias(
            @Param("idAlumno") Long idAlumno,
            @Param("idCronograma") Long idCronograma);
    @Modifying
    @Query("""
    DELETE FROM AsistenciaCurso ac
    WHERE ac.alumno.idAlumno = :idAlumno
      AND ac.cronogramaCurso.idCronograma = :idCronograma
""")
    void deleteByAlumnoAndCronograma(@Param("idAlumno") Long idAlumno, @Param("idCronograma") Long idCronograma);

    @Query("""
    SELECT ac
    FROM AsistenciaCurso ac
    WHERE ac.alumno.idAlumno = :idAlumno
      AND ac.cronogramaCurso.fechaFin < CURRENT_DATE
    ORDER BY ac.cronogramaCurso.fechaFin DESC
""")
    List<AsistenciaCurso> findTop6ByAlumnoAndCursoFinalizado(@Param("idAlumno") Long idAlumno, Pageable pageable);


}
