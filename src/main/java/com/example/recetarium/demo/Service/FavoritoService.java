package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.DTOs.FavoritoRequestDto;
import com.example.recetarium.demo.DTOs.FavoritoRespond;
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
}
