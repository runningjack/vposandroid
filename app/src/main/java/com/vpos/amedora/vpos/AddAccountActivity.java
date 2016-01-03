package com.vpos.amedora.vpos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.vpos.amedora.vpos.helper.DatabaseHelper;
import com.vpos.amedora.vpos.helper.Validation;
import com.vpos.amedora.vpos.model.Account;
import com.vpos.amedora.vpos.model.Bank;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Amedora on 7/16/2015.
 */
public class AddAccountActivity extends AppCompatActivity {
    DatabaseHelper db = new DatabaseHelper(this);
    Spinner spnBank;
    Button  btnAddAcc;
    String  bank;
    EditText edtAccountno;
    String accno;
    Long success;
    EditText edtBank;
    Account account2;
    ArrayAdapter<CharSequence> adapter;
    ArrayList<HashMap<String, String>> bankList;
    public static String TAG_BANK_NAME,TAG_SHORT_NAME;
    String StoreName,StoreContact,SEmail,pass,bankName,accNo;

    String[] dias;

 protected void onCreate(Bundle savedInstanceState){
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_addaccount);
     spnBank = (Spinner)findViewById(R.id.spinner);
     adapter = ArrayAdapter.createFromResource(this,R.array.banklist,android.R.layout.simple_spinner_item);
     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     edtAccountno = (EditText) findViewById(R.id.edtAccNo);
     spnBank.setAdapter(adapter);
     btnAddAcc = (Button)findViewById(R.id.btnAddAcc);
     account2 = new Account();

     spnBank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             ((TextView) parent.getChildAt(0)).setTextColor(Color.YELLOW);
             ((TextView) parent.getChildAt(0)).setTextSize(25);
             bankName = parent.getItemAtPosition(position).toString();
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
     });
     bankList = new ArrayList<HashMap<String, String>>();
     List<Bank> bank = db.getAllBank();
     System.out.println(bank.size());
     // loop through each website
     dias = new String[bank.size()];
     for (int i = 0; i < bank.size(); i++) {
         Bank s = bank.get(i);
         // creating new HashMap
         HashMap<String, String> map = new HashMap<String, String>();
         // adding each child node to HashMap key => value

         map.put(TAG_BANK_NAME, String.valueOf(s.getBank_name()));
         // adding HashList to ArrayList
         bankList.add(map);
         // add sqlite id to array
         // used when deleting a website from sqlite
         dias[i] = String.valueOf(s.getShort_name());
     }


     ArrayAdapter adp = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dias);
     adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     spnBank.setAdapter(adp);



     //edtBank.setText(null);


     spnBank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             ((TextView) parent.getChildAt(0)).setTextColor(Color.YELLOW);
             ((TextView) parent.getChildAt(0)).setTextSize(25);
             bankName = parent.getItemAtPosition(position).toString();
             //edtBank.setText(parent.getItemAtPosition(position).toString());
             account2.setBank(parent.getItemAtPosition(position).toString());
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
     });



     btnAddAcc.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if(edtAccountno.length()>0) {
                account2.setAccount_no(Long.valueOf(edtAccountno.getText().toString()));
            }
            if(!db.ifExists(account2)){
                if(createAccount()){
                    Intent intent = new Intent(AddAccountActivity.this,AccountListingActivity.class);
                    startActivity(intent);
                }else{

                    Toast.makeText(getBaseContext(),"Invalid Input",Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getBaseContext(),"Record already existing",Toast.LENGTH_LONG).show();
            }
         }
     });
 }
    private boolean createAccount(){
        if(checkValidation()){
            Account account = new Account();
            account.setBank(bankName);
            Bank bank2 = db.getBankByName(bankName);
            account.setBank_code(bank2.getSort_code());
            accno = edtAccountno.getText().toString();
            account.setAccount_no( Long.valueOf(accno));
            success = db.createAccount(account);
            if(success != null)
                return true;
            return false ;
        }
        return false;
    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.hasText(edtAccountno)) ret = false;
        if (!Validation.isAccountNo(edtAccountno, 10)) ret = false;
        //if (!Validation.isPhoneNumber(edtLname, false)) ret = false;

        return ret;
    }
}
