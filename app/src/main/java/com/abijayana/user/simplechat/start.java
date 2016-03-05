package com.abijayana.user.simplechat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by user on 05-03-2016.
 */
public class start extends Activity {
    SharedPreferences ui;int chk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui=this.getSharedPreferences("ABI", Context.MODE_PRIVATE);
        chk=ui.getInt("CHECK",0);
        if(chk==0){
            try {
                Class g=Class.forName("com.abijayana.user.simplechat.MainActivity");
                Intent y=new Intent(start.this,g);
                startActivity(y);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
        else if(chk==1){
            try {
                Class g=Class.forName("com.abijayana.user.simplechat.Main");
                Intent o=new Intent(start.this,g);
                startActivity(o);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
    }
}
