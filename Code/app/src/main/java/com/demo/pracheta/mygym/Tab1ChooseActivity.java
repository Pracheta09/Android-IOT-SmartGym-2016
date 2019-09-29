package com.demo.pracheta.mygym;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by Pracheta on 11/5/2016.
 */
public class Tab1ChooseActivity extends Fragment implements View.OnClickListener {
    public boolean stop_flag=false;
     public float DumbbellWeight=0;
    long timeWhenStopped=0;
    int i=0;
    private  TextView weight,count;
    private Button start,reset,stop,disconnect;
    private Chronometer chrono;
    ImageView iv;
   static String pcon="1",con="0";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.tab1_choose_activity, container, false);
        chrono=(Chronometer) rootView.findViewById(R.id.chronometer);

        weight=(TextView)rootView.findViewById(R.id.tvWeight);
        count=(TextView)rootView.findViewById(R.id.tvcount);

        iv=(ImageView)rootView.findViewById(R.id.ivWeightDumbbell);

        start=(Button)rootView.findViewById(R.id.bStart);
        stop=(Button)rootView.findViewById(R.id.bStop);
        reset=(Button)rootView.findViewById(R.id.bReset);
        disconnect=(Button)rootView.findViewById(R.id.bDisconnect);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        disconnect.setOnClickListener(this);
        reset.setOnClickListener(this);
        iv.setOnClickListener(this);

        return rootView;
    }
    void run()
    {
        Networking.startNetwork();
        pcon=con;
        con = Networking.receivedstring;
        //Toast toast1 = Toast.makeText(context, text, duration);
        //toast1.setGravity(Gravity.CENTER, 0, 0);
        //toast1.show();
        count.setText(con);
    }

    @Override
    public void onClick(View v) {
        if(v==start)
        {
            chrono.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);

            chrono.start();
            Networking.startNetwork();
            if(!Networking.receivedstring.equals("Nodata"))
            {
                /*Context context = getApplicationContext();
                CharSequence text = Networking.receivedstring;
                int duration = Toast.LENGTH_LONG;


                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();*/


                UDP_Client.startSending("start");
                if(UDP_Client.flag==1) {
                    Context context = getContext();
                    CharSequence text = "sent";
                    int duration = Toast.LENGTH_SHORT;


                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER, 0, 0);

                    toast.show();
                }
                chrono.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);

                chrono.start();

                   //Thread s = new Thread();
                    //s.start();

                Log.e(getTag(),"maman");
                        while (!pcon.equals(con)) {
                           // UDP_Client.startSending("No data");
                            pcon = con;
                            //Log.e(getTag(),"sama");
                            //Networking.startNetwork();
                            //con = Networking.receivedstring1+"";
//                            Context context = getContext();
//                            CharSequence text = "sent";
//                            int duration = Toast.LENGTH_SHORT;
//
//                            Toast toast1 = Toast.makeText(context, text, duration);
//                            toast1.setGravity(Gravity.CENTER, 0, 0);
//                            toast1.show();
                            //count.setText(con);
                        //if()
                        //{
                            //count.setText(con);

                       // }
                        }




            }
            else
            {
                /*Context context = getContext();
                CharSequence text = "Punch Card";
                int duration = Toast.LENGTH_LONG;


                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();*/
            }

        }
        if(v==stop)
        {
            stop_flag=true;
            UDP_Client.startSending("pause");

           /* if(UDP_Client.flag==1) {
                Context context = getContext();
                CharSequence text = "pause";
                int duration = Toast.LENGTH_SHORT;


                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);

                toast.show();
                Networking.startNetwork();
                con = Networking.receivedstring+"";
                count.setText("15");
            }*/
            count.setText("15");
            timeWhenStopped = chrono.getBase() - SystemClock.elapsedRealtime();
            chrono.stop();
        }
       /* if(v==reset)
        {
            chrono.setBase(SystemClock.elapsedRealtime());
            timeWhenStopped = 0;
            i=0;
            count.setText("0");
            chrono.stop();
        }*/
        if(v==disconnect)
        {
            Tab2History hist=new Tab2History();
            UDP_Client.startSending("disconnect");

            //Networking.startNetwork();

            boolean didItWork=true;

                    GymDatabase entry=new GymDatabase(getContext());
                    String time=" 15" ;
                    //int temp=(Integer.parseInt(con)/15);
                    String set=con;
                    String cal="12";

                    String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            try {
                entry.open();
            } catch (SQLException e) {

            }
            entry.createEntryHistory(formattedDate, time, set, cal);
                    entry.close();



            if(didItWork) {

                //Context context = getApplicationContext();
                CharSequence text = "Data is added";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(getContext(), text, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
//                Intent regIntent = new Intent(Tab1ChooseActivity.this, MainPage.class);
//                Tab1ChooseActivity.this.startActivity(regIntent);
//                finish();finish
            }
            chrono.setBase(SystemClock.elapsedRealtime());
            timeWhenStopped = 0;
            i=0;
            count.setText("0");
            chrono.stop();



        }

        if(v==iv)
        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setTitle("Dumbbell Weight");

            // Setting Dialog Message
            alertDialog.setMessage("Enter Weight (Kg)");
            final EditText input = new EditText(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            input.setRawInputType(Configuration.KEYBOARD_12KEY);
            input.setLayoutParams(lp);
            alertDialog.setView(input); // uncomment this line
            //alertDialog.setView(input);

            // Setting Icon to Dialog
            alertDialog.setIcon(R.drawable.dumbbell);

            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            // Write your code here to execute after dialog
                            if(input.getText().toString().equals("")||input.getText().toString().equals(null))
                            {

                            }
                            else
                            DumbbellWeight=Float.parseFloat(input.getText().toString());

                        }
                    });
            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog
                            dialog.cancel();
                        }
                    });

            // closed

            // Showing Alert Message
            alertDialog.show();
        }


    }

        /*
        restart.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			focus.setBase(SystemClock.elapsedRealtime());
		}
	});


        set.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			focus.setFormat("Formated Time - %s");
		}
	});


        clear.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			focus.setFormat(null);
		}
	});
         */

    }
