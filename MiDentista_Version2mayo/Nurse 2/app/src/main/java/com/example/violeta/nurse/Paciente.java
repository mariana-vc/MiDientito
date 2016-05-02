package com.example.violeta.nurse;

/**
 * Created by MarianaItzel on 04/04/2016.
 */
public class Paciente {
    public String nombre, id, edad, sexo, tipoSanguineo;

    public Paciente(String nombre, String id, String edad, String sexo, String tipoSanguineo) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.sexo = sexo;
        this.tipoSanguineo = tipoSanguineo;
    }

    public Paciente() {
    }
}
