package com.example.djeon.cycleseoul;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by DJeon on 2019. 3. 30..
 */

public class FragList extends Fragment {

    TextView text;

    public FragList() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag,container,false);

        text = (TextView)view.findViewById(R.id.textView);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.INVISIBLE);
            }
        });

        return view;

    }



}


    /*View view =  inflater.inflate(R.layout.frag,container,false);
    final String routes[] = {"Wonnam_Course","B_Course","C_Course"};


    ListView listView = (ListView)view.findViewById(R.id.listV);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,routes);
    listView.setAdapter(adapter);
    //listRoute.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(routes[i]) {
                    case "Wonnam_Course":
                        String won = "WonReady";
                        //Intent
                        Intent s = new Intent(getActivity().getBaseContext(), MainActivity.class);
                        //s.putExtra(SENDER_KEY, "FragList");
                        s.putExtra("theRoute", won.toString());

                        //Send
                        getActivity().startActivity(s.addFlags(FLAG_ACTIVITY_NEW_TASK));

                        break;

                    case "B_Course":
                        Toast.makeText(getActivity(), "B Hello", Toast.LENGTH_SHORT).show();
                        break;
                    case "C_Course":
                        Toast.makeText(getActivity(), "C Hello", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getActivity(), "NNNNHello", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });



        return view;

    }

}*/
