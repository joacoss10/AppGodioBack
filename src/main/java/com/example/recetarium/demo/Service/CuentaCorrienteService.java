package com.example.recetarium.demo.Service;

import com.example.recetarium.demo.Model.CuentaCorriente;
import com.example.recetarium.demo.Repository.CuentaCorrienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaCorrienteService {
    @Autowired
    private CuentaCorrienteRepository repository;
    public void guardarCuentaCorriente(CuentaCorriente cte){
        repository.save(cte);
    }

}
