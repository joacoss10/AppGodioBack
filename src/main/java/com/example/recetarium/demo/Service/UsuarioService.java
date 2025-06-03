package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.DTOs.UsuarioRequestDto;
import com.example.recetarium.demo.DTOs.UsuarioResponseDto;
import com.example.recetarium.demo.Model.Usuario;
import com.example.recetarium.demo.Repository.UsuarioRepository;
import com.example.recetarium.demo.Utiles.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    private CodigoVerificacioService codigoVerificacioService;
    public UsuarioResponseDto crearUsuario(UsuarioRequestDto requestDto){//Creacion de usurio solo por MAIL Y ALIAS
        Usuario usuario=new Usuario();
        UsuarioResponseDto response=new UsuarioResponseDto();
        Optional<Usuario> op=repository.findByMailOrAlias(requestDto.getMail(),requestDto.getAlias());

        if(op.isEmpty()){//si no existe ni mail ni alias lo guardo
            usuario.setAlias(requestDto.getAlias());
            usuario.setMail(requestDto.getMail());
            usuario.setEstadoVerificacion(2);//SETEA QUE EL CODIGO ESTA PENDIENTE DE VERIFICAR
            repository.save(usuario);


            //CREACION DEL CODIGO DE VERIFICACION DE LA CUENTA
            codigoVerificacioService.crearCodigoDeVerificacionCuenta(usuario);
            Notificador notificador=new Notificador(op.get().getMail(),op.get().getIdUsuario());

        }else{//VERIFICO CUAL ES EL CAMPO REPETIDO
            if(op.get().getEstadoVerificacion()==2 && !codigoVerificacioService.vencido(op.get().getIdUsuario())){
               response.setCodigo(910); //sigfinica que puede ir a verificar codigo
            }
            else{if(op.get().getEstadoVerificacion()==0){//SE LE VENCIO EL PLAZO DE VERIFICACION
                response.setCodigo(900);// significa que el codigo vencio y hay que noticiarle
            }
            else{//CUENTA VERIFICADA POR LO QUE EL USUARIO ESTA INGRESANDO CON DATOS ERRONEOS
                if(op.get().getAlias().equals(requestDto.getAlias())){//COMPARO SI EL ALIAS ES IGUAL AL QUE INGRESO, EL ALIAS ES EL REPETIDO
                    response.setAlias(op.get().getAlias());
                }
                else{//LO INVERSO AL ANTERIOR, EL REPETIDO ES EL MAIL
                    response.setMail(op.get().getMail());
                }
                response.setCodigo(409);//codigo de datos repetidos, SE ENCARGA EL FRONT
            }
            }
        }
        return response;
    }
    public void datosUsuario(UsuarioRequestDto requestDto){//Actualizo datos de usuario (nombre, apellido, direccion) y lo guardo.Se actualiza por ID
        Optional<Usuario> user=repository.findByMailOrAlias(requestDto.getMail(),requestDto.getAlias());
        Usuario usuario=new Usuario();
        usuario.setApellido(requestDto.getApellido());
        usuario.setNombre(requestDto.getNombre());
        usuario.setDireccion(requestDto.getDireccion());
        usuario.setIdUsuario(user.get().getIdUsuario());
        repository.save(usuario);
    }
    public void cambiarEstadoVerificacion(int codigo,Long idUsuario){
       Optional <Usuario> usuario=repository.findById(idUsuario);
       usuario.get().setEstadoVerificacion(codigo);
       if(codigo==1){
           usuario.get().setHabilitado(true);
       }
       repository.save(usuario.get());
    }
    public Long getId(String mail, String alias){
        Optional<Usuario> user=repository.findByMailOrAlias(mail,alias);
        return user.get().getIdUsuario();
    }

}
