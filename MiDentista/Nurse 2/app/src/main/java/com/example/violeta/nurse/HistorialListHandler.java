package com.example.violeta.nurse;

import java.util.ArrayList;


public class HistorialListHandler extends ValueListHandler<Hist> {
    private HistorialDao dao = null;
    private Hist historial = null;

    public HistorialListHandler(Hist historial) {
        try {
            this.historial = historial;
            this.dao = new HistorialDao();
            executeSearch();
        } catch (Exception e) {
        }
    }

    public void executeSearch() {
        try {
            if (historial == null) {
                System.out.println("historial is null");
            }
            ArrayList<Hist> resultsList =dao.executeSelect(historial);
            setList(resultsList);
        } catch (Exception e) {
        }
    }
}
