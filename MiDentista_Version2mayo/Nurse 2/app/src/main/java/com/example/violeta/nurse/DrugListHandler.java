package com.example.violeta.nurse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beeme on 03/04/2016.
 */
public class DrugListHandler extends ValueListHandler<Drug> {
    private DrugDAO dao = null;
    private Drug drug = null;

    public DrugListHandler(Drug drug) {
        try {
            this.drug = drug;
            this.dao = new DrugDAO();
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
            setList(resultsList);
        } catch (Exception e) {
        }
    }
}
