package com.example.recetarium.demo.Controller;

import com.example.recetarium.demo.DTOs.CursoPreviewRespondDto;
import com.example.recetarium.demo.DTOs.InformacionSedeRespondDto;
import com.example.recetarium.demo.Service.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sede")
public class SedeController {
    @Autowired
    SedeService service;

    @GetMapping("/informacion")
    public InformacionSedeRespondDto obtenerInformacionSede(@RequestParam Long idSede){
        return service.obtenerInfoSede(idSede);
    }
    @GetMapping("/cursos")
    public ResponseEntity<Page<CursoPreviewRespondDto>> obtenerCursos (@RequestParam Long idSede,
                                                                       @RequestParam(defaultValue = "0")int page,
                                                                       @RequestParam(defaultValue ="10" )int size){
        return ResponseEntity.ok(service.obtenerCursoPorSede(idSede,page,size));
    }
}
