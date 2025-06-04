package com.example.recetarium.demo.Utiles;

import com.example.recetarium.demo.Service.CodigoVerificacioService;
import com.example.recetarium.demo.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Notificador {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    CodigoVerificacioService codigoVerificacioService;
    @Autowired
    private JavaMailSender mailSender;

    public void enviarMail(String mailUsuario,String alias){
        SimpleMailMessage mensaje=new SimpleMailMessage();
        Long id=usuarioService.getId(mailUsuario,alias);
        mensaje.setTo(mailUsuario);
        mensaje.setSubject("CODIGO VERIFICACION CUENTA");
        mensaje.setText("El codigo de verificacion de su cuenta es "+codigoVerificacioService.obtenerCodigo(id));
        mailSender.send(mensaje);
    }

}
