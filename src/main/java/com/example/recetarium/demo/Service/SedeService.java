package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.DTOs.CursoPreviewRespondDto;
import com.example.recetarium.demo.DTOs.InformacionSedeRespondDto;
import com.example.recetarium.demo.DTOs.SedePreviewRespondDto;
import com.example.recetarium.demo.Model.CronogramaCurso;
import com.example.recetarium.demo.Model.Curso;
import com.example.recetarium.demo.Model.Sede;
import com.example.recetarium.demo.Repository.CronogramaCursoRepository;
import com.example.recetarium.demo.Repository.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SedeService {
    @Autowired
    SedeRepository sedeRepository;
    @Autowired
    CronogramaCursoRepository cronogramaCursoRepository;
    public InformacionSedeRespondDto obtenerInfoSede(Long idSede){
        Optional<Sede> sede =sedeRepository.findById(idSede);
        InformacionSedeRespondDto respondDto=new InformacionSedeRespondDto();

        respondDto.setMail(sede.get().getMailSede());
        respondDto.setTelefono(sede.get().getTelefonoSede());
        respondDto.setUbicacion(sede.get().getDireccionSede());
        respondDto.setWpp(sede.get().getWhatsApp());

        return respondDto;
    }
    public Page<CursoPreviewRespondDto>obtenerCursoPorSede(Long idSede,int page, int size){
        Pageable pageable= PageRequest.of(page,size);
        return cronogramaCursoRepository.findDistinctCursosBySede(idSede,pageable)
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
    public Page<SedePreviewRespondDto> obtenerPreviews(String palabra, int page, int size){
        Pageable pageable=PageRequest.of(page,size);
        Page<Sede> sedes;
        if(palabra!=null && !palabra.trim().isEmpty()){
            sedes=sedeRepository.findByNombreSedeContainingIgnoreCase(palabra,pageable);
        }else{
            sedes=sedeRepository.findAll(pageable);
        }
        return sedes.map(this::mapToPreviewSede);
    }
    private SedePreviewRespondDto mapToPreviewSede(Sede sedeencontrada){
        return new SedePreviewRespondDto(
                sedeencontrada.getIdSede(),
                sedeencontrada.getNombreSede(),
                sedeencontrada.getDireccionSede()
        );
    }
}
