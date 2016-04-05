package com.example.violeta.nurse;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;


public class GuardarReceta extends Fragment {
    View rootView;
    String idP;
    String idMedicina;
    EditText indicaciones;
    private ProgressDialog progressDialog;
    ArrayList idMedicinas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.guardar_receta, container, false);
        TextView idPac = (TextView) rootView.findViewById(R.id.idPac);
        TextView medicinasT = (TextView) rootView.findViewById(R.id.medicinas);
        indicaciones = (EditText) rootView.findViewById(R.id.indicaciones);
        idP = getArguments().getString("id");
        idPac.setText(idP);
        Button save = (Button) rootView.findViewById(R.id.guardaReceta);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"¡Receta guardada!",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), PerfilPaciente.class);
                startActivity(i);
            }
        });


        ArrayList<String> medicinas = new ArrayList<String>();

        medicinas.add("Medicina1");
        medicinas.add("Medicina2");
        medicinas.add("Medicina3");
        medicinas.add("Medicina4");

        String x = "";
        for (int i = 0; i < medicinas.size(); i++) {
            x += medicinas.get(i).toString();
            x += "\n";
            medicinasT.setText(x);
        }

        return rootView;
    }
}
