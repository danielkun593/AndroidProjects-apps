package com.example.cacaomobile2.Entidades;

import android.media.Image;

public class Agricultor {
    private Image foto;
    private String cedula;
    private String nombre;
    private String telefono;
    private String ciudad;
    private String email;
    private String genero;
    private String usuario;
    private String password;

    public Agricultor(Image foto, String cedula, String nombre, String telefono, String ciudad, String email, String genero, String usuario, String password) {
        this.foto = foto;
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.email = email;
        this.genero = genero;
        this.usuario = usuario;
        this.password = password;
    }

    public Agricultor() {
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
