package com.example.recetarium.demo.Controller;

import com.example.recetarium.demo.DTOs.UsuarioRequestDto;
import com.example.recetarium.demo.DTOs.UsuarioResponseDto;
import com.example.recetarium.demo.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;
    @PostMapping
    public UsuarioResponseDto crearUsuario(@RequestParam String mail, @RequestParam String alias){
        UsuarioRequestDto request=new UsuarioRequestDto();
        request.setAlias(alias);
        request.setMail(mail);
        return service.crearUsuario(request);
    }
    @PatchMapping
    public void actualiarUsuario(@RequestBody UsuarioRequestDto requestDto){
        service.datosUsuario(requestDto);
    }

}
