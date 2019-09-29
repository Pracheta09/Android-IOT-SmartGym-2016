package com.demo.pracheta.mygym;

import android.app.Activity;
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

public class Login extends Activity {
    GymDatabase validation;
    private EditText etusername,etpassword;
    public static String username;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button login=(Button) findViewById(R.id.bLogin);
        final TextView registerlink= (TextView) findViewById(R.id.tvRegister);

        etusername= (EditText)findViewById(R.id.etUsername);
        etpassword= (EditText)findViewById(R.id.etPassword);

       validation=new GymDatabase(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                username=etusername.getText().toString();
                password=etpassword.getText().toString();

            if(username.length()<=0 || password.length()<=0) {
                Context context = getApplicationContext();
                CharSequence text = "Enter username and password";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
            else {


                String data=" ";
                try {
                    validation.open();
                    data=validation.getValidation(username);

                    if(data.equals(password))

                    {
                        Intent regIntent= new Intent(Login.this,MainPage.class);
                        Login.this.startActivity(regIntent);
                        finish();



                    }
                    else
                    {



                        Context context = getApplicationContext();
                        CharSequence text = "Invalid username/password";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Exception  "+e);
                }
                finally {
                    validation.close();
                }





            }
            }
        });

        registerlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent regIntent= new Intent(Login.this,Register.class);
                Login.this.startActivity(regIntent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
