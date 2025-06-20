package com.example.recetarium.demo.Controller;

import com.example.recetarium.demo.DTOs.*;
import com.example.recetarium.demo.Service.AlumnoService;
import com.example.recetarium.demo.Service.AsistenciaACursoService;
import com.example.recetarium.demo.Service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfil")
public class PerfilController {
    @Autowired
    AsistenciaACursoService asistenciaACursoService;
    @Autowired
    CursoService cursoService;

    @Autowired
    AlumnoService alumnoService;

    @GetMapping("/miscursos")
    public List<MisCursosPreviewRespondDto> obtenerMisCursos(@RequestParam Long idAlumno){
        return asistenciaACursoService.obtenerCursosAlumno(idAlumno);
    }
    @GetMapping("/miscursos/curso/informacion")
    public MisCursosInformacionRespondDto obtenerInfoCurso(@RequestParam Long idAlumno,@RequestParam Long idCronograma){
       return cursoService.obtenerInformacionMiCurso(idAlumno,idCronograma);
    }
    @GetMapping("/miscursos/curso/practicas")
    public MisCursosPracticasRespondDto obtenerRequerimientos(@RequestParam Long idCurso){
        return cursoService.obtenerRequerimientosCurso(idCurso);
    }
    @GetMapping("/miscursos/curso/temas")
    public ContenidoCursoRespondDto obtenerTemasCurso(@RequestParam Long idCurso){
        return  cursoService.obtenerContenidoCurso(idCurso);
    }
    @DeleteMapping("/miscursos/baja")
    public void bajaCurso(@RequestParam Long idAlumno, @RequestParam Long idCronograma){
        asistenciaACursoService.baja(idAlumno,idCronograma);
    }
    @GetMapping("/cuentacorriente")
    public CuentaCorrienteRespondDto obtenerCuentaCorriente (@RequestParam Long idAlumno){
       return alumnoService.obtenerCuentaCorriente(idAlumno);
    }
    @GetMapping("/cuentacorriente/mediodepago")
    public MedioDePagoRespondDto obtenerMedioDePago(@RequestParam Long idAlumno){
        return alumnoService.obtenerMedioDePago(idAlumno);
    }
    @PostMapping("/cuentacorriente/mediodepago")
    public void borrarMedioDePago(@RequestBody MedioDePagoRequestDto request){
        alumnoService.nuevoMedioDePago(request);
    }

}
