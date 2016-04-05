package com.example.violeta.nurse;

/**
 * Created by velahuer on 03/04/16.
 */
public class AuthenticationFilter implements Filter {
    public void execute(String request){
        System.out.println("Autenticando la petición: " + request);
    }
}
