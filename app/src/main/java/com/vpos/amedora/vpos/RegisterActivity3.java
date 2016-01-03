package com.vpos.amedora.vpos;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.vpos.amedora.vpos.helper.DatabaseHelper;
import com.vpos.amedora.vpos.helper.Installation;
import com.vpos.amedora.vpos.model.Apps;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amedora on 7/10/2015.
 */
public class RegisterActivity3 extends AppCompatActivity{
    EditText edtPhone;
    TextView tvMessage;
    String Fname,Lname,Email,Password;
    Button btnNext,btnBack;
    HttpClient httpclient;
    HttpPost httppost;
    String ERRORMSG;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog  =null;
    String Phone ;
    String AppID;
    DatabaseHelper db = new DatabaseHelper(this);
    boolean success = false;
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_register_screen3);
        tvMessage =(TextView)findViewById(R.id.tvMessage3);
        edtPhone = (EditText) findViewById(R.id.edtPhone);



        Bundle bundle = getIntent().getExtras();

        Fname 		= bundle.getString("Fname");
        Lname 		= bundle.getString("Lname");
        Email 		= bundle.getString("Email");
        Password    = bundle.getString("Password");
        AppID       = bundle.getString("AppID");


        btnBack     = (Button) findViewById(R.id.btnregback3);
        btnNext     = (Button) findViewById(R.id.btnregnext3);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = ProgressDialog.show(RegisterActivity3.this, "",
                        "Registering user...", true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                register();
                                handler.removeCallbacks(this);
                                Looper.myLooper().quit();
                            }
                        }, 2000);
                        Looper.loop();
                    }
                }).start();
                if(!success){
                    tvMessage.setText(ERRORMSG);
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

  private void register(){
        try{
            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://mylcpschoolbook.net/VirtualPOS/vposapi/");
            //add your data
            //httppost.setHeader("Content-Type","*/*;charset=UTF-8");

            nameValuePairs = new ArrayList<>(2);
            // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar,
            //Toast.makeText(getApplicationContext(), this.getIntent().getStringExtra("fname").toString().trim(), Toast.LENGTH_LONG).show();

            nameValuePairs.add(new BasicNameValuePair("controller","customer"));
            nameValuePairs.add(new BasicNameValuePair("action",  "create"));
            nameValuePairs.add(new BasicNameValuePair("firstname", Fname));
            nameValuePairs.add(new BasicNameValuePair("lastname", Lname));
            nameValuePairs.add(new BasicNameValuePair("email", Email));
            nameValuePairs.add(new BasicNameValuePair("app_id",AppID));
            nameValuePairs.add(new BasicNameValuePair("key_salt",""));
            nameValuePairs.add(new BasicNameValuePair("phone", edtPhone.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("password",Password));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            //Execute HTTP Post Request
            //response=httpclient.execute(httppost);

            // ResponseHandler<String> responseHandler = new BasicResponseHandler();
            HttpResponse response = httpclient.execute(httppost);

            Integer status = response.getStatusLine().getStatusCode();

            Log.e("STATUS RETURN", status.toString());
            //HttpEntity resEntity = response.getEntity();
            if(status == 200){


                String result = EntityUtils.toString(response.getEntity());
                JSONObject jsonResponse;
                jsonResponse = new JSONObject(result);
                // CONVERT RESPONSE STRING TO JSON ARRAY
                JSONArray ja = jsonResponse.getJSONArray("data");

                // ITERATE THROUGH AND RETRIEVE CLUB FIELDS
                int n = ja.length();
                for (int i = 0; i < n; i++) {
                    // GET INDIVIDUAL JSON OBJECT FROM JSON ARRAY
                    JSONObject jo = ja.getJSONObject(i);
                    success = jo.optBoolean("success");
                    //tvMessage.setText(jo.optString("errmsg"));
                    //userId = jo.getInt("id");
                    ERRORMSG =jo.optString("errmsg");
                    System.out.println("Status " +jo.optString("errmsg"));

                    if(i>0){
                        break;
                    }
                }

                if(success){
                    Apps apps = new Apps();
                    apps.setApp_id(Installation.appId(getApplicationContext()));
                    db.createApp(apps);
                    Intent regIntent = new Intent(RegisterActivity3.this, RegisterActivity4.class);
                    regIntent.putExtra("AppID", Installation.appId(getApplicationContext()));
                    regIntent.putExtra("number",edtPhone.getText().toString());
                    dialog.dismiss();
                    startActivity(regIntent);

                }else{
                    Toast.makeText(RegisterActivity3.this,"Registration was not successful", Toast.LENGTH_SHORT).show();
                    Log.e("ERROR","Unsuccessful");
                }

            }else{
                Toast.makeText(RegisterActivity3.this,"Request could not be reached", Toast.LENGTH_SHORT).show();
                Log.e("ERROR","Unsuccessful");
            }

        }catch(Exception e){
           // dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
            //Log.e("Exception", e.getMessage());
        }
      dialog.dismiss();
    }


}
