package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.DTOs.CodigoRecuperoRequestDto;
import com.example.recetarium.demo.DTOs.CodigoRecuperoResponseDto;
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
            Optional<Usuario> usuario=usuarioService.getUsuarioPorMailOAlias(aliasOmail.getAliasOMail(),aliasOmail.getAliasOMail());
            CodigoRecuperoResponseDto response=new CodigoRecuperoResponseDto();
            if(usuario.isPresent()){//Verifica que el mail exista
                Optional<CodigoRecupero> codigoRecupero=repositoryCodigoRecupero.findByUsuario_IdUsuario(usuario.get().getIdUsuario());
                if(codigoRecupero.isPresent()){//comprueba que no tenga un codigo ya asociado
                    if(vencido(codigoRecupero.get().getFechaDeCreacion())){//si tiene un codigo asigando vencido se crea uno nuevo
                        repositoryCodigoRecupero.deleteByUsuario_IdUsuario(usuario.get().getIdUsuario());
                        generarCodigoRecu(usuario.get());//genera el codigo nuevo y manda el mail
                    }else{//se manda el codigo asociado existente
                        notificador.enviarMailCodigoRecupero(usuario.get().getMail(),codigoRecupero.get().getCodigo());
                    }
                }else{
                    generarCodigoRecu(usuario.get());

                }
                //GENERA CODIGO POR PRIMERA VEZ
                response.setCodigo(200);//exitoso
            }
            else{//NO EXISTE USUARIO CON ESE MAIL
                response.setCodigo(400);//mal
            }
            return response;
    }

    public CodigoRecuperoResponseDto verificadorCodigo(CodigoRecuperoRequestDto dto) {
        Optional<Usuario> user=usuarioService.getUsuarioPorMailOAlias(dto.getAliasOMail(),dto.getAliasOMail());
        Long idUsuario = user.get().getIdUsuario();
        Optional<CodigoRecupero> codigoRecupero = repositoryCodigoRecupero.findByUsuario_IdUsuario(idUsuario);
        CodigoRecuperoResponseDto response = new CodigoRecuperoResponseDto();

        if (codigoRecupero.get().getCodigo() == dto.getCodigo()) {
            response.setCodigo(200);//TODO GOD
            String contraseniaNueva=generadorDeContrasenias();
            usuarioService.guardarContraseniaa(user.get().getMail(),user.get().getAlias(),contraseniaNueva);
            notificador.enviarContrasenia(user.get().getMail(), contraseniaNueva);

        } else if (vencido(codigoRecupero.get().getFechaDeCreacion())) {
            response.setCodigo(601);//FUERA DE TIEMPO
        } else {
            response.setCodigo(400); //CODIGO INCORRECTO
        }
        return response;
    }

    ///////////////////////privados paaaaaaaaaaaaaaaaaaaaaaaaaa//////////////////////////////////
    private void generarCodigoRecu(Usuario usuario){
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
