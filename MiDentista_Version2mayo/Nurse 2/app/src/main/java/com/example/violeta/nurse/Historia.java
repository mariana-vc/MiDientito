package com.example.violeta.nurse;

/**
 * Created by MarianaItzel on 03/05/2016.
 */
public class Historia {
    public String nombre, fecha;

    public Historia(){

    }

    public Historia(String nombre, String fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return nombre + "\n "+ fecha;
    }
}
