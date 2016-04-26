package com.example.violeta.nurse;

/**
 * Created by velahuer on 03/04/16.
 */
public class DebugFilter implements Filter {
    public void execute(String request){
        System.out.println("Log de peticion: " + request);
    }
}
