package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.DTOs.RecetaRequestDto;
import com.example.recetarium.demo.DTOs.RecetaResponseDto;
import com.example.recetarium.demo.Model.Receta;
import com.example.recetarium.demo.Model.Usuario;
import com.example.recetarium.demo.Model.tipoReceta;
import com.example.recetarium.demo.Repository.RecetaRepository;
import com.example.recetarium.demo.Repository.TipoRecetaRepository;
import com.example.recetarium.demo.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RecetaService {
    @Autowired
    private RecetaRepository repoReceta;
    @Autowired
    private UsuarioRepository repoUsuario;
    @Autowired
    private TipoRecetaRepository repoTipoReceta;

    public RecetaResponseDto crearReceta(RecetaRequestDto dto){
        Optional<Usuario> usuario= repoUsuario.findByAlias(dto.getAliasUsuario());
        //Optional<tipoReceta> tipoReceta=repoTipoReceta.findByDescripcion(dto.getTipoReceta());
        RecetaResponseDto response = new RecetaResponseDto();
        if(!usuario.isEmpty()){
            Receta receta= new Receta();
            receta.setNombreReceta(dto.getNombreReceta());
            receta.setDescripcionReceta(dto.getDescripcionReceta());
            receta.setPorciones(dto.getPorciones());
            receta.setCantidadPersonas(dto.getCantidadPersonas());
            receta.setFechaCreacion(java.sql.Timestamp.valueOf(LocalDateTime.now()));
            receta.setUsuario(usuario.get());
            repoReceta.save(receta);
            //receta.setTipoReceta(tipoReceta.get());
            //
            response.setCodigo(200);//CREACION CORRECTA DE RECETA
        }
        else{
            response.setCodigo(400);//NO SE PUDO CREAR LA RECETA por falta de usuario
        }
        return response;
    }
}
