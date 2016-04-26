package mx.itesm.ladiesnight;

/**
 * Created by velahuer on 04/04/16.
 */
public class InterceptingFilterDemo {
    public static void main(String[] args){
        FilterManager filterManager = new FilterManager(new OnceLogin());
        filterManager.setFilter((Filter) new AuthenticationFilter());
        filterManager.setFilter(new DebugFilter());

        Client client = new Client();
        client.setFilterManager(filterManager);
        client.sendRequest("HOME");

    }
}
