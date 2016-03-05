package com.abijayana.user.simplechat;

/**
 * Created by user on 05-03-2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;






public class listreciever extends ArrayAdapter<people> {

    Context context;
    int resource;
    ArrayList<people> objects;
    public listreciever(Context context, int resource, ArrayList<people> objects) {
        super(context,resource,objects);


        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent) {
        final ViewHoldera vw;

        if(convertView==null)
        {
            LayoutInflater lf=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=lf.inflate(resource,null);
            vw=new ViewHoldera();
            vw.tv1=(TextView)convertView.findViewById(R.id.tv1rty);
            vw.iv1=(ImageView)convertView.findViewById(R.id.imagenew1);
            vw.tv2=(TextView)convertView.findViewById(R.id.tv2yhu);
            convertView.setTag(vw);



        }
        else{

            vw=(ViewHoldera)convertView.getTag();
        }
        vw.tv1.setText(objects.get(position).getName());
        vw.tv2.setText(objects.get(position).getMessage());
        Picasso.with(getContext()).load(objects.get(position).getUrl()).placeholder(R.drawable.anyu).into(vw.iv1);


        return convertView;
    }


    static class ViewHoldera{
        TextView tv1;
        ImageView iv1;
        TextView tv2;

    }
}

