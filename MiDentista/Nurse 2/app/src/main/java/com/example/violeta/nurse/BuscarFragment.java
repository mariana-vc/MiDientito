package com.example.violeta.nurse;

import android.content.Intent;
import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class BuscarFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_buscar,
                container, false);
        Button button = (Button) view.findViewById(R.id.hecho);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(getActivity(), BuscarPaciente.class);
                startActivity(intent);
            }
        });

//------------------------------------------------------------------------------------------------------------------->
        ImageButton button2 = (ImageButton) view.findViewById(R.id.imageView4);
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent();
                intent.setClass(getActivity(), BuscarPaciente.class);
                startActivity(intent);
            }
        });
        return view;
    }








    }



