package mx.itesm.ladiesnight;

/**
 * Created by LuisFernando on 12/11/2015.
 */
public class Farmacia {
    public String nombre, direccion;
    public double latitud, longitud;

    public Farmacia(String nombre, String direccion, double latitud, double longitud){
        this.direccion=direccion;
        this.latitud=latitud;
        this.longitud=longitud;
        this.nombre=nombre;
    }


}
