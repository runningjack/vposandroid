package com.vpos.amedora.vpos.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.method.DateTimeKeyListener;

import com.vpos.amedora.vpos.model.Account;
import com.vpos.amedora.vpos.model.Apps;
import com.vpos.amedora.vpos.model.Bank;
import com.vpos.amedora.vpos.model.Transaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Amedora on 7/16/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    //Database Helper for ATMCamera




    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "vposManager";

    // Table name
    private static final String TABLE_ACCOUNT = "accounts";
    private static final String TABLE_APP = "apps";
    private static final String TABLE_BANKS ="banks";
    private static final String TABLE_TRANS = "transactions";

    //Generic Fields
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT ="created_at";
    private static final String KEY_UPDATED_AT ="updated_at";

    //fields for account
    private static final String KEY_BANK_ID ="bank_id";
    private static final String KEY_BANK_NAME ="bank";
    private static final String KEY_ACCOUNT_NO ="account_no";
    private static final String KEY_ACCOUNT_APP_ID ="app_id";
    //private static final String KEY_BANK_SORT_CODE ="bank_sort_code";

    //Fields for banks
    private static final String KEY_BANK ="bank_name";
    private static final String KEY_BANK_SHORT_NAME = "short_name";
    private static final String KEY_BANK_CODE ="sort_code";
    private static final String KEY_BANK_ADDRESS = "address";

    //fields for app
    private static final String KEY_APP_ID = "app_id";
    private static final String KEY_APP_STATUS="status";


    //fields for transaction
    private static final String KEY_TRANS_STATUS = "trans_status";
    private static final String KEY_TRANS_ID ="trans_id";
    private static final String KEY_TRANS_AMOUNT = "trans_amount";
    private static final String KEY_TRANS_TYPE = "trans_type";
    private static final String KEY_TRANS_ACCOUNT = "account_no";
    private static final String KEY_TRANS_NARRATION ="narration";




    //string to create database tables
    private static final String CREATE_TABLE_ACCOUNT = " CREATE TABLE "+ TABLE_ACCOUNT +"("+KEY_ID+ " INTEGER PRIMARY KEY,"
            + KEY_BANK_ID +" TEXT, "+KEY_BANK_CODE+" TEXT,"+KEY_BANK_NAME+ " TEXT, "+ KEY_ACCOUNT_NO+" INTEGER,"+ KEY_ACCOUNT_APP_ID+" TEXT,"
            +KEY_CREATED_AT +" DATETIME,"+KEY_UPDATED_AT+ " DATETIME)";

    private static final String CREATE_TABLE_APP = " CREATE TABLE "+TABLE_APP +"("+KEY_ID+" INTEGER PRIMARY KEY,"
            +KEY_BANK_CODE+" TEXT,"+KEY_BANK_NAME+" TEXT,"+KEY_ACCOUNT_NO+" TEXT,"
            +KEY_APP_ID+ " TEXT, "+KEY_APP_STATUS+" INTEGER DEFAULT 0, "+KEY_CREATED_AT+" DATETIME,"+KEY_UPDATED_AT+" DATETIME"+ ")";

    private static final String CREATE_TABLE_BANK = " CREATE TABLE "+TABLE_BANKS + "("+KEY_ID+" INTERGET PRIMARY KEY,"
            +KEY_BANK_SHORT_NAME+ " TEXT, "+KEY_BANK +" TEXT, "+KEY_BANK_CODE+ " TEXT,"+KEY_BANK_ADDRESS+" TEXT,"
            +KEY_CREATED_AT +" DATETIME,"+KEY_UPDATED_AT+ " DATETIME"+")";

    private static final String CREATE_TABLE_TRANSACTION = " CREATE TABLE "+ TABLE_TRANS + "("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_APP_ID+" TEXT,"
            +KEY_TRANS_ID+" TEXT,"+KEY_TRANS_ACCOUNT +" TEXT,"+ KEY_TRANS_AMOUNT +" TEXT, "+ KEY_TRANS_NARRATION +" TEXT,"+KEY_TRANS_TYPE+" TEXT,"+KEY_TRANS_STATUS+" INTEGER, "
            +KEY_CREATED_AT+" DATETIME,"+KEY_UPDATED_AT+" DATETIME)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_ACCOUNT);
        db.execSQL(CREATE_TABLE_APP);
        db.execSQL(CREATE_TABLE_BANK);
        db.execSQL(CREATE_TABLE_TRANSACTION);
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'CBN', 'Central Bank of Nigeria', '001','', '"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'First Bank', 'First Bank of Nigeria Plc', '011','', '"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'MAINSTREET BANK', 'MAINSTREET BANK', '014','', '"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'Citi Bank', 'Citi Ban', '023','', '"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'HERITAGE BANK', 'HERITAGE BANK', '030','', '"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'Union Bank', 'Union Bank Plc', '032','', '"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'UBA', 'United Bank for Africa Plc', '033','', '"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'Wema Bank', 'Wema Bank Plc', '035', '','"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'Access Bank', 'Access Bank Plc', '044','', '"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'Zenith Bank', 'Zenith Bank Plc', '057', '','"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'GTBank', 'Guaranty Trust Bank Plc', '058','', '"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'Diamond Bank', 'Diamond Bank Plc', '063','', '"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'Standard Chartered Bank', 'Standard Chartered Bank', '068','', '"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'Fidelity Bank', 'Fidelity Bank Plc', '070', '','"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'Skye Bank', 'Skye Bank Plc', '076', '','"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'KEYSTONE', 'KEYSTONE', '082','', '"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'ENTERPRISE BANK', 'ENTERPRISE BANK', '084','', '"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'FCMB', 'FIRST CITY MONUMENT BANK PLC', '214','', '"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'Unity Bank', 'Unity Bank Plc', '215', '','"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'Stanbic IBTC', 'Stanbic IBTC Bank Plc', '221','', '"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'Sterling', 'Sterling Bank Plc', '232','', '"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'JAIZ BANK', 'JAIZ BANK', '233', '','"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'ASO SAVINGS', 'ASO SAVINGS AND LOANS PLC', '401','', '"+getDateTime()+"', '"+getDateTime()+"')");
        db.execSQL("INSERT INTO "+TABLE_BANKS+" VALUES (null, 'JUBILEE-LIFE', 'JUBILEE-LIFE MORTGAGE BANK', '402','', '"+getDateTime()+"', '"+getDateTime()+"')");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_APP);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_BANK);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_TRANSACTION);
        onCreate(db);
    }

    public long createAccount(Account account){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ACCOUNT_APP_ID,account.getApp_id());
        values.put(KEY_ACCOUNT_NO,account.getAccount_no());
        values.put(KEY_BANK_CODE,account.getBank_code());
        values.put(KEY_BANK_NAME,account.getBank());
        values.put(KEY_CREATED_AT,getDateTime());
        values.put(KEY_UPDATED_AT,getDateTime());
        long acc_id = db.insert(TABLE_ACCOUNT, null, values);
        return acc_id;
    }

    /**
     * Updating an account
     */
    public int updateAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ACCOUNT_NO, account.getAccount_no());
        values.put(KEY_BANK_NAME, account.getBank());
        values.put(KEY_BANK_CODE,account.getBank_code());
        values.put(KEY_BANK_ID,account.getBank_id());
        values.put(KEY_UPDATED_AT,getDateTime());
        // updating row
        return db.update(TABLE_ACCOUNT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(account.getId()) });
    }

    // Deleting an account
    public void deleteAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACCOUNT, KEY_ID + " = ?",
                new String[] { String.valueOf(account.getId()) });
        db.close();
    }

    public boolean ifExists(Account account){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_ACCOUNT+" WHERE "
                +KEY_BANK_NAME + " ='" +account.getBank()+"' AND "+KEY_ACCOUNT_NO+ "= "+account.getAccount_no()+"";
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()) {
            return true;
        }else{
            return false;
        }
    }

    public List<Account> getAllAccount() {
        List<Account> accountList = new ArrayList<Account>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ACCOUNT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Account contact = new Account();
                contact.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                contact.setBank_id(cursor.getInt(cursor.getColumnIndex(KEY_BANK_ID)));
                contact.setBank(cursor.getString(cursor.getColumnIndex(KEY_BANK_NAME)));
                contact.setAccount_no(cursor.getInt(cursor.getColumnIndex(KEY_ACCOUNT_NO)));
                contact.setApp_id(cursor.getString(cursor.getColumnIndex(KEY_ACCOUNT_APP_ID)));
                // Adding contact to list
                accountList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return accountList;
    }


    /**
     * Bank Detail
     **/
    public long createBank(Bank bank){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BANK,bank.getBank_name());
        values.put(KEY_BANK_SHORT_NAME,bank.getShort_name());
        values.put(KEY_BANK_CODE,bank.getSort_code());
        values.put(KEY_BANK_ADDRESS,bank.getAddress());
        values.put(KEY_CREATED_AT,getDateTime());
        values.put(KEY_UPDATED_AT,getDateTime());
        long bank_id = db.insert(TABLE_BANKS, null, values);
        return bank_id;

    }

    /**
     * Updating an account
     */
    public int updateBank(Bank bank) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BANK,bank.getBank_name());
        values.put(KEY_BANK_SHORT_NAME,bank.getShort_name());
        values.put(KEY_BANK_CODE,bank.getSort_code());
        values.put(KEY_BANK_ADDRESS,bank.getAddress());
        values.put(KEY_UPDATED_AT,getDateTime());
        // updating row
        return db.update(TABLE_BANKS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(bank.getId()) });
    }


    public List<Bank> getAllBank() {
        List<Bank> bankList = new ArrayList<Bank>();
        //Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BANKS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Bank bank = new Bank();
                bank.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                bank.setShort_name(cursor.getString(cursor.getColumnIndex(KEY_BANK_SHORT_NAME)));
                bank.setBank_name(cursor.getString(cursor.getColumnIndex(KEY_BANK)));
                bank.setSort_code(cursor.getString(cursor.getColumnIndex(KEY_BANK_CODE)));
                bank.setAddress(cursor.getString(cursor.getColumnIndex(KEY_BANK_ADDRESS)));
                //Adding contact to list
                bankList.add(bank);
            } while (cursor.moveToNext());
        }
        //Return contact list
        return bankList;
    }



    public boolean ifExists(Bank bank){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_BANKS+" WHERE "
                +KEY_BANK + " ='" +bank.getBank_name()+"' AND "+KEY_BANK_CODE+ "= '"+bank.getSort_code()+"'";
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()) {
            return true;
        }else{
            return false;
        }
    }

    public String getBankSortCode(String bankName){
        String sortCode = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_BANKS+" WHERE "
                + KEY_BANK_SHORT_NAME +" ='"+bankName+"'";
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            sortCode = cursor.getString(cursor.getColumnIndex(KEY_BANK_CODE));
        }

        return sortCode;
    }

    public Bank getBankByName(String bankName){

        Bank bank = new Bank();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_BANKS+" WHERE "
                + KEY_BANK_SHORT_NAME +" ='"+bankName+"'";
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            bank.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            bank.setShort_name(cursor.getString(cursor.getColumnIndex(KEY_BANK_SHORT_NAME)));
            bank.setBank_name(cursor.getString(cursor.getColumnIndex(KEY_BANK)));
            bank.setSort_code(cursor.getString(cursor.getColumnIndex(KEY_BANK_CODE)));
            bank.setAddress(cursor.getString(cursor.getColumnIndex(KEY_BANK_ADDRESS)));
        }
        return bank;
    }




    //creates application data
    public long createApp(Apps app){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_APP_ID,app.getApp_id());
        values.put(KEY_CREATED_AT,getDateTime());
        values.put(KEY_UPDATED_AT,getDateTime());
        long acc_id = db.insert(TABLE_APP, null, values);
        return acc_id;
    }

    /**
     * Updating application info
     */
    public int updateApp(Apps apps) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_APP_ID,apps.getApp_id());
        values.put(KEY_APP_STATUS,apps.getStatus());

        values.put(KEY_UPDATED_AT,getDateTime());
        // updating row
        return db.update(TABLE_APP, values, KEY_ID + " = ?",
                new String[] { String.valueOf(apps.getId()) });
    }


    public Apps getApp(String app_id){
        Apps apps= new Apps();
        String sortCode = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_APP+" WHERE "
                + KEY_APP_ID +" ='"+app_id+"'";
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor != null) {
            cursor.moveToFirst();
            apps.setApp_id(cursor.getString(cursor.getColumnIndex(KEY_APP_ID)));
            apps.setStatus(cursor.getInt(cursor.getColumnIndex(KEY_APP_STATUS)));
            apps.setCreated_at(cursor.getString(cursor.getColumnIndex(KEY_CREATED_AT)));
            apps.setUpdated_at(cursor.getString(cursor.getColumnIndex(KEY_UPDATED_AT)));
        }
        return apps;
    }


    //Create a new transaction

    public Long createTransaction(Transaction transaction){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_APP_ID,transaction.getApp_id());
        values.put(KEY_TRANS_ACCOUNT,transaction.getAccount_no());
        values.put(KEY_TRANS_AMOUNT,transaction.getTrans_amount());
        values.put(KEY_TRANS_ID,transaction.getTransId());
        values.put(KEY_TRANS_TYPE,transaction.getTrans_type());
        values.put(KEY_TRANS_STATUS,transaction.getStatus());
        values.put(KEY_TRANS_NARRATION,transaction.getNarration());
        values.put(KEY_CREATED_AT,getDateTime());
        values.put(KEY_UPDATED_AT,getDateTime());
        Long trans_row_id = db.insert(TABLE_TRANS,null,values);
        return trans_row_id;
    }

    public int updateTransaction(Transaction transaction){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_APP_ID,transaction.getApp_id());
        values.put(KEY_TRANS_ACCOUNT,transaction.getAccount_no());
        values.put(KEY_TRANS_AMOUNT,transaction.getTrans_amount());
        values.put(KEY_TRANS_ID,transaction.getTransId());
        values.put(KEY_TRANS_TYPE,transaction.getTrans_type());
        values.put(KEY_TRANS_STATUS,transaction.getStatus());
        values.put(KEY_TRANS_NARRATION,transaction.getNarration());
        values.put(KEY_UPDATED_AT,getDateTime());

        // updating row
        return db.update(TABLE_TRANS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(transaction.getId()) });
    }


    // Deleting an account
    public void deleteTransaction(Transaction account) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRANS, KEY_ID + " = ?",
                new String[] { String.valueOf(account.getId()) });
        db.close();
    }

    public List<Transaction> getAllTransactions(){
        List<Transaction> transList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+ TABLE_TRANS;
        Cursor c =db.rawQuery(sql,null);

        if(c != null){
            if(c.moveToFirst()){
                do{
                    Transaction transaction = new Transaction();
                    transaction.setApp_id(c.getString(c.getColumnIndex(KEY_APP_ID)));
                    transaction.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                    transaction.setStatus(c.getInt(c.getColumnIndex(KEY_TRANS_STATUS)));
                    transaction.setAccount_no(c.getString(c.getColumnIndex(KEY_TRANS_ACCOUNT)));
                    transaction.setNarration(c.getString(c.getColumnIndex(KEY_TRANS_NARRATION)));
                    transaction.setTrans_amount(c.getDouble(c.getColumnIndex(KEY_TRANS_AMOUNT)));
                    transaction.setTrans_type(c.getString(c.getColumnIndex(KEY_TRANS_TYPE)));
                    transaction.setCreated_at(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
                    transaction.setUpdated_at(c.getString(c.getColumnIndex(KEY_UPDATED_AT)));
                    transList.add(transaction);
                }while (c.moveToNext());
            }
        }
        return transList;
    }

    public Transaction getTransById(String trans_id){
        Transaction transaction = new Transaction();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_TRANS+" WHERE "+KEY_TRANS_ID+ "='"+trans_id+"'";
        Cursor c = db.rawQuery(sql,null);

        if(c != null){
            c.moveToFirst();
            transaction.setApp_id(c.getString(c.getColumnIndex(KEY_APP_ID)));
            transaction.setId(c.getInt(c.getColumnIndex(KEY_ID)));
            transaction.setStatus(c.getInt(c.getColumnIndex(KEY_TRANS_STATUS)));
            transaction.setAccount_no(c.getString(c.getColumnIndex(KEY_TRANS_ACCOUNT)));
            transaction.setNarration(c.getString(c.getColumnIndex(KEY_TRANS_NARRATION)));
            transaction.setTrans_amount(c.getDouble(c.getColumnIndex(KEY_TRANS_AMOUNT)));
            transaction.setTrans_type(c.getString(c.getColumnIndex(KEY_TRANS_TYPE)));
            transaction.setCreated_at(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
            transaction.setUpdated_at(c.getString(c.getColumnIndex(KEY_UPDATED_AT)));
        }

        return  transaction;
    }

    public int getLastTransId(){
        int sortCode=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+ TABLE_TRANS+" ORDER BY "+KEY_ID+" DESC LIMIT 1";

        Cursor c =db.rawQuery(sql,null);
        if( c.getCount()>0){
            c.moveToFirst();
            sortCode = c.getInt(c.getColumnIndex(KEY_ID));
        }
        if(sortCode !=0)
            return sortCode;
        return 1;
    }

    //gets the current date
    public String getDateTime(){
        String myDate;
        Date dt = new  Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        myDate =dateFormat.format(date);
        return myDate;
    }

}
