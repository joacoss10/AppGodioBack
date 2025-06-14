package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.DTOs.*;
import com.example.recetarium.demo.Model.*;
import com.example.recetarium.demo.Model.Enums.EstadoReceta;
import com.example.recetarium.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecetaService {
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
    //CREAR RECETA
    @Transactional
    public RecetaResponseDto crearReceta(RecetaRequestDto dto){
        RecetaResponseDto response=new RecetaResponseDto();
        for (IngredienteRequestDto ingredienteDto : dto.getIngredientesUsados()) {
            Optional<Unidad> unidadOp = repoUnidad.findByDescripcion(ingredienteDto.getUnidad());
            if (unidadOp.isEmpty()) {//unidad no encontrada, no deberia de pasar pero por las dudas
                response.setCodigo(401);
                return response;
            }
        }
        //coca
        Optional<Usuario> usuarioOptional=repoUsuario.findByMailOrAlias(dto.getAliasUsuario(), dto.getAliasUsuario());
        if(usuarioOptional.isEmpty()){//Usuario no encontrado, no deberia de pasar pero por las dudas
            response.setCodigo(400);
            return response;
        }
        Usuario usuario=usuarioOptional.get();
        //coca
        //CREAR RECETA
        Receta receta=new Receta();
        receta.setNombreReceta(dto.getNombreReceta());
        receta.setDescripcionReceta(dto.getDescripcionReceta());
        receta.setPorciones(dto.getPorciones());
        receta.setCantidadPersonas(dto.getCantidadPersonas());
        receta.setFechaCreacion(Timestamp.valueOf(LocalDateTime.now()));
        receta.setUsuario(usuario);
        receta.setImagen(dto.getImagenPrincipal());
        receta.setEstado(EstadoReceta.En_Espera);

        repoReceta.save(receta);
        //coca
        //GUARDAT FOTOS ADICIONALES, puede no haber por eso el if
        if(dto.getImagenes()!=null){
            for(byte[] imagen: dto.getImagenes()){
                FotoReceta foto= new FotoReceta();
                foto.setReceta(receta);
                foto.setFoto(imagen);
                repoFotos.save(foto);
            }
        }
        //coca
        //Guardar pasos y multimedia. Pasos son obligatorios
        for(PasoRequestDto pasoDto: dto.getPasos()){
            Paso paso=new Paso();
            paso.setNumeroDePaso(pasoDto.getNumeroPaso());
            paso.setTexto(pasoDto.getTexto());
            paso.setReceta(receta);
            repoPaso.save(paso);
            if(pasoDto.getImagen()!=null){//paso con imagen
                Multimedia multimedia=new Multimedia();
                multimedia.setContenido(pasoDto.getImagen());
                multimedia.setTipoDeContenido("Imagen");
                multimedia.setPaso(paso);
                repoMultimedia.save(multimedia);
            }
        }
        //Guardar ingredientes usados
        for(IngredienteRequestDto ingredienteDto: dto.getIngredientesUsados()){
            Optional<Ingrediente>IngredienteOp=repoIngrediente.findByNombre(ingredienteDto.getNombreIngrediente());
            Optional<Unidad> unidadOp=repoUnidad.findByDescripcion(ingredienteDto.getUnidad());

            if(IngredienteOp.isPresent()){//ingrediente existente
                Utilizado usado=new Utilizado();
                usado.setCantidad(ingredienteDto.getCantidad());
                usado.setObservaciones(ingredienteDto.getObservacion());
                usado.setReceta(receta);
                usado.setIngrediente(IngredienteOp.get());
                usado.setUnidad(unidadOp.get());
                repoUtilizado.save(usado);
            }
            else{//EL INGREDIENTE ES NUEVO
                    Ingrediente ingrediente=new Ingrediente();
                    ingrediente.setNombre(ingredienteDto.getNombreIngrediente());
                    repoIngrediente.save(ingrediente);
                    //
                    Utilizado usado=new Utilizado();
                    usado.setCantidad(ingredienteDto.getCantidad());
                    usado.setObservaciones(ingredienteDto.getObservacion());
                    usado.setReceta(receta);
                    usado.setIngrediente(ingrediente);
                    usado.setUnidad(unidadOp.get());
                    repoUtilizado.save(usado);
                }
            }
        response.setCodigo(200);
        return response;
    }
    //
    //OBTENER PREVIEW DEL MAIN con logeo o sin logeo
    @Transactional
    public List<RecetaPreviewRespondDto> obtenerPreviews(Long idUsuario, Pageable pageable){
        Page<Receta> recetasAprobadas=repoReceta.findRecetasPublicadas(pageable);//busco recetas aprobadas en formato descenciente y por cantidad de pageable
        List<RecetaPreviewRespondDto> previews=new ArrayList<>();
        Usuario usuario=null;
        if(idUsuario!=null){
            Optional<Usuario> usuarioOp=repoUsuario.findById(idUsuario);
            usuario=usuarioOp.get();//obtengo usuario para saber si esta en favoritos
        }

        for(Receta receta:recetasAprobadas){//construyo el dto segun las recetas encontradas
            RecetaPreviewRespondDto dto=new RecetaPreviewRespondDto();
            dto.setIdReceta(receta.getIdReceta());
            dto.setTitulo(receta.getNombreReceta());
            dto.setImagenPrincipal(receta.getImagen());
            dto.setAutor(receta.getUsuario().getAlias());
            dto.setClasificacionPromedio(calcularPromedio(receta));
            if(usuario!=null){
                boolean favorito=repoFavoritos.existsByUsuarioAndReceta(usuario,receta);
                dto.setEnFavoritos(favorito);
            }
            else{
                dto.setEnFavoritos(false);
            }
            previews.add(dto);
        }
        return previews;
    }
///////////////////////privados pa/////////////////////////////////////
    private Double calcularPromedio(Receta receta) {
        List<Calificacion> valoraciones=repoCalificacion.findByReceta(receta);
        if (valoraciones.isEmpty()){
            return 0.0;
        }
        double total=0;
        for (Calificacion valor:valoraciones){
            total+=valor.getCalificacion();
        }
        return total/ valoraciones.size();
    }
}
