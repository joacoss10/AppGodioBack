package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.DTOs.RecetaDto;
import com.example.recetarium.demo.DTOs.RecetaResumenDto;
import com.example.recetarium.demo.Model.*;
import com.example.recetarium.demo.Model.Enums.EstadoReceta;
import com.example.recetarium.demo.Repository.*;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final UsuarioRepository usuarioRepository;
    private final TipoRecetaRepository tipoRecetaRepository;
    private final IngredienteRepository ingredienteRepository;

    public RecetaService(
            RecetaRepository recetaRepository,
            UsuarioRepository usuarioRepository,
            TipoRecetaRepository tipoRecetaRepository,
            IngredienteRepository ingredienteRepository
    ) {
        this.recetaRepository = recetaRepository;
        this.usuarioRepository = usuarioRepository;
        this.tipoRecetaRepository = tipoRecetaRepository;
        this.ingredienteRepository = ingredienteRepository;
    }

    public void crearReceta(RecetaDto dto, String aliasUsuario) {
        Usuario usuario = usuarioRepository.findByMailOrAlias(aliasUsuario, aliasUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (recetaRepository.existsByNombreRecetaAndUsuario_IdUsuario(dto.nombreReceta, usuario.getIdUsuario())) {
            throw new RuntimeException("Ya existe una receta con ese nombre para este usuario");
        }

        TipoReceta tipo = tipoRecetaRepository.findById(dto.idTipoReceta)
                .orElseThrow(() -> new RuntimeException("Tipo de receta inválido"));

        Receta receta = new Receta();
        receta.setNombreReceta(dto.nombreReceta);
        receta.setDescripcionReceta(dto.descripcionReceta);
        receta.setPorciones(dto.porciones);
        receta.setCantidadPersonas(dto.cantidadPersonas);
        receta.setFechaCreacion(new Date());
        receta.setTipoReceta(tipo);
        receta.setUsuario(usuario);
        receta.setImagen(dto.imagenPrincipal);
        receta.setEstado(EstadoReceta.PENDIENTE);

        List<Paso> pasos = new ArrayList<>();
        for (int i = 0; i < dto.pasos.size(); i++) {
            Paso paso = new Paso();
            paso.setTexto(dto.pasos.get(i));
            paso.setNumeroDePaso(i + 1);
            paso.setReceta(receta);
            pasos.add(paso);
        }
        receta.setPaso(pasos);

        List<Utilizado> utilizados = new ArrayList<>();
        for (RecetaDto.IngredienteCantidadDTO ing : dto.ingredientes) {
            Ingrediente ingrediente = ingredienteRepository.findById(ing.idIngrediente)
                    .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));

            Utilizado usado = new Utilizado();
            usado.setIngrediente(ingrediente);
            usado.setCantidad(ing.cantidad);
            usado.setReceta(receta);
            utilizados.add(usado);
        }
        receta.setUtilizado(utilizados);

        recetaRepository.save(receta);
    }

    public List<RecetaResumenDto> obtenerRecetasPublicadas() {
        return recetaRepository.findByEstado(EstadoReceta.APROBADA)
                .stream()
                .map(receta -> new RecetaResumenDto(
                        receta.getIdReceta(),
                        receta.getNombreReceta(),
                        receta.getDescripcionReceta(),
                        receta.getImagen()))
                .collect(Collectors.toList());
    }

    public void editarReceta(Long id, RecetaDto dto, String aliasUsuario) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));

        if (!receta.getUsuario().getAlias().equals(aliasUsuario)) {
            throw new RuntimeException("No tienes permiso para editar esta receta");
        }

        receta.setNombreReceta(dto.nombreReceta);
        receta.setDescripcionReceta(dto.descripcionReceta);
        receta.setPorciones(dto.porciones);
        receta.setCantidadPersonas(dto.cantidadPersonas);
        receta.setImagen(dto.imagenPrincipal);
        receta.setEstado(EstadoReceta.PENDIENTE);

        recetaRepository.save(receta);
    }
}
