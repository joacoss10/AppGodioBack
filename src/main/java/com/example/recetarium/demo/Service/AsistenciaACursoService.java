package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.DTOs.InscripcionRespondDto;
import com.example.recetarium.demo.DTOs.MisCursosInformacionRespondDto;
import com.example.recetarium.demo.DTOs.MisCursosPreviewRespondDto;
import com.example.recetarium.demo.Model.*;
import com.example.recetarium.demo.Model.Enums.ModalidadClase;
import com.example.recetarium.demo.Repository.AlumnoRepository;
import com.example.recetarium.demo.Repository.AsistenciaACursoRepository;
import com.example.recetarium.demo.Repository.CronogramaCursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        if(estaIncriptoAlCurso(idAlumno,idCronograma)) {
            respond.setCodigo(900);
        }else{
            AsistenciaCurso asistenciaCurso=new AsistenciaCurso();
            Optional<Alumno> alumno=alumnoRepository.findById(idAlumno);
            Optional<CronogramaCurso> cronograma=cronogramaCursoRepository.findById(idCronograma);
            asistenciaCurso.setAlumno(alumno.get());
            asistenciaCurso.setCronogramaCurso(cronograma.get());
            CronogramaCurso cronogramaCurso=cronograma.get();
            cronogramaCurso.setVacantes(cronogramaCurso.getVacantes()-1);
            asistenciaACursoRepository.save(asistenciaCurso);
            respond.setCodigo(200);
        }

        return respond;
    }
    @Transactional
    public List<MisCursosPreviewRespondDto> obtenerCursosAlumno(Long idAlumno){
       List<AsistenciaCurso> asistenciaCursos= asistenciaACursoRepository.findByAlumno_IdAlumno(idAlumno);
       List<MisCursosPreviewRespondDto> respond = new ArrayList<>();
       if (!asistenciaCursos.isEmpty()) {
           for (AsistenciaCurso asis : asistenciaCursos) {
               CronogramaCurso cronograma = asis.getCronogramaCurso();
               Curso curso = cronograma.getCurso();
               Sede sede = cronograma.getSede();

               MisCursosPreviewRespondDto dto = new MisCursosPreviewRespondDto();
               dto.setIdCronograma(cronograma.getIdCronograma());
               dto.setDia(cronograma.getDia());
               String ubicacion;
               if (curso.getModalidad() == ModalidadClase.Virtual) {
                   ubicacion = "Virtual";
               } else {
                   ubicacion = sede.getDireccionSede();
               }
               dto.setHora(curso.getHora());
               dto.setSede(ubicacion);
               dto.setNombreCurso(curso.getNombreCurso());
               dto.setCodigo(200);
               respond.add(dto);
           }
       }else{
           MisCursosPreviewRespondDto dto=new MisCursosPreviewRespondDto();
           dto.setCodigo(900);
           respond.add(dto);
       }
        return respond;
    }
    public int getAsistencia(Long idAlumno, Long idCronograma){
        return asistenciaACursoRepository.obtenerCantidadAsistencias(idAlumno,idCronograma);
    }
    @Transactional
    public void baja(Long idAlumno,Long idCronograma){
        asistenciaACursoRepository.deleteByAlumnoAndCronograma(idAlumno,idCronograma);
        Optional<CronogramaCurso> cronogramaCurso=cronogramaCursoRepository.findById(idCronograma);
        CronogramaCurso cronogramaActualizado=cronogramaCurso.get();
        cronogramaActualizado.setVacantes(cronogramaActualizado.getVacantes()+1);
        cronogramaCursoRepository.save(cronogramaActualizado);
    }

    private boolean estaIncriptoAlCurso(Long idAlumno, Long idCronograma){
        Optional<CronogramaCurso> catedra=cronogramaCursoRepository.findById(idCronograma);
        Long idCurso=catedra.get().getCurso().getIdCurso();
       return asistenciaACursoRepository.existeInscripcionACurso(idAlumno,idCurso); //chquea que no se quiera meter dos veces al mismo curso
    }
}

