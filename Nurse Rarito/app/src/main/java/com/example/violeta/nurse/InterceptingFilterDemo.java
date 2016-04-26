package com.example.violeta.nurse;

/**
 * Created by velahuer on 03/04/16.
 */
public class InterceptingFilterDemo {
    public static void main(String[] args){
        FilterManager filterManager = new FilterManager(new LoginDoctor());
        filterManager.setFilter(new AuthenticationFilter());
        filterManager.setFilter(new DebugFilter());

        Client client = new Client();
        client.setFilterManager(filterManager);
        client.sendRequest("HOME");

    }
}
