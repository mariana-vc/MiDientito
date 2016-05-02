package com.example.violeta.nurse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by velahuer on 03/04/16.
 */
public class FilterChain {
    private List<Filter> filters = new ArrayList<Filter>();
    private LoginDoctor loginDoctor;

    public void addFilter(Filter filter){
        filters.add(filter);
    }

    public void execute(String request){
        for(Filter filter : filters){
            filter.execute(request);
        }
        loginDoctor.execute(request);
    }

    public void setLoginDoctor(LoginDoctor loginDoctor){
        this.loginDoctor = loginDoctor;
    }
}
