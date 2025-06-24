package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.DTOs.RecetaPreviewRespondDto;
import com.example.recetarium.demo.Repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recetarium.demo.DTOs.*;
import com.example.recetarium.demo.Model.*;
import com.example.recetarium.demo.Model.Enums.EstadoReceta;
import com.example.recetarium.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusquedaRecetaService {//AUNQUE SEA UN SERVICE NUEVO USA LOS REPOSITORY YA CREADOS (no hay uno de busqueda)
    @Autowired
    private RecetaRepository repoReceta;
    @Autowired
    private UsuarioRepository repoUsuario;
    @Autowired
    private FotoRecetaRepository repoFotos;
    @Autowired
    private PasoRepository repoPaso;
    @Autowired
    private MultimediaRepository repoMultimedia;
    @Autowired
    private IngredienteRepository repoIngrediente;
    @Autowired
    private UnidadRepository repoUnidad;
    @Autowired
    private UtilizadoRepository repoUtilizado;
    @Autowired
    private FavoritoRepository repoFavoritos;
    @Autowired
    private CalificacionRepository repoCalificacion;
    //
    //MIS RECETAS CON BARRA DE BUSQUEDA (solo palabra clave)
    @Transactional
    public Page<MisRecetasPreviewRespondDto> buscarMisRecetas(Long idUsuario, String palabraClave,Pageable pageable){
        Optional<Usuario> usuarioOp=repoUsuario.findById(idUsuario);
        if(usuarioOp.isEmpty()){
            return Page.empty();
        }
        //
        Page<Receta> recetaPage=repoReceta.buscarMisRecetasPorNombre(idUsuario, palabraClave, pageable);
        List<MisRecetasPreviewRespondDto> previews=new ArrayList<>();
        //
        for(Receta receta:recetaPage.getContent()){
            MisRecetasPreviewRespondDto dto=new MisRecetasPreviewRespondDto();
            dto.setIdReceta(receta.getIdReceta());
            dto.setTitulo(receta.getNombreReceta());
            dto.setImagenPrincipal(receta.getImagen());
            dto.setAutor(receta.getUsuario().getAlias());
            dto.setClasificacionPromedio(calcularPromedio(receta));
            //
            boolean favorito=repoFavoritos.existsByUsuarioAndReceta(usuarioOp.get(), receta);
            dto.setEnFavoritos(favorito);
            //
            dto.setEstado(receta.getEstado());
            previews.add(dto);
        }
        return new PageImpl<>(previews,pageable,recetaPage.getTotalElements());

    }
    //
    //MIS FAVORITOS CON BARRA DE BUSQUEDA (solo palabra clave)
    @Transactional
    public Page<RecetaPreviewRespondDto> buscarFavoritosPorNombre(Long idUsuario,String palabraClave,Pageable pageable){
        Optional<Usuario> usuarioOp=repoUsuario.findById(idUsuario);
        if(usuarioOp.isEmpty()){
            return Page.empty();
        }
        //
        Page<RecetaFavorito> favoritosPage=repoFavoritos.buscarFavoritosPorNombre(idUsuario, palabraClave, pageable);
        List<RecetaPreviewRespondDto> previews=new ArrayList<>();

        for(RecetaFavorito favorito:favoritosPage.getContent()){
            Receta receta=favorito.getReceta();
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
    //
    //MAIN BARRA DE BUSQUEDA CON FILTROS
    public Page<RecetaPreviewRespondDto> buscarRecetasMainFiltro(BusquedaMainRecetaRequestDto dto, Pageable pageable) {
        //verifico que exista usuairo
        Usuario usuario = null;
        if (dto.getIdUsuario() != null) {
            Optional<Usuario> usuarioOp = repoUsuario.findById(dto.getIdUsuario());
            if (usuarioOp.isPresent()) {
                usuario = usuarioOp.get();
            } else {
                return Page.empty();
            }
        }

        Page<Receta> recetasPage = null;

        // Sin filtros (solo nombre (opcional) y orden)
        if (dto.getFiltrosIngredientes() == null && dto.getCantidadPersona() == null) {
            //BUSCO POR PALABRA CLAVE EN ORDEN
            recetasPage = repoReceta.buscarRecetasPorNombreRecetaFiltroMain(dto.getPalabraClave(), pageable);
        }

        // Solo filtro de ingrediente (sin cantidadPersona)
        else if (dto.getFiltrosIngredientes() != null && dto.getCantidadPersona() == null) {
            FiltroIngredienteDto filtro = dto.getFiltrosIngredientes();
            if (filtro.isIncluir()) {
                Optional<Ingrediente> ingredienteOp = repoIngrediente.findByNombre(filtro.getNombreIngrediente());
                if (ingredienteOp.isEmpty()) return Page.empty();//verifico que exista el ingrediente

                if (filtro.getUnidadDeseada() != null) {//verifico que quiera buscar por cantidad de unidad especifica
                    Optional<Unidad> unidadOp = repoUnidad.findByDescripcion(filtro.getUnidadDeseada());
                    if (unidadOp.isEmpty()) return Page.empty();//verifico que exista la unidad
                    //BUSCO POR INGREDIENTE CON MAXIMO DE CANTIDAD
                    recetasPage = repoReceta.buscarRecetasConFiltroIngredienteConConversion(
                            dto.getPalabraClave(),
                            filtro.getNombreIngrediente(),
                            filtro.getUnidadDeseada(),
                            filtro.getCanitidadMaxima(),
                            pageable
                    );
                } else {//no busca por cantidad, solo por ingrediente
                    //BUSCO POR INGREDIENTE (sin maximo de unidad)
                    recetasPage = repoReceta.buscarRecetasPorIngredienteSinCantidad(
                            dto.getPalabraClave(),
                            filtro.getNombreIngrediente(),
                            pageable
                    );
                }
            } else {//excluye ingrediente
                //BUSCO POR INGREDIENTE (EXCLUYENDOLO)
                recetasPage = repoReceta.buscarRecetasExcluyendoIngrediente(
                        dto.getPalabraClave(),
                        filtro.getNombreIngrediente(),
                        pageable
                );
            }
        }

        // Solo cantidadPersona
        else if (dto.getFiltrosIngredientes() == null && dto.getCantidadPersona() != null) {
            //BUSCO SOLO POR CANTIDAD DE PERSONAS (sin ingredientes)
            recetasPage = repoReceta.buscarRecetasPorNombreRecetaFiltroMainPersonas(
                    dto.getPalabraClave(),
                    dto.getCantidadPersona(),
                    pageable
            );
        }

        // Filtro de ingrediente + cantidadPersona
        else {
            FiltroIngredienteDto filtro = dto.getFiltrosIngredientes();
            if (filtro.isIncluir()) {
                Optional<Ingrediente> ingredienteOp = repoIngrediente.findByNombre(filtro.getNombreIngrediente());
                if (ingredienteOp.isEmpty()) return Page.empty();

                if (filtro.getUnidadDeseada() != null) {
                    Optional<Unidad> unidadOp = repoUnidad.findByDescripcion(filtro.getUnidadDeseada());
                    if (unidadOp.isEmpty()) return Page.empty();
                    //BUSCO POR INGREDIENTE CON MAXIMO DE CANTIDAD Y PENSADA PARA X PERSONAS
                    recetasPage = repoReceta.buscarRecetasConFiltroIngredienteConConversionPersona(
                            dto.getPalabraClave(),
                            filtro.getNombreIngrediente(),
                            filtro.getUnidadDeseada(),
                            filtro.getCanitidadMaxima(),
                            dto.getCantidadPersona(),
                            pageable
                    );
                } else {
                    //BUSCO POR INGREDIENTE (sin maximo de unidad) Y PENSADA PARA X PERSONAS
                    recetasPage = repoReceta.buscarRecetasPorIngredienteSinCantidadPersona(
                            dto.getPalabraClave(),
                            filtro.getNombreIngrediente(),
                            dto.getCantidadPersona(),
                            pageable
                    );
                }
            } else {
                //BUSCO POR INGREDIENTE (EXCLUYENDOLO) CON MAXIMO DE CANTIDAD
                recetasPage = repoReceta.buscarRecetasExcluyendoIngredientePersona(
                        dto.getPalabraClave(),
                        filtro.getNombreIngrediente(),
                        dto.getCantidadPersona(),
                        pageable
                );
            }
        }

        // Armo la respuesta
        List<RecetaPreviewRespondDto> previews = new ArrayList<>();
        for (Receta receta : recetasPage.getContent()) {
            RecetaPreviewRespondDto dtoResponse = new RecetaPreviewRespondDto();
            dtoResponse.setIdReceta(receta.getIdReceta());
            dtoResponse.setTitulo(receta.getNombreReceta());
            dtoResponse.setImagenPrincipal(receta.getImagen());
            dtoResponse.setAutor(receta.getUsuario().getAlias());
            dtoResponse.setClasificacionPromedio(calcularPromedio(receta));
            dtoResponse.setEnFavoritos(usuario != null && repoFavoritos.existsByUsuarioAndReceta(usuario, receta));
            previews.add(dtoResponse);
        }
        return new PageImpl<>(previews, pageable, recetasPage.getTotalElements());
    }

    ///////////////////////privados paaaaaaaaaa/////////////////////////////////////
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
