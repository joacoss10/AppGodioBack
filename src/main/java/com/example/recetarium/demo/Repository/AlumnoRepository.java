package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    @Query("""
    SELECT c.saldo
    FROM Alumno a
    JOIN a.cuentaCorriente c
    WHERE a.idAlumno = :idAlumno
""")
    Float obtenerSaldoPorIdAlumno(@Param("idAlumno") Long idAlumno);


}
