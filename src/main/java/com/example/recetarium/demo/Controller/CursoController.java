package com.example.recetarium.demo.Controller;

import com.example.recetarium.demo.DTOs.ContenidoCursoRespondDto;
import com.example.recetarium.demo.DTOs.CursoPreviewRespondDto;
import com.example.recetarium.demo.DTOs.InfoGeneralCursoRespondDto;
import com.example.recetarium.demo.Service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    CursoService cursoService;

    @GetMapping("/preview")
    public ResponseEntity<Page<CursoPreviewRespondDto>> obtenerPreviews(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<CursoPreviewRespondDto> previews=cursoService.obtenerPreviews(PageRequest.of(page,size));
        return ResponseEntity.ok(previews);
    }
    @GetMapping("/infogeneral")
    public InfoGeneralCursoRespondDto getInformacionGeneral(@RequestParam Long idCronograma){
      return cursoService.obtenerInfoGeneral(idCronograma);
    }
    @GetMapping("/contenidos")
    public ContenidoCursoRespondDto getContenido(@RequestParam Long idCurso){
        return cursoService.obtenerContenidoCurso(idCurso);
    }
}
