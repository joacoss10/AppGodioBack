package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.DTOs.*;
import com.example.recetarium.demo.Model.CronogramaCurso;
import com.example.recetarium.demo.Model.Curso;
import com.example.recetarium.demo.Model.Enums.ModalidadClase;
import com.example.recetarium.demo.Model.Sede;
import com.example.recetarium.demo.Repository.CronogramaCursoRepository;
import com.example.recetarium.demo.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    CronogramaCursoRepository cronogramaCursoRepository;
    @Autowired
    CursoRepository cursoRepository;
    @Autowired
    AsistenciaACursoService asistenciaACursoService;



    public Page<CursoPreviewRespondDto> obtenerPreviews(Pageable pageable, String palabra, String filtro){
        Sort sort= Sort.unsorted();
        if("fecha".equalsIgnoreCase(filtro)){
            sort=Sort.by(Sort.Direction.ASC, "fechaInicio");
        }else if("alfabeticamente".equalsIgnoreCase(filtro)){
            sort=Sort.by(Sort.Direction.ASC,"curso.nombreCurso");
        }
        Pageable sortedPageable= PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sort);
        if (palabra!=null && !palabra.trim().isEmpty()){
            return cronogramaCursoRepository.buscarDisponiblesPorNombreCurso(palabra.toLowerCase(),sortedPageable)
                    .map(this::mapToPreviewDto);
        }else{
            return cronogramaCursoRepository.findCronogramasDisponibles(sortedPageable)
                    .map(this::mapToPreviewDto);
        }

    }
    private CursoPreviewRespondDto mapToPreviewDto(CronogramaCurso cronogramaCurso) {
        Curso curso=cronogramaCurso.getCurso();
        return new CursoPreviewRespondDto(
                cronogramaCurso.getId(),
                curso.getNombreCurso(),
                curso.getDescripcion(),
                curso.getIdCurso()

        );
    }
    public InfoGeneralCursoRespondDto obtenerInfoGeneral(Long idCronograma){
        InfoGeneralCursoRespondDto respondDto=new InfoGeneralCursoRespondDto();
        Optional<CronogramaCurso> cronograma=cronogramaCursoRepository.findById(idCronograma);
        Curso curso=cronograma.get().getCurso();
        Sede sede=cronograma.get().getSede();

        String ubicacion;
        if(curso.getModalidad()== ModalidadClase.Virtual){
            ubicacion="Virtual";
        }else ubicacion=sede.getDireccionSede();

        float valorCurso=Math.round(curso.getPrecio());
        float descuento=Math.round(sede.getBonificacionCursos()*100);
        float precioFinal=Math.round(valorCurso-(valorCurso*sede.getBonificacionCursos()));

        respondDto.setDescripcion(curso.getDescripcion());
        respondDto.setUbicacion(ubicacion);
        respondDto.setPrecioFinal(precioFinal);
        respondDto.setRecomendaciones(curso.getRequerimientos());
        respondDto.setDuracion(curso.getDuracion());
        respondDto.setHora(curso.getHora());
        respondDto.setFechaInicio(cronograma.get().getFechaInicio());
        respondDto.setFechaFin(cronograma.get().getFechaFin());
        respondDto.setVacantes(cronograma.get().getVacantes());
        respondDto.setBonificacion(descuento);
        respondDto.setPrecio(curso.getPrecio());
        respondDto.setTitulo(curso.getNombreCurso());
        respondDto.setIdCronograma(cronograma.get().getIdCronograma());
        respondDto.setIdCurso(curso.getIdCurso());

        return respondDto;
    }
    public MisCursosInformacionRespondDto obtenerInformacionMiCurso(Long idAlumno,Long idCronograma){
        MisCursosInformacionRespondDto respond=new MisCursosInformacionRespondDto();
        Optional<CronogramaCurso> cronograma=cronogramaCursoRepository.findById(idCronograma);

        Curso curso=cronograma.get().getCurso();
        int asistencia=asistenciaACursoService.getAsistencia(idAlumno,idCronograma);
        int clasesDictadas=cronograma.get().getClasesDictadas();
        int porcentaje=100;
        if(asistencia!=0){
            porcentaje= clasesDictadas/(asistencia*100);
        }

        respond.setIdCurso(curso.getIdCurso());
        respond.setAsistencias(asistencia);
        respond.setClasesDictadas(clasesDictadas);
        respond.setPorcentaje(porcentaje);
        respond.setDescripcion(curso.getDescripcion());
        respond.setNombreDelCurso(curso.getNombreCurso());

        return respond;
    }
    public ContenidoCursoRespondDto obtenerContenidoCurso(Long idCurso){
        ContenidoCursoRespondDto respond=new ContenidoCursoRespondDto();
        Optional<Curso> curso=cursoRepository.findById(idCurso);
        respond.setTemas(curso.get().getContenidos());
        return respond;
    }
    public MisCursosPracticasRespondDto obtenerRequerimientosCurso(Long idCurso){
        MisCursosPracticasRespondDto respond=new MisCursosPracticasRespondDto();
        Optional<Curso>curso=cursoRepository.findById(idCurso);
        respond.setRecomendaciones(curso.get().getRequerimientos());
        return respond;
    }
}
