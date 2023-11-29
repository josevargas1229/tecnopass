package com.example.conexionbd.Modelo;

public class ClaseDatosProductos {
    String nombre;
    String imgproducto;
    double precio;
    int stock;
    String descripcion;
    public ClaseDatosProductos(String nombre,double precio, int stock,String imgproducto,String descripcion ) {
        this.nombre=nombre;
        this.imgproducto=imgproducto;
        this.precio=precio;
        this.stock=stock;
        this.descripcion=descripcion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getFoto() {
        return imgproducto;
    }
    public void setFoto(String imgproducto) {
        this.imgproducto = imgproducto;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getExistencia() {
        return stock;
    }
    public void setExistencia(int stock) {
        this.stock = stock;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
