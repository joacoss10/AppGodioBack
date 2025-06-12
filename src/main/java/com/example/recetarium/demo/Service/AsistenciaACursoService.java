package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.DTOs.InscripcionRespondDto;
import com.example.recetarium.demo.Model.Alumno;
import com.example.recetarium.demo.Model.AsistenciaCurso;
import com.example.recetarium.demo.Model.CronogramaCurso;
import com.example.recetarium.demo.Repository.AlumnoRepository;
import com.example.recetarium.demo.Repository.AsistenciaACursoRepository;
import com.example.recetarium.demo.Repository.CronogramaCursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AsistenciaACursoService {
    @Autowired
    AsistenciaACursoRepository asistenciaACursoRepository;
    @Autowired
    CronogramaCursoRepository cronogramaCursoRepository;
    @Autowired
    AlumnoRepository alumnoRepository;
    @Transactional
    public InscripcionRespondDto incribirse(Long idCronograma, Long idAlumno){
        InscripcionRespondDto respond=new InscripcionRespondDto();
        if(asistenciaACursoRepository.findByCronogramaCurso_IdCronogramaAndAlumno_IdAlumno(idCronograma,idAlumno).isEmpty()){
            AsistenciaCurso asistenciaCurso=new AsistenciaCurso();
            Optional<Alumno> alumno=alumnoRepository.findById(idAlumno);
            Optional<CronogramaCurso> cronograma=cronogramaCursoRepository.findById(idCronograma);
            asistenciaCurso.setAlumno(alumno.get());
            asistenciaCurso.setCronogramaCurso(cronograma.get());
            CronogramaCurso cronogramaCurso=cronograma.get();
            cronogramaCurso.setVacantes(cronogramaCurso.getVacantes()-1);
            asistenciaACursoRepository.save(asistenciaCurso);
            respond.setCodigo(200);
        }else {
            respond.setCodigo(900);
        }
        return respond;
    }
}
