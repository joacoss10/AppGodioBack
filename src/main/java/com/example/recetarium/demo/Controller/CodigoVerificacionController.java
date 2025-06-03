package com.example.recetarium.demo.Controller;

import com.example.recetarium.demo.DTOs.CodigoVerificacionRequestDto;
import com.example.recetarium.demo.DTOs.CodigoVerificacionResponseDto;
import com.example.recetarium.demo.Service.CodigoVerificacioService;
import com.example.recetarium.demo.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuario/codeVerificacion")
public class CodigoVerificacionController {
  @Autowired
  private CodigoVerificacioService service;

  @PatchMapping
  public CodigoVerificacionResponseDto verificarCodigo(@RequestBody CodigoVerificacionRequestDto dto){
    return service.verificadorCodigo(dto);
  }

}
