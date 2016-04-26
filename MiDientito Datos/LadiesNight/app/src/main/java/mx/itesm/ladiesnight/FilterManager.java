package mx.itesm.ladiesnight;

/**
 * Created by velahuer on 04/04/16.
 */
public class FilterManager {
    FilterChain filterChain;

    public FilterManager(OnceLogin onceLogin){
        filterChain = new FilterChain();
        filterChain.setLoginDoctor(onceLogin);
    }

    public void setFilter(Filter filter){
        filterChain.addFilter(filter);
    }

    public void filterRequest(String request){
        filterChain.execute(request);
    }
}
