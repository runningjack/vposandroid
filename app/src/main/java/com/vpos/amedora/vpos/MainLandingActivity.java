package com.vpos.amedora.vpos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Amedora on 7/10/2015.
 */
public class MainLandingActivity extends AppCompatActivity {
    ImageButton imgBtnAddAcc,imgBtnPay,imgBtnList,imgTransList;
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);

        setContentView(R.layout.activity_mainlanding);

        imgBtnAddAcc = (ImageButton)findViewById(R.id.imgBtnMainAddAccount);
        imgBtnList  = (ImageButton)findViewById(R.id.imgBtnAccountListing);
        imgBtnPay   =   (ImageButton)findViewById(R.id.imgBtnMakePayment);
        ImageButton imgBtnTransList = (ImageButton)findViewById(R.id.imgBtnTransactions);

        imgBtnAddAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLandingActivity.this,AddAccountActivity.class);
                startActivity(intent);
            }
        });

        imgBtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLandingActivity.this,AccountListingActivity.class);
                startActivity(intent);

            }
        });

        imgBtnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainLandingActivity.this,ScannerActivity.class);
                startActivity(intent);
            }
        });
        imgBtnTransList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainLandingActivity.this,TransactionListingActivity.class);
                startActivity(intent);
            }
        });

    }
}
