package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.DTOs.ContenidoCursoRespondDto;
import com.example.recetarium.demo.DTOs.CursoPreviewRespondDto;
import com.example.recetarium.demo.DTOs.InfoGeneralCursoRespondDto;
import com.example.recetarium.demo.Model.CronogramaCurso;
import com.example.recetarium.demo.Model.Curso;
import com.example.recetarium.demo.Model.Enums.ModalidadClase;
import com.example.recetarium.demo.Model.Sede;
import com.example.recetarium.demo.Repository.CronogramaCursoRepository;
import com.example.recetarium.demo.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    CronogramaCursoRepository cronogramaCursoRepository;
    @Autowired
    CursoRepository cursoRepository;



    public Page<CursoPreviewRespondDto> obtenerPreviews(Pageable pageable){
        return cronogramaCursoRepository.findCronogramasDisponibles(pageable)
                .map(this::mapToPreviewDto);
    }
    private CursoPreviewRespondDto mapToPreviewDto(CronogramaCurso cronogramaCurso) {
        Curso curso=cronogramaCurso.getCurso();
        return new CursoPreviewRespondDto(
                curso.getIdCurso(),
                curso.getNombreCurso(),
                curso.getDescripcion(),
                cronogramaCurso.getSede().getIdSede()
        );
    }
    public InfoGeneralCursoRespondDto obtenerInfoGeneral(Long idCurso, Long idSede){
        InfoGeneralCursoRespondDto respondDto=new InfoGeneralCursoRespondDto();
        Optional<CronogramaCurso> cronograma=cronogramaCursoRepository.findByCurso_IdCursoAndSede_IdSede(idCurso,idSede);
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

        return respondDto;
    }
    public ContenidoCursoRespondDto obtenerContenidoCurso(Long idCurso){
        ContenidoCursoRespondDto respond=new ContenidoCursoRespondDto();
        Optional<Curso> curso=cursoRepository.findById(idCurso);
        respond.setTemas(curso.get().getContenidos());
        return respond;
    }
}
