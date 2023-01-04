package com.example.registercar;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class ListaVehiculos {
    public static ArrayList<ArrayList<String>> cars = new ArrayList<>();
    /**
     * @param marca
     * @param modelo
     * @param cilindraje
     * @param manejo
     * @param color
     * @param anio
     * @param placa
     * @param matricula
     * @param pais_impor
     * @param valor
     */

    public void Añadir(String marca, String modelo, String cilindraje, String manejo, String color, String anio, String placa, String matricula,
                       String pais_impor, String valor){
        ArrayList<String> con = new ArrayList<>(Arrays.asList(marca, modelo, cilindraje, manejo, color, anio, placa, matricula, pais_impor, valor));
        cars.add(con);
    }
}
