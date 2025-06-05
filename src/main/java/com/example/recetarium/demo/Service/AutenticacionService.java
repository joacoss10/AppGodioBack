package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.DTOs.LogginRequestDto;
import com.example.recetarium.demo.DTOs.LogginResponseDto;
import com.example.recetarium.demo.Utiles.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    JwtUtil jwtUtil;
    public LogginResponseDto logginCheck(LogginRequestDto requestDto){
        LogginResponseDto response= usuarioService.credencialesCorrectas(requestDto);
        if(response.getCodigo()==200){
            String token=jwtUtil.generarToken(requestDto.aliasOMail);
            response.setToken(token);
        }
        return response;
    }
}