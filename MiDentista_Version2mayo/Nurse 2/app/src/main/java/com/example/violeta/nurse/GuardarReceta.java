package com.example.violeta.nurse;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
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


    private long mLastClickTime = 0;
    boolean flag = false;
    View rootView;
    String idP;
    int tag;
    String idMedicina;
    EditText indicaciones;
    ProgressDialog mProgressDialog ;
    ArrayList idMedicinas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.guardar_receta, container, false);
        TextView idPac = (TextView) rootView.findViewById(R.id.idPac);
        TextView medicinasT = (TextView) rootView.findViewById(R.id.medicinas);
        indicaciones = (EditText) rootView.findViewById(R.id.indicaciones);
        idP = getArguments().getString("id");
        idPac.setText("MEDICINAS");
        Button save = (Button) rootView.findViewById(R.id.guardaReceta);
        ArrayList medicinas;
        medicinas = getArguments().getStringArrayList("medicinas");

        String x = "";

        for (int i = 0; i < medicinas.size(); i++) {
            Log.v("MEDICINASRECETa", medicinas.get(i).toString());
            x += medicinas.get(i).toString();
            x += "\n";
            medicinasT.setText(x);
        }


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new RemoteDataTask(rootView).execute();
                // if(flag == true){
                //getActivity().getFragmentManager().popBackStack();
                closefragment();
                //}

            }

        });
        return rootView;
    }
    public void closefragment(){

        getActivity().onBackPressed();
        getActivity().onBackPressed();
    }

    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {

        View rootView;
        TextView medicinasT;
        ArrayList medicinas;
        ArrayList idMedicinas=new ArrayList();
        public RemoteDataTask(View v){
            this.rootView=v;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(getActivity());
            // Set progressdialog title
            mProgressDialog.setTitle("Medicamentos");
            // Set progressdialog message
            mProgressDialog.setMessage("Guardando...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();

            medicinasT = (TextView) rootView.findViewById(R.id.medicinas);
            indicaciones = (EditText) rootView.findViewById(R.id.indicaciones);
            medicinas = getArguments().getStringArrayList("medicinas");

            String x = "";
            for (int i = 0; i < medicinas.size(); i++) {
                Log.v("MEDICINASRECETa", medicinas.get(i).toString());
                x += medicinas.get(i).toString();
                x += "\n";
                medicinasT.setText(x);
            }

        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            //rootView = inflater.inflate(R.layout.guardar_receta, container, false);
            idMedicina = "";

            ParseObject p_m;
            int num=0;
            p_m = new ParseObject("Paciente_medicamento");
            for (int i = 0; i < medicinas.size(); i++) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Medicamento");
                query.whereEqualTo("nombre", medicinas.get(i).toString()).
                        findInBackground(new FindCallback<ParseObject>() {
                            public void done(List<ParseObject> objects, ParseException e) {
                                if (e == null) {
                                    for (ParseObject parseObject : objects) {
                                        //Log.v("Parse Object", parseObject.getObjectId().toString());
                                        idMedicina = parseObject.getObjectId().toString();
                                        idMedicinas.add(idMedicina);
                                    }

                                } else {
                                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                                }
                                if(medicinas.size() == idMedicinas.size()){
                                    for (int i = 0; i < idMedicinas.size(); i++) {
                                        Log.v("Entro", "Entro al for");
                                        ParseObject p_m2 = new ParseObject("Paciente_medicamento");
                                        p_m2.put("id_p", idP);
                                        p_m2.put("periodo", indicaciones.getText().toString());
                                        //p_m2.put("periodo", "PERIODO----->");
                                        p_m2.put("id_m", idMedicinas.get(i).toString());
                                        Log.i("GUARDAR", idMedicinas.get(i).toString());

                                        p_m2.saveInBackground(new SaveCallback() {
                                            @Override
                                            public void done(ParseException e) {
                                                if (e == null) {
                                                    //Toast.makeText(getActivity().getApplicationContext(), "Medicamento guardado", Toast.LENGTH_LONG).show();
                                                    //closefragment();
                                                    Log.i("veces", String.valueOf(tag));
                                                    flag = true;
                                                    tag++;
                                                } else {
                                                    //Toast.makeText(getActivity().getApplicationContext(), "Algo salio mal", Toast.LENGTH_LONG).show();
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                    }

                                }
                            }
                        });

            }
            Log.v("--------------> size",idMedicinas.size()+ "<-----------");
            Log.v("--------> sizemeds",medicinas.size()+ "<--------");

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            // Close the progressdialog
            //closefragment();
            mProgressDialog.dismiss();
        }
    }




}
