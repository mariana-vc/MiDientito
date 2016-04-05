package mx.itesm.ladiesnight;

import java.util.ArrayList;

/**
 * Created by beeme on 03/04/2016.
 */
public class HistoriaListHandler extends ValueListHandler<Historia> {
    private HistoriaDAO dao = null;
    private Historia historia = null;

    public HistoriaListHandler(Historia historia) {
        try {
            this.historia = historia;
            this.dao = new HistoriaDAO();
            executeSearch();
        } catch (Exception e) {
        }
    }

    public void executeSearch() {
        try {
            if (historia == null) {
                System.out.println("historia is null");
            }
            ArrayList<Historia> resultsList =dao.executeSelect(historia);
            setList(resultsList);
        } catch (Exception e) {
        }
    }
}
