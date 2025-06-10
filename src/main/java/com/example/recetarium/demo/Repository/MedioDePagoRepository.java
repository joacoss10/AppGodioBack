package com.example.recetarium.demo.Repository;

import com.example.recetarium.demo.Model.MedioDePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedioDePagoRepository extends JpaRepository<MedioDePago, Long> {
}
