package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.DTOs.AlumnoRequestDto;
import com.example.recetarium.demo.DTOs.CuentaCorrienteRespondDto;
import com.example.recetarium.demo.DTOs.MedioDePagoRequestDto;
import com.example.recetarium.demo.DTOs.MedioDePagoRespondDto;
import com.example.recetarium.demo.Model.Alumno;
import com.example.recetarium.demo.Model.CuentaCorriente;
import com.example.recetarium.demo.Model.MedioDePago;
import com.example.recetarium.demo.Model.Usuario;
import com.example.recetarium.demo.Repository.AlumnoRepository;
import com.example.recetarium.demo.Repository.CuentaCorrienteRepository;
import com.example.recetarium.demo.Repository.MedioDePagoRepository;
import com.example.recetarium.demo.Repository.UsuarioRepository;
import com.example.recetarium.demo.Utiles.JwtUtil;
import jakarta.transaction.Transactional;
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
    CuentaCorrienteRepository cuentaCorrienteRepository;

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
        cuentaCorriente.setMedioDePago(medioDePago);
        medioDePago.setCuentaCorriente(cuentaCorriente);

        usuario.get().setAlumno(alumno);
        usuarioRepository.save(usuario.get());

    }
    public CuentaCorrienteRespondDto obtenerCuentaCorriente(Long idAlumno){
       CuentaCorrienteRespondDto respond=new CuentaCorrienteRespondDto();
       respond.setSaldo(repository.obtenerSaldoPorIdAlumno(idAlumno));
       return respond;
    }
    public MedioDePagoRespondDto obtenerMedioDePago(Long idAlumno) {
        MedioDePagoRespondDto response = new MedioDePagoRespondDto();
        Optional<Alumno> alumno = repository.findById(idAlumno);
        MedioDePago medioDePago = alumno.get().getCuentaCorriente().getMedioDePago();

        response.setId(medioDePago.getIdMedioDePago());
        response.setVencimiento(medioDePago.getVencimiento());
        response.setTitular(medioDePago.getNombreTitular());

        String numeroOculto= medioDePago.getNumTarjeta();
        String ultimoCuatro=numeroOculto.substring(numeroOculto.length()-4);
        String numeroTarjeta="*".repeat(numeroOculto.length()-4)+ultimoCuatro;

        response.setNumeroTarjeta(numeroTarjeta);

        return response;
    }

    public void nuevoMedioDePago(MedioDePagoRequestDto requestDto){
        Optional<Alumno> alumno=repository.findById(requestDto.getIdAlumno());
        CuentaCorriente cuentaCorriente=alumno.get().getCuentaCorriente();
        MedioDePago anterior=cuentaCorriente.getMedioDePago();
        cuentaCorriente.setMedioDePago(null);
        cuentaCorrienteRepository.save(cuentaCorriente);

        MedioDePago nuevoMedioDePago=new MedioDePago();
        nuevoMedioDePago.setCuentaCorriente(cuentaCorriente);
        nuevoMedioDePago.setNombreTitular(requestDto.getNombreTitular());
        nuevoMedioDePago.setNumTarjeta(requestDto.getNumeroTarjeta());
        nuevoMedioDePago.setVencimiento(requestDto.getVencimiento());
        nuevoMedioDePago.setCodSeguridad(requestDto.getCodigoSeguridad());

        cuentaCorriente.setMedioDePago(nuevoMedioDePago);
        cuentaCorrienteRepository.save(cuentaCorriente);
    }

}
