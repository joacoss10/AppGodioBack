package com.example.recetarium.demo.Utiles;

import com.example.recetarium.demo.Model.Usuario;
import com.example.recetarium.demo.Service.CodigoVerificacioService;
import com.example.recetarium.demo.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

//coca?

public class Notificador {
    @Autowired
    UsuarioService serviceUsuario;
    @Autowired
    CodigoVerificacioService codigoVerificacioService;
    private JavaMailSender mailSender;
    private String mailUsuario;
    private Long idUsuario;


    public Notificador(String mailUsuario, Long idUsuario) {
        this.mailUsuario = mailUsuario;
        this.idUsuario=idUsuario;
    }

    public void enviarMail(){
        SimpleMailMessage mensaje=new SimpleMailMessage();
        mensaje.setTo(mailUsuario);
        mensaje.setSubject("CODIGO VERIFICACION CUENTA");
        mensaje.setText("El codigo de verificacion de su cuenta es "+codigoVerificacioService.obtenerCodigo(idUsuario));
    }

}
