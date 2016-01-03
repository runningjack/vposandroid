package com.vpos.amedora.vpos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vpos.amedora.vpos.helper.DatabaseHelper;
import com.vpos.amedora.vpos.model.Transaction;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by Amedora on 8/5/2015.
 */
public class TransactionListingActivity extends AppCompatActivity {
    public static String TRANS_ID = "transId";
    public static String TRANS_ACCOUNT_NO = "account_no";
    public static String TRANS_AMOUNT = "amount";
    public static String TRANS_DATE = "transdate";
    ListView lv ;
    DatabaseHelper db = new DatabaseHelper(this);
    ArrayList<HashMap<String, String>> transList;
    LvAdapter adapter;
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_transactions);
        lv = (ListView)findViewById(R.id.lvtransactions);

        transList = new ArrayList<HashMap<String, String>>();

        // List<WebSite> siteList = rssDb.getAllSites();
        List<Transaction> transactions = db.getAllTransactions();

        //sqliteIds = new String[transactions.size()];

        // loop through each website
        for (int i = 0; i < transactions.size(); i++) {

            Transaction s = transactions.get(i);
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            // adding each child node to HashMap key => value

            Double amount = new Double(s.getTrans_amount());

            NumberFormat numberFormatter;

            String amountOut;

            numberFormatter = NumberFormat.getNumberInstance(Locale.US);

            amountOut = numberFormatter.format(amount);



            map.put(TRANS_DATE, String.valueOf(s.getCreated_at()));
            map.put(TRANS_ID, String.valueOf(s.getTransId()));
            map.put(TRANS_AMOUNT, String.valueOf("₦" + amountOut));
            // adding HashList to ArrayList
            transList.add(map);

            // add sqlite id to array
            // used when deleting a website from sqlite
            //sqliteIds[i] = String.valueOf(s.getId());
        }
        /**
         * Updating list view with websites
         * */

        adapter=new LvAdapter(this, transList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
