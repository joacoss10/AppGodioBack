package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.DTOs.FavoritoRequestDto;
import com.example.recetarium.demo.Model.Receta;
import com.example.recetarium.demo.Model.RecetaFavorito;
import com.example.recetarium.demo.Model.Usuario;
import com.example.recetarium.demo.Repository.FavoritoRepository;
import com.example.recetarium.demo.Repository.RecetaRepository;
import com.example.recetarium.demo.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class FavoritoService {
    @Autowired
    private FavoritoRepository repoFavoritos;
    @Autowired
    private UsuarioRepository repoUsuario;
    @Autowired
    private RecetaRepository repoReceta;

    //
    //AGREGAR A FAVS
    public void agregarEliminarFavoritosReceta(FavoritoRequestDto dto){
        Optional<Usuario> usuarioOp=repoUsuario.findById(dto.getIdUsuario());
        Usuario usuario=usuarioOp.get();
        Optional<Receta> recetaOp=repoReceta.findById(dto.getIdReceta());
        Receta receta=recetaOp.get();
        //
        if(dto.isFavorito()){//elimino
            Optional<RecetaFavorito> recetaFavoritoOp=repoFavoritos.findByUsuarioAndReceta(usuario,receta);
            repoFavoritos.delete(recetaFavoritoOp.get());
            }
        else{//agrego a favs
            RecetaFavorito recetaFavorito=new RecetaFavorito();
            recetaFavorito.setUsuario(usuario);
            recetaFavorito.setReceta(receta);
            repoFavoritos.save(recetaFavorito);
        }
    }
}
