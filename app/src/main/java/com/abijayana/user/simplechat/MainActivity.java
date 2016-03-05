package com.abijayana.user.simplechat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Spinner s;EditText ed,pss;String strg;String  sx="http://img06.deviantart.net/df34/i/2015/119/8/9/kyoya_by_white_mysterion-d3id1wa.png";
    long r=0;int egf=0;String passw,comm;
    String[] d={"Male","Female"};Button log;Firebase fr;SharedPreferences sp,ps,kl,ui;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        ed=(EditText)findViewById(R.id.edittxt);
        pss=(EditText)findViewById(R.id.editpass);
        log=(Button)findViewById(R.id.buttonlog);
        log.setVisibility(View.INVISIBLE);
        sp=this.getSharedPreferences("AMMAACHAN", Context.MODE_PRIVATE);
        ps=this.getSharedPreferences("ACHANAMMA",Context.MODE_PRIVATE);
        kl=this.getSharedPreferences("ABIJAY",Context.MODE_PRIVATE);
        ui=this.getSharedPreferences("ABI",Context.MODE_PRIVATE);


        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed.setHint("");
            }
        });

        s=(Spinner)findViewById(R.id.spinr);
        ArrayAdapter<String> adp=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,d);
        s.setAdapter(adp);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int c = s.getSelectedItemPosition();
                if (c == 0) {

                    sx = "http://img06.deviantart.net/df34/i/2015/119/8/9/kyoya_by_white_mysterion-d3id1wa.png";

                } else if (c == 1) {

                    sx = "http://img11.deviantart.net/de12/i/2013/327/b/c/png_anime_girl___by_katlee_by_katlee_kute-d6vf4nm.png";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fr=new Firebase("https://iitbbscha.firebaseio.com/");
        fr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                r=dataSnapshot.getChildrenCount();


                if((r!=0)&&(egf==0)){
                    log.setVisibility(View.VISIBLE);
                    SharedPreferences.Editor ghjkl=ps.edit();
                    ghjkl.putLong("BOY", r);
                    ghjkl.commit();

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_LONG).show();

            }
        });

log.setOnClickListener(new View.OnClickListener() {


    @Override
    public void onClick(View v) {


        log.setVisibility(View.INVISIBLE);
        strg=ed.getText().toString();
        passw=pss.getText().toString();
        comm=strg+passw;
        SharedPreferences.Editor dfgh=sp.edit();
        dfgh.putString("HAI", strg);
        dfgh.commit();
        SharedPreferences.Editor dfghop=kl.edit();
        dfghop.putString("TA",sx);
        dfghop.commit();




        Map<String,String> number=new HashMap<String, String>();
        number.put(String.valueOf(r),null);
        fr.push().setValue(number, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if(firebaseError!=null){
                    Toast.makeText(MainActivity.this,"ERROR1",Toast.LENGTH_SHORT).show();
                }
                else{

                    Map<String,Object> data=new HashMap<String, Object>();
                    data.put("name",strg);
                    data.put("image",sx);
                    data.put("nassword",comm);
                    egf=1;


                    fr.child(String.valueOf(r)).updateChildren(data, new Firebase.CompletionListener() {
                        @Override
                        public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                            if(firebaseError!=null){
                                Toast.makeText(MainActivity.this,"ERROR2",Toast.LENGTH_SHORT).show();
                            }
                            else{

                                Map<String,String> ghi=new HashMap<String, String>();
                                ghi.put("recieve",null);
                                fr.child(String.valueOf(r-1)).push().setValue(ghi, new Firebase.CompletionListener() {
                                    @Override
                                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                                        if(firebaseError!=null){
                                            Toast.makeText(MainActivity.this,"ERROR3",Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Map<String,String> ghik=new HashMap<String, String>();
                                            ghik.put("0",null);
                                            fr.child(String.valueOf(r-1)).child("recieve").push().setValue(ghik, new Firebase.CompletionListener() {
                                                @Override
                                                public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                                                    if(firebaseError!=null){
                                                        Toast.makeText(MainActivity.this,"ERROR4",Toast.LENGTH_SHORT).show();
                                                    }
                                                    else{  Map<String,Object> numberwr=new HashMap<String, Object>();
                                                        numberwr.put("image", "https://resources.whatwg.org/logo-notifications.png");
                                                        numberwr.put("sender","App");
                                                        numberwr.put("message","Hi");
                                                        fr.child(String.valueOf(r-1)).child("recieve").child("0").updateChildren(numberwr, new Firebase.CompletionListener() {
                                                            @Override
                                                            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                                                                if(firebaseError!=null){
                                                                    Toast.makeText(MainActivity.this,"ERROR5",Toast.LENGTH_SHORT).show();
                                                                }
                                                                else{
                                                                    SharedPreferences.Editor cvb=ui.edit();
                                                                    cvb.putInt("CHECK",1);
                                                                    cvb.commit();

                                                                    Toast.makeText(MainActivity.this,"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                                                                    try {
                                                                        Class g=Class.forName("com.abijayana.user.simplechat.Main");
                                                                        Intent y=new Intent(MainActivity.this,g);
                                                                        startActivity(y);
                                                                    } catch (ClassNotFoundException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                }

                                                            }
                                                        });



                                                    }

                                                }
                                            });
                                        }
                                    }
                                });

                            }



                        }
                    });


                }
            }
        });
    }
});















    }

        }


