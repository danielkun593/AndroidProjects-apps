package com.example.basededatos;

public class Constants {
    //ombre de la base de datos
    public static final String DB_NAME = "REGISTROS";
    //version de la base de datos
    public static final int DB_VERSION = 1;
    //nombre de la tabla
    public static final String TABLE_NAME = "REGISTROS_TABLA";
    //columnas o campos de la tabla
    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_IMAGEN = "IMAGE";
    public static final String C_DESCRIPCION = "BIO";
    public static final String C_PHONE = "PHONE";
    public static final String C_EMAIL = "EMAIL";
    public static final String C_FECHA = "DOB";
    public static final String C_ADDED_TIMESTAMP = "ADDED_TIME_STAMP";
    public static final String C_UPDATE_TIMESTAMP = "UPDATE_TIME_STAMP";
    //crear query
    public static final String CREATE_TABLE = "CREATE TABLE"+TABLE_NAME +"("
            +C_ID+" INTEGER PRIMARY KEY,"
            +C_NAME+" TEXT,"
            +C_IMAGEN+ " TEXT,"
            +C_DESCRIPCION+ " TEXT,"
            +C_PHONE+ " TEXT,"
            +C_EMAIL+ " TEXT,"
            +C_FECHA+ " TEXT,"
            +C_ADDED_TIMESTAMP+ " TEXT,"
            +C_UPDATE_TIMESTAMP+ " TEXT)";

}
