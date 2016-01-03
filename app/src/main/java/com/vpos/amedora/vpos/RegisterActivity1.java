package com.vpos.amedora.vpos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.vpos.amedora.vpos.helper.Validation;


/**
 * Created by Amedora on 7/10/2015.
 */
public class RegisterActivity1 extends AppCompatActivity {
    EditText edtFname,edtLname,edtEmail;
    String Fname,Lname,Email;
    Button btnNext;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_register_screen1);
        btnNext = (Button) findViewById(R.id.btnregnext1);
        edtFname = (EditText)findViewById(R.id.edtFirstname);
        edtLname = (EditText)findViewById(R.id.edtLastname);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        Fname   = edtFname.getText().toString();
        Lname   =   edtLname.getText().toString();
        Email   = edtEmail.getText().toString();

        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                Validation.isEmailAddress(edtEmail, true);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValidation ()){
                    sendData();
                }else{
                    Toast.makeText(getApplicationContext(),
                            "firstname or lastname or email fields must not be empty",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void sendData(){
// Submit your form here. your form is valid

        Toast.makeText(this, "Submitting form...", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(RegisterActivity1.this,RegisterActivity2.class);

        intent.putExtra("Fname",edtFname.getText().toString());
        intent.putExtra("Lname",edtLname.getText().toString());
        intent.putExtra("Email",edtEmail.getText().toString());
        startActivity(intent);
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


    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.hasText(edtFname)) ret = false;
        if (!Validation.isEmailAddress(edtEmail, true)) ret = false;
        //if (!Validation.isPhoneNumber(edtLname, false)) ret = false;

        return ret;
    }



}
