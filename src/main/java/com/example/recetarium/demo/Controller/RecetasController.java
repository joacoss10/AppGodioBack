package com.example.recetarium.demo.Controller;

import com.example.recetarium.demo.DTOs.RecetaDto;
import com.example.recetarium.demo.DTOs.RecetaResumenDto;
import com.example.recetarium.demo.Service.RecetaService;
import com.example.recetarium.demo.Service.FavoritoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/auth/recetas")
public class RecetasController {

    private final RecetaService recetaService;
    private final FavoritoService favoritoService;

    @Autowired
    public RecetasController(RecetaService recetaService, FavoritoService favoritoService) {
        this.recetaService = recetaService;
        this.favoritoService = favoritoService;
    }

   @PostMapping
   @PreAuthorize("hasAnyRole('USUARIO', 'ALUMNO')")
   public ResponseEntity<String> crearReceta(@RequestBody RecetaDto recetaDto, Principal principal) {
      recetaService.crearReceta(recetaDto, principal.getName());
      return ResponseEntity.ok("Receta guardada exitosamente");
    }

   @GetMapping
   public ResponseEntity<List<RecetaResumenDto>> obtenerRecetas() {
     List<RecetaResumenDto> recetas = recetaService.obtenerRecetasPublicadas();
     return ResponseEntity.ok(recetas);
    }


    @PostMapping("/{id}/favoritos")
    @PreAuthorize("hasAnyRole('USUARIO', 'ALUMNO')")
    public ResponseEntity<String> agregarAFavoritos(@PathVariable Long id, Principal principal) {
        favoritoService.agregarFavorito(id, principal.getName());
        return ResponseEntity.ok("Receta agregada a favoritos");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USUARIO', 'ALUMNO')")
    public ResponseEntity<String> editarReceta(@PathVariable Long id, @RequestBody RecetaDto recetaDto,
                                           Principal principal) {
        recetaService.editarReceta(id, recetaDto, principal.getName());
        return ResponseEntity.ok("Receta editada exitosamente");
   }


    @DeleteMapping("/{id}/favoritos")
    @PreAuthorize("hasAnyRole('USUARIO', 'ALUMNO')")
    public ResponseEntity<String> eliminarDeFavoritos(@PathVariable Long id, Principal principal) {
       favoritoService.eliminarFavorito(id, principal.getName());
       return ResponseEntity.ok("Receta eliminada de favoritos");
    }

}
