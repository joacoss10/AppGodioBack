package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.DTOs.FavoritoRequestDto;
import com.example.recetarium.demo.DTOs.FavoritoRespond;
import com.example.recetarium.demo.DTOs.RecetaPreviewRespondDto;
import com.example.recetarium.demo.Model.Calificacion;
import com.example.recetarium.demo.Model.Receta;
import com.example.recetarium.demo.Model.RecetaFavorito;
import com.example.recetarium.demo.Model.Usuario;
import com.example.recetarium.demo.Repository.CalificacionRepository;
import com.example.recetarium.demo.Repository.FavoritoRepository;
import com.example.recetarium.demo.Repository.RecetaRepository;
import com.example.recetarium.demo.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class FavoritoService {
    @Autowired
    private FavoritoRepository repoFavoritos;
    @Autowired
    private UsuarioRepository repoUsuario;
    @Autowired
    private RecetaRepository repoReceta;
    @Autowired
    private CalificacionRepository repoCalificacion;

    //
    //AGREGAR A FAVS
    @Transactional
    public FavoritoRespond agregarEliminarFavoritosReceta(FavoritoRequestDto dto){
        FavoritoRespond response=new FavoritoRespond();
        Optional<Usuario> usuarioOp=repoUsuario.findById(dto.getIdUsuario());
        Usuario usuario=usuarioOp.get();
        Optional<Receta> recetaOp=repoReceta.findById(dto.getIdReceta());
        Receta receta=recetaOp.get();
        if(usuarioOp.isPresent() && recetaOp.isPresent()){//verifica que exista el usuario con ese id o receta por las dudas.No deberia de pasar
            if(dto.isFavorito()){//elimino
                Optional<RecetaFavorito> recetaFavoritoOp=repoFavoritos.findByUsuarioAndReceta(usuario,receta);
                if(recetaFavoritoOp.isPresent()){//verifica que la tenga en favs por las dudas, no deberia de pasar que no la tenga
                    repoFavoritos.delete(recetaFavoritoOp.get());
                }
            }
            else{//agrego a favs
                if(!repoFavoritos.existsByUsuarioAndReceta(usuario,receta)){//verifica que no la tenga en favs por las dudas, no deberia de pasar que la tenga
                    RecetaFavorito recetaFavorito=new RecetaFavorito();
                    recetaFavorito.setUsuario(usuario);
                    recetaFavorito.setReceta(receta);
                    repoFavoritos.save(recetaFavorito);
                }
            }
            response.setCodigo(200);
        }
        else{
            response.setCodigo(400);
        }
        return response;
    }
    ///////////////////
    //OBTENER previews de favoritas
    @Transactional
    public Page<RecetaPreviewRespondDto> obtenerPreviewFavs(Long idUsuario, Pageable pageable){
        Optional<Usuario> usuarioOp=repoUsuario.findById(idUsuario);
        if(usuarioOp.isEmpty()){
            return Page.empty();
        }
        Page<RecetaFavorito> favoritosPage=repoFavoritos.findFavoritosOrdenadosPorFecha(idUsuario,pageable);
        List<RecetaPreviewRespondDto> previews=new ArrayList<>();

        for(RecetaFavorito fav:favoritosPage.getContent()){
            Receta receta= fav.getReceta();
            RecetaPreviewRespondDto dto=new RecetaPreviewRespondDto();
            dto.setIdReceta(receta.getIdReceta());
            dto.setTitulo(receta.getNombreReceta());
            dto.setImagenPrincipal(receta.getImagen());
            dto.setAutor(receta.getUsuario().getAlias());
            dto.setClasificacionPromedio(calcularPromedio(receta));
            dto.setEnFavoritos(true);
            previews.add(dto);
        }
        return new PageImpl<>(previews, pageable, favoritosPage.getTotalElements());
    }
    /////////privados paaaaaaaaaaaaaaaaaaaaaaaa/////////
    private Double calcularPromedio(Receta receta) {
        List<Calificacion> valoraciones=repoCalificacion.findByReceta(receta);
        if (valoraciones.isEmpty()){
            return 0.0;
        }
        double total=0;
        int cantidadValidas=0;
        for (Calificacion valor:valoraciones){
            if(valor.getCalificacion()!=null){
                total+=valor.getCalificacion();
                cantidadValidas++;
            }
        }
        if(cantidadValidas==0){
            return 0.0;
        }
        return total / cantidadValidas;
    }
}
