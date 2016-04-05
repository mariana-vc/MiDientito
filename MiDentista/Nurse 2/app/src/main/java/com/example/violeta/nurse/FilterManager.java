package com.example.violeta.nurse;

/**
 * Created by velahuer on 03/04/16.
 */
public class FilterManager {
    FilterChain filterChain;

    public FilterManager(LoginDoctor loginDoctor){
        filterChain = new FilterChain();
        filterChain.setLoginDoctor(loginDoctor);
    }

    public void setFilter(Filter filter){
        filterChain.addFilter(filter);
    }

    public void filterRequest(String request){
        filterChain.execute(request);
    }
}
