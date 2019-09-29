package com.demo.pracheta.mygym;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;


public class Tab2History extends Fragment implements OnClickListener{
    //LIST OF ARRAY STRINGS WHICH WILL SERVE
    //AS LIST ITEMS
    TextView text;
    static String s;

    ArrayList<String> Arraylist;
    ArrayAdapter<String> adapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.tab2_history, container, false);
        Button b1=(Button)rootView.findViewById(R.id.addBtn);
       ListView lv=(ListView)rootView.findViewById(R.id.listView);
        text=(TextView)rootView.findViewById(R.id.daate);
        b1.setOnClickListener(this);




        String[] listItems={"apple", "hi"};

            Arraylist=new ArrayList<>(Arrays.asList(listItems));
            adapter=new ArrayAdapter<String>(getContext(), R.layout.simple_list_item_1,R.id.daate, Arraylist);

            lv.setAdapter(adapter);
            lv.setDrawSelectorOnTop(true);



            return rootView;

         }


    @Override
    public void onClick(View v) {
        GymDatabase db=new GymDatabase(getContext());
        try {
            db.open();
            String newItem=db.getHistory();

            if(newItem.equals("No data"))
                Arraylist.add(newItem);
            else
            {
                String str[]=newItem.split("-");
                for (int i=0;i<str.length;i++)
                {
                    Arraylist.add(str[i]);
                }

            }
            adapter.notifyDataSetChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}

