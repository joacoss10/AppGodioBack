package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.Model.MedioDePago;
import com.example.recetarium.demo.Repository.MedioDePagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedioDePagoService {
    @Autowired
    private MedioDePagoRepository repository;

    public void guardarMedioDePago(MedioDePago medioDePago){
        repository.save(medioDePago);
    }
}
