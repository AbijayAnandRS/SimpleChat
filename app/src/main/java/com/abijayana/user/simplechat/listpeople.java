package com.abijayana.user.simplechat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 03-03-2016.
 */
public class listpeople extends ArrayAdapter<people> {

    Context context;
    int resource;
    ArrayList<people> objects;
    public listpeople(Context context, int resource, ArrayList<people> objects) {
        super(context,resource,objects);


        this.context=context;
        this.resource=resource;
       this.objects=objects;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent) {
        final ViewHolder vw;

        if(convertView==null)
        {
            LayoutInflater lf=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=lf.inflate(resource,null);
           vw=new ViewHolder();
            vw.tv=(TextView)convertView.findViewById(R.id.textvw1);
            vw.iv=(ImageView)convertView.findViewById(R.id.iv1);
            convertView.setTag(vw);



        }
        else{

        vw=(ViewHolder)convertView.getTag();
        }
        vw.tv.setText(objects.get(position).getName());
        Picasso.with(getContext()).load(objects.get(position).getUrl()).placeholder(R.drawable.anyu).into(vw.iv);


        return convertView;
    }


    static class ViewHolder{
        TextView tv;
        ImageView iv;

    }
}
