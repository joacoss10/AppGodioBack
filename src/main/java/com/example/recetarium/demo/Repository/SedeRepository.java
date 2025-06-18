package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.Sede;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

@Repository
public interface SedeRepository extends JpaRepository<Sede,Long> {
    Page<Sede> findByNombreSedeContainingIgnoreCase(String nombre, Pageable pageable);

}
