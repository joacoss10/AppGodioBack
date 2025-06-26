package com.example.recetarium.demo.Controller;

import com.example.recetarium.demo.DTOs.AlumnoRegistroRespondoDto;
import com.example.recetarium.demo.DTOs.AlumnoRequestDto;
import com.example.recetarium.demo.Service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @PostMapping
    public AlumnoRegistroRespondoDto crearAlumno(@RequestBody AlumnoRequestDto dto){

        return alumnoService.crearAlumno(dto);
    }
}
