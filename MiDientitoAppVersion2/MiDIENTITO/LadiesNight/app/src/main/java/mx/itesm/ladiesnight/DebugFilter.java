package mx.itesm.ladiesnight;

/**
 * Created by velahuer on 04/04/16.
 */
public class DebugFilter implements Filter {
    public void execute(String request){
        System.out.println("Log de peticion: " + request);
    }
}

