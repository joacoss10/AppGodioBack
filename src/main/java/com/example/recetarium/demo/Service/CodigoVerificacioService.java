package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.DTOs.CodigoVerificacionRequestDto;
import com.example.recetarium.demo.DTOs.CodigoVerificacionResponseDto;
import com.example.recetarium.demo.Model.CodigoDeVerificacion;
import com.example.recetarium.demo.Model.Usuario;
import com.example.recetarium.demo.Repository.CodigoVerificacion;
import com.example.recetarium.demo.Utiles.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class CodigoVerificacioService {
    @Lazy
    @Autowired
    Notificador notificador;
    @Autowired
    private CodigoVerificacion repositoryVerificacion;

    @Autowired
    private UsuarioService usuarioService;

    public CodigoDeVerificacion crearCodigoDeVerificacionCuenta(Usuario usuario){
        CodigoDeVerificacion codigo=new CodigoDeVerificacion();
        Random random=new Random();
        int numero = 10000 + random.nextInt(90000);


        LocalDateTime fechaYHoraActual = LocalDateTime.now();//tomo fecha y hora actual
        codigo.setFechaDeCreacion(fechaYHoraActual);
        codigo.setUsuario(usuario);
        codigo.setCodigoVerificacion(numero);
        repositoryVerificacion.save(codigo);
        return codigo;
    }
    public int obtenerCodigo(Long idUsuario){
        Optional<CodigoDeVerificacion>op=repositoryVerificacion.findByUsuario_IdUsuario(idUsuario);
        return op.get().getCodigoVerificacion();
    }
    public boolean vencido(Long idUsuario){
        Optional<CodigoDeVerificacion>op=repositoryVerificacion.findByUsuario_IdUsuario(idUsuario);
        LocalDateTime hora = LocalDateTime.now();
        LocalDateTime fecha= op.get().getFechaDeCreacion();
        Duration duracion = Duration.between(fecha, hora);
        boolean vencido=duracion.toHours()>24;
        if(vencido){
            usuarioService.cambiarEstadoVerificacion(0,idUsuario);
        }
        return vencido;
    }
    public CodigoVerificacionResponseDto verificadorCodigo(CodigoVerificacionRequestDto dto){
        CodigoVerificacionResponseDto responseDto=new CodigoVerificacionResponseDto();
        Long idUsuario=usuarioService.getId(dto.getMailUsuario(), dto.getAliasUsuario());
        Optional<CodigoDeVerificacion> codigoDeVerificacion=repositoryVerificacion.findByUsuario_IdUsuario(idUsuario);
        if(codigoDeVerificacion.get().getCodigoVerificacion()== dto.getCodigo()){
            responseDto.setCodigo(200);
            String contrasenia=generadorDeContrasenias();
            usuarioService.cambiarEstadoVerificacion(1,idUsuario);
            usuarioService.guardarContraseniaa(dto.getMailUsuario(),dto.getAliasUsuario(),contrasenia);
            notificador.enviarContrasenia(dto.getMailUsuario(), contrasenia);
        }
        else{
            responseDto.setCodigo(400);//INDICA QUE EL CODIGO ES INCORRECTO
        }
        return  responseDto;
    }
    private String generadorDeContrasenias(){
        String mayus = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String minus = "abcdefghijklmnopqrstuvwxyz";
        String numeros = "0123456789";
        String simbolos = "@#_";

        String todos = mayus + minus + numeros + simbolos;

        SecureRandom random = new SecureRandom();
        StringBuilder contrasenia = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(todos.length());
            contrasenia.append(todos.charAt(index));
        }
        return contrasenia.toString();
    }

}
