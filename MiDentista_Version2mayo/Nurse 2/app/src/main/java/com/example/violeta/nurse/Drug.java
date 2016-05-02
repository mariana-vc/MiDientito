package com.example.violeta.nurse;


public class Drug {
    public String getNombre() {
        return nombre;
    }

    public String nombre;

    public String getDescripcion() {
        return descripcion;
    }

    public String descripcion;
    public String tipo;

    public Drug(String nombre, String descripcion){
        this.descripcion=descripcion;
        //this.tipo=tipo;
        this.nombre=nombre;
    }
    public Drug(){
    }

}
