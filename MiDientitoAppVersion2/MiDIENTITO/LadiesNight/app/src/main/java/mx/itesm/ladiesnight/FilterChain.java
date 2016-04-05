package mx.itesm.ladiesnight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velahuer on 04/04/16.
 */
public class FilterChain {
    private List<Filter> filters = new ArrayList<Filter>();
    private OnceLogin onceLogin;

    public void addFilter(Filter filter){
        filters.add(filter);
    }

    public void execute(String request){
        for(Filter filter : filters){
            filter.execute(request);
        }
        onceLogin.execute(request);
    }

    public void setLoginDoctor(OnceLogin loginDoctor){
        this.onceLogin = onceLogin;
    }
}
