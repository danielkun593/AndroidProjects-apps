package com.example.cacaomobile2.Entidades;

public class Tratamientos {
    private String imagen;
    private String fecha;
    private String hora;
    private String tipo_cacao;
    private String fecha_sembrio;
    private String fertelizante;
    private String riego;
    private String poda;
    private String descripcion;

    public Tratamientos(String imagen, String fecha, String hora, String tipo_cacao, String fecha_sembrio, String fertelizante, String riego, String poda, String descripcion) {
        this.imagen = imagen;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo_cacao = tipo_cacao;
        this.fecha_sembrio = fecha_sembrio;
        this.fertelizante = fertelizante;
        this.riego = riego;
        this.poda = poda;
        this.descripcion = descripcion;
    }

    public Tratamientos() {
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipo_cacao() {
        return tipo_cacao;
    }

    public void setTipo_cacao(String tipo_cacao) {
        this.tipo_cacao = tipo_cacao;
    }

    public String getFecha_sembrio() {
        return fecha_sembrio;
    }

    public void setFecha_sembrio(String fecha_sembrio) {
        this.fecha_sembrio = fecha_sembrio;
    }

    public String getFertelizante() {
        return fertelizante;
    }

    public void setFertelizante(String fertelizante) {
        this.fertelizante = fertelizante;
    }

    public String getRiego() {
        return riego;
    }

    public void setRiego(String riego) {
        this.riego = riego;
    }

    public String getPoda() {
        return poda;
    }

    public void setPoda(String poda) {
        this.poda = poda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
