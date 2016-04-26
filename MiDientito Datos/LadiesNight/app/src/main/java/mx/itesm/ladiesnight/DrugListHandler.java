package mx.itesm.ladiesnight;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beeme on 03/04/2016.
 */
public class DrugListHandler extends ValueListHandler<Drug> {
    private DrugDAO dao = null;
    private Drug drug = null;

    public DrugListHandler(Drug drug, String id) {
        try {
            this.drug = drug;
            this.dao = new DrugDAO(id);
            executeSearch();
        } catch (Exception e) {
        }
    }

    public void executeSearch() {
        try {
            if (drug == null) {
                System.out.println("drug is null");
            }
            ArrayList<Drug> resultsList =dao.executeSelect(drug);
            for (Drug id_med : resultsList){
                Log.i("AQUI", id_med.getNombre());
            }
            if(resultsList==null){
                Log.i("HANDLER", "VACIO");
            }

                setList(resultsList);
        } catch (Exception e) {
        }
    }
}
