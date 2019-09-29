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

public class Register extends Activity implements View.OnClickListener {
    private Button Reg,ViewDB,Back;
    private  EditText etName, etUN ,etPass,etAge,etEmail,etMobile,etWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName=(EditText)findViewById(R.id.etName);
        etUN=(EditText)findViewById(R.id.etUsername);
        etPass=(EditText)findViewById(R.id.etPassword);
        etAge=(EditText)findViewById(R.id.etAge);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etMobile=(EditText)findViewById(R.id.etPhone);
        etWeight=(EditText)findViewById(R.id.etWeight);

        Reg=(Button)findViewById(R.id.bRegister);
        ViewDB=(Button)findViewById(R.id.bViewDB);
        Back=(Button)findViewById(R.id.bback);
        Reg.setOnClickListener(this);
        ViewDB.setOnClickListener(this);
        Back.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {//Validations
        if(v==Back)
        {
            Intent regIntent= new Intent(Register.this,Login.class);
            Register.this.startActivity(regIntent);
            finish();
        }
        if(v==ViewDB)
        {
            Intent regIntent= new Intent(Register.this,SQLView.class);
            Register.this.startActivity(regIntent);
            finish();
        }
        if(v==Reg)
        {                                     // Field Non empty done for all except gender
            if(etName.getText().toString().length()<=0 || etUN.getText().toString().length()<=0 || etPass.getText().toString().length()<=0||
                    etAge.getText().toString().length()<=0 ||etEmail.getText().toString().length()<=0||etMobile.getText().toString().length()<=0
                    ||etWeight.getText().toString().length()<=0) {

                Context context = getApplicationContext();
                CharSequence text = "Fill all fields";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
            else
            {
                boolean didItWork=true;
                try
                {
                String name=etName.getText().toString();
                String user_name=etUN.getText().toString();
                String password= etPass.getText().toString();
                String age=etAge.getText().toString();
                String email=etEmail.getText().toString();
                String mobile=etMobile.getText().toString();
                String weight=etWeight.getText().toString();

                    System.out.println("Dataaaaa:  " + name + "\t" + password);

                GymDatabase entry=new GymDatabase(Register.this);
                entry.open();
                entry.createEntry(name, user_name, password, age, email, mobile, weight);

                entry.close();


                }
                catch(Exception e)
                {
                    didItWork=false;
                    String error=e.toString();
                    Dialog d=new Dialog(this);
                    d.setTitle("Error!");
                    TextView tv= new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }
                finally {
                    /*if(didItWork)
                    {
                        Dialog d=new Dialog(this);
                        d.setTitle("Yes!");
                        TextView tv= new TextView(this);
                        tv.setText("Success");
                        d.setContentView(tv);
                        d.show();
                    }*/

                }

                Intent regIntent= new Intent(Register.this,Login.class);
                Register.this.startActivity(regIntent);
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
