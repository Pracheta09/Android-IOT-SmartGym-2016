package com.demo.pracheta.mygym;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.sql.SQLException;

public class SQLView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlview);
        TextView tv= (TextView)findViewById(R.id.tvSQLinfo);

        GymDatabase info=new GymDatabase(this);
        String data=" ";
        try {
            info.open();
            data=info.getData();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception  "+e);
        }
        finally {
            info.close();
        }
        //System.out.println("Query    "+data);

        tv.setText(data);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sqlview, menu);
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
