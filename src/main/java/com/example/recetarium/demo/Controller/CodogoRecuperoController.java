package com.example.recetarium.demo.Controller;


import com.example.recetarium.demo.DTOs.CodigoRecuperoRequestDto;
import com.example.recetarium.demo.DTOs.CodigoRecuperoResponseDto;
import com.example.recetarium.demo.DTOs.CodigoVerificacionRequestDto;
import com.example.recetarium.demo.DTOs.CodigoVerificacionResponseDto;
import com.example.recetarium.demo.Service.CodigoRecuperoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth/usuario/codeRecupero")
public class CodogoRecuperoController {

    @Autowired
    CodigoRecuperoService service;

    @PostMapping
    public CodigoRecuperoResponseDto generarCodigoRecupero(@RequestBody CodigoRecuperoRequestDto dto){
       return service.VerificarYCrearCodigoRecupero(dto);
    }
    @PatchMapping
    public CodigoRecuperoResponseDto verificarCodigo(@RequestBody CodigoRecuperoRequestDto dto){
        return service.verificadorCodigo(dto);
    }
}
