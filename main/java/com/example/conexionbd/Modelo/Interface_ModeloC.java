package com.example.conexionbd.Modelo;

public interface Interface_ModeloC {
    void validar(String email);
    void consulta(String email);
    void actualizar(String codigo, int id);
    void validarCodigo(String mail,String codigo);
}
