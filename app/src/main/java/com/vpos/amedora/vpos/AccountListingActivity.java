package com.vpos.amedora.vpos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.vpos.amedora.vpos.helper.DatabaseHelper;
import com.vpos.amedora.vpos.model.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Amedora on 7/16/2015.
 */
public class AccountListingActivity extends AppCompatActivity {
   ListView lvAcc;
    ArrayList<HashMap<String, String>> accountList;

    public static String TAG_ID = "id";
    public static String TAG_ACCOUNT_NO = "account_no";
    public static String TAG_BANK = "bank";
    String[] sqliteIds;
    private ArrayList<Map> data;
    DatabaseHelper db = new DatabaseHelper(this);
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_accountlisting);
        lvAcc = (ListView)findViewById(R.id.lvAccounts);
        //DatabaseHelper db = new DatabaseHelper(this);
        //List<Account> account = db.getAllAccount();
       // ArrayAdapter<Account> adapter = new ArrayAdapter<Account>(this, android.R.layout.simple_list_item_1,account);
       // lvAcc.setAdapter(adapter);

        accountList = new ArrayList<HashMap<String, String>>();

       // List<WebSite> siteList = rssDb.getAllSites();
        List<Account> account = db.getAllAccount();

        sqliteIds = new String[account.size()];

        // loop through each website
        for (int i = 0; i < account.size(); i++) {

            Account s = account.get(i);
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            // adding each child node to HashMap key => value
            map.put(TAG_ID, String.valueOf(s.getId()));
            map.put(TAG_ACCOUNT_NO, String.valueOf(s.getAccount_no()));
            map.put(TAG_BANK, s.getBank());
            // adding HashList to ArrayList
            accountList.add(map);
            // add sqlite id to array
            // used when deleting a website from sqlite
            sqliteIds[i] = String.valueOf(s.getId());
        }
        /**
         * Updating list view with websites
         * */
        ListAdapter adapter = new SimpleAdapter(
                AccountListingActivity.this,
                accountList, R.layout.accountview,
                new String[] { TAG_ID, TAG_ACCOUNT_NO, TAG_BANK },
                new int[] { R.id.tvAccNoLview, R.id.tvAccLview, R.id.tvBankLview });
        // updating listview
        lvAcc.setAdapter(adapter);
        //registerForContextMenu(lv);

        lvAcc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }
}
