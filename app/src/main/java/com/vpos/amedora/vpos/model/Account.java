package com.vpos.amedora.vpos.model;

/**
 * Created by Amedora on 7/16/2015.
 */
public class Account {
    int id;
    String bank;
    long account_no;
    String app_id;
    int bank_id;
    String bank_code;

    public Account(){
    }

    public Account(String bank, long accNo, String app_id,int bank_id,String bank_code){
        this.bank   =   bank;
        this.account_no = accNo;
        this.bank_id =bank_id;
        this.app_id = app_id;
        this.bank_code = bank_code;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setApp_id(String app_id){
        this.app_id = app_id;
    }

    public void setBank(String bank){
        this.bank = bank;
    }

    public void setBank_id(int bank_id){
        this.bank_id = bank_id;
    }
    public void setAccount_no(long account_no){
        this.account_no = account_no;
    }

    public void setBank_code(String bank_code){ this.bank_code = bank_code;}

    public long getId(){
        return  this.id;
    }

    public String getBank_code(){ return this.bank_code;}

    public long getAccount_no(){
        return this.account_no;
    }

    public int getBank_id(){
        return this.bank_id;
    }

    public String getApp_id(){
        return this.app_id;
    }

    public String getBank(){
        return this.bank;
    }
}
