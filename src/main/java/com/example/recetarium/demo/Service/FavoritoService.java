package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.Model.Receta;
import com.example.recetarium.demo.Model.RecetaFavorito;
import com.example.recetarium.demo.Model.Usuario;
import com.example.recetarium.demo.Repository.RecetaFavoritoRepository;
import com.example.recetarium.demo.Repository.RecetaRepository;
import com.example.recetarium.demo.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class FavoritoService {

    private final RecetaFavoritoRepository favoritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RecetaRepository recetaRepository;

    public FavoritoService(RecetaFavoritoRepository favoritoRepository,
                           UsuarioRepository usuarioRepository,
                           RecetaRepository recetaRepository) {
        this.favoritoRepository = favoritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.recetaRepository = recetaRepository;
    }

    public void agregarFavorito(Long idReceta, String aliasUsuario) {
        Usuario usuario = usuarioRepository.findByMailOrAlias(aliasUsuario, aliasUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Receta receta = recetaRepository.findById(idReceta)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));

        boolean yaEsFavorito = favoritoRepository.existsByUsuarioAndReceta(usuario, receta);

        if (yaEsFavorito) {
            throw new RuntimeException("La receta ya está en favoritos");
        }

        RecetaFavorito favorito = new RecetaFavorito();
        favorito.setUsuario(usuario);
        favorito.setReceta(receta);
        favoritoRepository.save(favorito);
    }

    public void eliminarFavorito(Long idReceta, String aliasUsuario) {
        Usuario usuario = usuarioRepository.findByMailOrAlias(aliasUsuario, aliasUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Receta receta = recetaRepository.findById(idReceta)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));

        favoritoRepository.deleteByUsuarioAndReceta(usuario, receta);
    }
}
