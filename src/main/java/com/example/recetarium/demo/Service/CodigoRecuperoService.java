package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.DTOs.CodigoRecuperoRequestDto;
import com.example.recetarium.demo.DTOs.CodigoRecuperoResponseDto;
import com.example.recetarium.demo.Model.CodigoDeVerificacion;
import com.example.recetarium.demo.Model.CodigoRecupero;
import com.example.recetarium.demo.Model.Usuario;
import com.example.recetarium.demo.Repository.CodigoRecuperoRepository;

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
public class CodigoRecuperoService {
    @Lazy
    @Autowired
    Notificador notificador;

    @Autowired
    private CodigoRecuperoRepository repositoryCodigoRecupero;

    @Autowired
    private UsuarioService usuarioService;

    public CodigoRecuperoResponseDto VerificarYCrearCodigoRecupero(CodigoRecuperoRequestDto aliasOmail){
            Optional<Usuario> usuario=usuarioService.aliasOmailRecupero(aliasOmail);
            CodigoRecuperoResponseDto response=new CodigoRecuperoResponseDto();
            if(!usuario.isEmpty()){//Verifica que el mail exista
                GenerarCodigoRecu(usuario.get());//genera el codigo y manda el mail
                response.setCodigo(200);//exitoso
            }
            else{//NO EXISTE USUARIO CON ESE MAIL
                response.setCodigo(400);//mal
            }
            return response;
    }

    public CodigoRecuperoResponseDto verificadorCodigo(CodigoRecuperoRequestDto dto){
        //Optional<CodigoRecupero> codRecu2= repositoryCodigoRecupero.findByUsuario_IdUsuario(usuario.get().getIdUsuario());
        CodigoRecuperoResponseDto response=new CodigoRecuperoResponseDto();
        Optional<Usuario> usuario1=usuarioService.aliasOmailRecupero(dto);//obtengo el usuario por mail
        Optional<CodigoRecupero> codRecu= repositoryCodigoRecupero.findByCodigo(dto.getCodigo());//busco el objeto recu por el codigo
        Usuario usuario2=codRecu.get().getUsuario();//obtengo el usuario por el codigo que mando

        if((usuario1.get().getIdUsuario()==usuario2.getIdUsuario())&&!vencido(codRecu.get().getFechaDeCreacion())){//un re quilombo
            String contrasenia=generadorDeContrasenias();
            usuarioService.guardarContraseniaa(usuario1.get().getMail(),usuario1.get().getAlias(),contrasenia);
            response.setCodigo(200);
            notificador.enviarContrasenia(usuario1.get().getMail(), contrasenia);
        }
        else{
            response.setCodigo(400);//CODIGO INCORRECTO o FUERA DE TIEMPO
        }
        return response;
    }

    ///////////////////////privados paaaaaaaaaaaaaaaaaaaaaaaaaa//////////////////////////////////
    private void GenerarCodigoRecu(Usuario usuario){
        CodigoRecupero codigoRecupero=new CodigoRecupero();
        Random random=new Random();

        int numero = 10000 + random.nextInt(90000);
        LocalDateTime fechaYHoraActual = LocalDateTime.now();//tomo fecha y hora actual

        codigoRecupero.setCodigo(numero);
        codigoRecupero.setUsuario(usuario);
        codigoRecupero.setFechaDeCreacion(fechaYHoraActual);
        repositoryCodigoRecupero.save(codigoRecupero);//crea codigo en la bd

        notificador.enviarMailCodigoRecupero(usuario.getMail(),codigoRecupero.getCodigo());//manda mail
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

    public boolean vencido(LocalDateTime fechaCodigo){
        LocalDateTime hora = LocalDateTime.now();
        Duration duracion = Duration.between(fechaCodigo, hora);
        boolean vencido=duracion.toMinutes()>30;
        return vencido;
    }
}
