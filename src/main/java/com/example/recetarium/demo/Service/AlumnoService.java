package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.DTOs.AlumnoRequestDto;
import com.example.recetarium.demo.Model.Alumno;
import com.example.recetarium.demo.Model.CuentaCorriente;
import com.example.recetarium.demo.Model.MedioDePago;
import com.example.recetarium.demo.Model.Usuario;
import com.example.recetarium.demo.Repository.AlumnoRepository;
import com.example.recetarium.demo.Repository.UsuarioRepository;
import com.example.recetarium.demo.Utiles.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlumnoService {
    @Autowired
    private AlumnoRepository repository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CuentaCorrienteService cuentaCorrienteService;
    @Autowired
    private MedioDePagoService medioDePagoService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void crearAlumno(AlumnoRequestDto requestDto){
        JwtUtil jwtUtil=new JwtUtil();
        Alumno alumno=new Alumno();
        String alias=jwtUtil.getUserName(requestDto.getToken());
        Optional<Usuario> usuario=usuarioService.getUsuarioPorMailOAlias(alias,alias);
        alumno.setDniFondo(requestDto.getDniFondo());
        alumno.setDniFrente(requestDto.getDniFrente());
        alumno.setNroTramite(requestDto.getNumeroTramite());

        CuentaCorriente cuentaCorriente=new CuentaCorriente();
        cuentaCorriente.setSaldo(0);
        alumno.setCuentaCorriente(cuentaCorriente);
        MedioDePago medioDePago=new MedioDePago();

        medioDePago.setCodSeguridad(requestDto.getCodigo());
        medioDePago.setNombreTitular(requestDto.getTitular());
        medioDePago.setNumTarjeta(requestDto.getNumeroTarjeta());
        medioDePago.setVencimiento(requestDto.getVencimiento());
        medioDePago.setCuentaCorriente(cuentaCorriente);

        usuario.get().setAlumno(alumno);
        usuarioRepository.save(usuario.get());
        cuentaCorrienteService.guardarCuentaCorriente(cuentaCorriente);
        medioDePagoService.guardarMedioDePago(medioDePago);
    }



}
