package com.example.examenprogramacion56sa.Utilidad;

public class Producto {
    private String codigo;
    private String nombre_producto;
    private String stock;
    private String precio;
    private String area;

    public Producto(String codigo, String nombre_producto, String stock, String precio, String area) {
        this.codigo = codigo;
        this.nombre_producto = nombre_producto;
        this.stock = stock;
        this.precio = precio;
        this.area = area;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
