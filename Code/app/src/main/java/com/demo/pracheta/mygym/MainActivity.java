package com.demo.pracheta.mygym;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(SaveSharedPreferences.getUserName(MainActivity.this).length() == 0)
        {
            // call Login Activity
        }
        else
        {
            // Stay at the current activity.
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        new CountDownTimer(2000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                if(SaveSharedPreferences.getUserName(MainActivity.this).length() == 0)
                {
                    startActivity(new Intent(MainActivity.this,Login.class));
                    finish();
                }
                else
                {
                    startActivity(new Intent(MainActivity.this,MainPage.class));
                    finish();
                }

            }
        }.start();



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
