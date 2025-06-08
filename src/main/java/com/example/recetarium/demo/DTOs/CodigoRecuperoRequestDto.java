package com.example.recetarium.demo.DTOs;

public class CodigoRecuperoRequestDto {
    public String aliasOMail;
    private int codigo;

    public CodigoRecuperoRequestDto(String aliasOMail, int codigo){
        this.aliasOMail=aliasOMail;
        this.codigo=codigo;
    }

    public String getAliasOMail(){
        return aliasOMail;
    }
    public int getCodigo(){
        return codigo;
    }
}
