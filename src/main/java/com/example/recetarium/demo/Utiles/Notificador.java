package com.example.recetarium.demo.Utiles;

import com.example.recetarium.demo.Model.Usuario;
import com.example.recetarium.demo.Service.CodigoVerificacioService;
import com.example.recetarium.demo.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Async;

@Component
public class Notificador {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    CodigoVerificacioService codigoVerificacioService;
    @Autowired
    private JavaMailSender mailSender;
    @Async
    public void enviarMailCodigoVerificacion(String mailUsuario, String alias){
        SimpleMailMessage mensaje=new SimpleMailMessage();
        Long id=usuarioService.getId(mailUsuario,alias);
        mensaje.setTo(mailUsuario);
        mensaje.setSubject("CODIGO VERIFICACION CUENTA");
        mensaje.setText("El codigo de verificacion de su cuenta es: "+codigoVerificacioService.obtenerCodigo(id));
        mailSender.send(mensaje);
    }
    @Async
    public void enviarMailCodigoRecupero (String mailUsuario,int codigo){
        SimpleMailMessage mensaje=new SimpleMailMessage();
        mensaje.setTo(mailUsuario);
        mensaje.setSubject("CODIGO DE RECUPERO DE CUENTA");
        mensaje.setText("El codigo de recupero de su cuenta es: "+ codigo);
        mailSender.send(mensaje);
    }
    @Async
    public void enviarContrasenia(String mail,String contra){
        SimpleMailMessage mensaje=new SimpleMailMessage();
        mensaje.setTo(mail);
        mensaje.setSubject("Contraseña Recetarium");
        mensaje.setText("Su contraseña de Recetarium es: "+contra);
        mailSender.send(mensaje);
    }

}
