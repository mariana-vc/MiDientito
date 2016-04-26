package com.example.violeta.nurse;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;


public class ResgistrarFragment extends Fragment  {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_resgistrar_fragment,
                container, false);
        Button button = (Button) view.findViewById(R.id.hecho);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent();
                intent.setClass(getActivity(), AgregarPaciente.class);
                startActivity(intent);
            }
        });


//------------------------------------------------------------------------------------------------------------------->

        ImageButton button2 = (ImageButton) view.findViewById(R.id.imageView3);
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent();
                intent.setClass(getActivity(), AgregarPaciente.class);
                startActivity(intent);
            }
        });


        return view;
    }



}