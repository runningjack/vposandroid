package com.vpos.amedora.vpos;

import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.vpos.amedora.vpos.helper.Installation;

import java.io.File;


public class MainActivity extends AppCompatActivity {
    Button btnRgister,btnLogin;
    private Handler mHandler = new Handler();
    private static final String INSTALLATION = "INSTALLATION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);

       mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                File installation = new File(getApplicationContext().getFilesDir(),INSTALLATION);
                if(installation.exists()){
                    String AppID = Installation.readInstallationFile(installation);
                    if (AppID ==null){
                        Intent intent = new Intent(MainActivity.this, RegisterActivity1.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(MainActivity.this, MainLandingActivity.class);
                        startActivity(intent);
                    }
                }else{
                }
            }
        }, 4000); // 4 seconds

        btnLogin = (Button)findViewById(R.id.btnstartlogin);
        btnRgister = (Button)findViewById(R.id.btnstartregister);

        btnRgister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity1.class);
                startActivity(intent);
            }
        });
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
