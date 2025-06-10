package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByMailOrAlias(String mail, String Alias);
    Optional<Usuario> findByAlias(String Alias);
}
