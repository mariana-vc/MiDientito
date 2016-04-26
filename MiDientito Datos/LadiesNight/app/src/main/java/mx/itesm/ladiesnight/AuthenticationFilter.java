package mx.itesm.ladiesnight;

/**
 * Created by velahuer on 04/04/16.
 */
public class AuthenticationFilter {
    public void execute(String request){
        System.out.println("Autenticando la petición: " + request);
    }
}
