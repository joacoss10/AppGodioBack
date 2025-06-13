package com.example.recetarium.demo.Controller;

import com.example.recetarium.demo.DTOs.InscripcionRespondDto;
import com.example.recetarium.demo.Service.AsistenciaACursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/asistencia")
public class AsistenciaController {
    @Autowired
    AsistenciaACursoService service;

    @PostMapping("/inscripcion")
    public InscripcionRespondDto incribirseAlCurso(@RequestParam Long idCronograma, @RequestParam Long idAlumno){
        return service.incribirse(idCronograma,idAlumno);
    }
}
