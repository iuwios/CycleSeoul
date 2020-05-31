package com.example.djeon.cycleseoul;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by DJeon on 2019. 3. 30..
 */

public class FragList extends Fragment {

    TextView text;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;

    public FragList() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag,container,false);

        text = (TextView)view.findViewById(R.id.textView);
        text2 = (TextView)view.findViewById(R.id.textGwang);
        text3 = (TextView)view.findViewById(R.id.textInsa);
        text4 = (TextView)view.findViewById(R.id.textChong);
        text5 = (TextView)view.findViewById(R.id.textWorld);

        String strtext = getArguments().getString("edttext");
        Toast.makeText(getActivity(), "String:" +strtext, Toast.LENGTH_SHORT).show();

        switch(strtext){
            case "Course1":

                text.setVisibility(View.VISIBLE);
                text2.setVisibility(View.INVISIBLE);
                text3.setVisibility(View.INVISIBLE);
                text4.setVisibility(View.INVISIBLE);
                text5.setVisibility(View.INVISIBLE);

                break;
            case "Course2":
                text.setVisibility(View.INVISIBLE);
                text3.setVisibility(View.INVISIBLE);
                text4.setVisibility(View.INVISIBLE);
                text5.setVisibility(View.INVISIBLE);
                text2.setVisibility(View.VISIBLE);

                break;
            case "Course3":
                text.setVisibility(View.INVISIBLE);
                text2.setVisibility(View.INVISIBLE);
                text3.setVisibility(View.VISIBLE);
                text4.setVisibility(View.INVISIBLE);
                text5.setVisibility(View.INVISIBLE);

                break;
            case "Course4":
                text.setVisibility(View.INVISIBLE);
                text2.setVisibility(View.INVISIBLE);
                text3.setVisibility(View.INVISIBLE);
                text4.setVisibility(View.VISIBLE);
                text5.setVisibility(View.INVISIBLE);

                break;
            case "Course5":
                text.setVisibility(View.INVISIBLE);
                text2.setVisibility(View.INVISIBLE);
                text3.setVisibility(View.INVISIBLE);
                text4.setVisibility(View.INVISIBLE);
                text5.setVisibility(View.VISIBLE);

                break;

            default:

                break;
        }

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.INVISIBLE);
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.INVISIBLE);
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.INVISIBLE);
            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.INVISIBLE);
            }
        });

        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.INVISIBLE);
            }
        });

        return view;

    }



}

