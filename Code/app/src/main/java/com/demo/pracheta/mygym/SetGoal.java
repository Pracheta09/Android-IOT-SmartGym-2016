package com.demo.pracheta.mygym;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SetGoal extends Activity implements View.OnClickListener {
    EditText set,time;
    Button setGoal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);
        set=(EditText)findViewById(R.id.etSet);
       time=(EditText)findViewById(R.id.etTime);
        setGoal=(Button)findViewById(R.id.BsetGoal);
        setGoal.setOnClickListener(this);
        /*Database
        Table:History
        Columns: Date Goal_Time Goal_Set Achieved_Time Achieved_Set Calories

        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_goal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        boolean didItWork=true;
        try {
            GymDatabase entry=new GymDatabase(SetGoal.this);
            String time1=time.getText().toString();
            String set1=set.getText().toString();
            String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            entry.open();
            entry.createEntryGoal(formattedDate, time1, set1);
            entry.close();
        } catch (SQLException e) {
            e.printStackTrace();
            didItWork=false;
            String error=e.toString();
            Dialog d=new Dialog(this);
            d.setTitle("Error!");
            TextView tv= new TextView(this);
            tv.setText(error);
            d.setContentView(tv);
            d.show();
        }

        if(didItWork) {

            Context context = getApplicationContext();
            CharSequence text = "Your goal is set";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            Intent regIntent = new Intent(SetGoal.this, MainPage.class);
            SetGoal.this.startActivity(regIntent);
            finish();
        }
    }
}
