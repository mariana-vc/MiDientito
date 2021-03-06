package com.example.violeta.nurse;

import java.util.ArrayList;


public class HistorialListHandler extends ValueListHandler<Hist> {
    private HistorialDao dao = null;
    private Hist historial = null;

    public HistorialListHandler(Hist historial, String id) {
        try {
            this.historial = historial;
            this.dao = new HistorialDao();
            executeSearch(id);
        } catch (Exception e) {
        }
    }

    public void executeSearch(String id) {
        try {
            if (historial == null) {
                System.out.println("historial is null");
            }
            ArrayList<Hist> resultsList =dao.executeSelect(historial,id);
            System.out.println("resultado hist " + resultsList);
            setList(resultsList);
        } catch (Exception e) {
        }
    }
}
