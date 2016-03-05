package com.abijayana.user.simplechat;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 03-03-2016.
 */
public class Main extends Activity {
    SharedPreferences sp,ps,kl;long cio=0;int egf;
    TabHost th; TabHost.TabSpec spec1;TabHost.TabSpec spec2;

    ArrayList<people> list,list1;Firebase fb;ListView lv,lv1;listpeople lsp;long rilk=0;listreciever lps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.people);
        th=(TabHost)findViewById(R.id.tabHostasd);
        th.setup();
        spec1=th.newTabSpec("tag3");
        spec1.setContent(R.id.linearLayout1);spec1.setIndicator("SEND");
        th.addTab(spec1);
        spec2=th.newTabSpec("tag4");
        spec2.setContent(R.id.linearLayout2);spec2.setIndicator("RECIEVED");
        th.addTab(spec2);

        for (int i = 0; i < th.getTabWidget().getChildCount(); i++) {

            TextView tv = (TextView) th.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#ffffff"));
        }


        fb=new Firebase("https://iitbbscha.firebaseio.com/");
        lv=(ListView)findViewById(R.id.lstvw);
        lv1=(ListView)findViewById(R.id.lstvw1);
        list=new ArrayList<people>();
        list1=new ArrayList<people>();
        lsp=new listpeople(getApplicationContext(),R.layout.single,list);
        lps=new listreciever(getApplicationContext(),R.layout.single1,list1);
        lv.setAdapter(lsp);
        lv1.setAdapter(lps);
        sp=this.getSharedPreferences("AMMAACHAN", Context.MODE_PRIVATE);
        ps=this.getSharedPreferences("ACHANAMMA",Context.MODE_PRIVATE);
        kl=this.getSharedPreferences("ABIJAY",Context.MODE_PRIVATE);
         rilk= ps.getLong("BOY", 0);



        fb.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();

                for (int i = 0; i < dataSnapshot.getChildrenCount(); i++)

                {
                    people ad = new people();
                    ad.setName(String.valueOf(dataSnapshot.child(String.valueOf(i)).child("name").getValue()));
                    ad.setUrl(String.valueOf(dataSnapshot.child(String.valueOf(i)).child("image").getValue()));
                    list.add(ad);
                }
                lsp.notifyDataSetChanged();
                list1.clear();
                for(long j=(dataSnapshot.child(String.valueOf(rilk)).child("recieve").getChildrenCount())-1;j>=0;j--)
                {
                    people sy=new people();
                    sy.setName(String.valueOf(dataSnapshot.child(String.valueOf(rilk)).child("recieve").child(String.valueOf(j)).child("sender").getValue()));
                    sy.setMessage(String.valueOf(dataSnapshot.child(String.valueOf(rilk)).child("recieve").child(String.valueOf(j)).child("message").getValue()));
                    sy.setUrl(String.valueOf(dataSnapshot.child(String.valueOf(rilk)).child("recieve").child(String.valueOf(j)).child("image").getValue()));
                    list1.add(sy);
                }

                lps.notifyDataSetChanged();



            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(Main.this, "ERROR", Toast.LENGTH_SHORT).show();

            }
        });


        fb.addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                lsp.notifyDataSetChanged();
                lps.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                lsp.notifyDataSetChanged();
                lps.notifyDataSetChanged();


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {


                lsp.notifyDataSetChanged();
                lps.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {


                lsp.notifyDataSetChanged();
                lps.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(Main.this, "NO", Toast.LENGTH_SHORT).show();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            String sh;
            String cv;
            Button b1;
            String cyh;
            Dialog dfg;
            EditText io;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                dfg = new Dialog(Main.this);
                dfg.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dfg.setContentView(R.layout.messagedialog);
                TextView rt = (TextView) dfg.findViewById(R.id.txter1);
                io = (EditText) dfg.findViewById(R.id.edt1re);
                b1 = (Button) dfg.findViewById(R.id.buttns);
                b1.setVisibility(View.INVISIBLE);
                rt.setText(list.get(position).getName());

                fb.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        cio = dataSnapshot.child(String.valueOf(position)).child("recieve").getChildrenCount();
                        if ((cio != 0) && (egf == 0)) {
                            b1.setVisibility(View.VISIBLE);
                            egf = 1;
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
                b1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        cv = io.getText().toString();

                        cyh = sp.getString("HAI", "do");
                        sh = kl.getString("TA", "ho");
                        Map<String, String> erty = new HashMap<String, String>();
                        erty.put(String.valueOf(cio), null);
                        fb.child(String.valueOf(position)).child("recieve").push().setValue(erty, new Firebase.CompletionListener() {
                            @Override
                            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                                if (firebaseError != null) {
                                    Toast.makeText(Main.this, "ERROR SENDING 1", Toast.LENGTH_SHORT).show();
                                } else {
                                    Map<String, Object> yop = new HashMap<String, Object>();
                                    yop.put("sender", cyh);
                                    yop.put("message", cv);
                                    yop.put("image", sh);
                                    fb.child(String.valueOf(position)).child("recieve").child(String.valueOf(cio)).updateChildren(yop, new Firebase.CompletionListener() {
                                        @Override
                                        public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                                            if (firebaseError != null) {
                                                Toast.makeText(Main.this, "ERROR SENDING 2", Toast.LENGTH_SHORT).show();
                                            } else {
                                                egf = 0;
                                                Toast.makeText(Main.this, "SENDING SUCCESSFULL", Toast.LENGTH_SHORT).show();

                                                dfg.cancel();


                                            }

                                        }
                                    });

                                }
                            }
                        });


                    }
                });
                dfg.show();

            }
        });









    }
}
