package com.vpos.amedora.vpos;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Amedora on 8/5/2015.
 */
public class LvAdapter extends BaseAdapter {
    private Activity activity;

    ArrayList<HashMap<String, String>> transList;
    private ArrayList<HashMap<String, String>> data;

    public static String TRANS_ID = "transId";
    public static String TRANS_ACCOUNT_NO = "account_no";
    public static String TRANS_AMOUNT = "amount";
    public static String TRANS_DATE = "transdate";

    private static LayoutInflater inflater=null;
    //public ImageLoader imageLoader;

    public LvAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.transaction_list_layout, null);

        TextView title = (TextView)vi.findViewById(R.id.tvtranstitle); // title
        TextView descp = (TextView)vi.findViewById(R.id.tvdesc); // artist name
        TextView transdate = (TextView)vi.findViewById(R.id.tvtransdate); // duration
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image


        HashMap<String, String> trans = new HashMap<String, String>();
        //HashMap>String, String&gt; song = new HashMap&lt;String, String&gt;();
        trans = data.get(position);

        // Setting all values in listview
        title.setText(trans.get(TransactionListingActivity.TRANS_AMOUNT));
        descp.setText(trans.get(TransactionListingActivity.TRANS_ID));
        transdate.setText(trans.get(TransactionListingActivity.TRANS_DATE));
        //imageLoader.DisplayImage(trans.get(TransactionListingActivity.KEY_THUMB_URL), thumb_image);
        return vi;
    }
}
