package com.example.trip_app;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Date> date;


    Adapter(Context context, ArrayList<Date> date)
    {
        this.inflater= LayoutInflater.from(context);
        this.date= date;

    }



    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.activity_list_view2,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
  String title=date.get(position).getTRIPNAME();
  String startpoint=date.get(position).getSTARTPOINT();
  String endpoint=date.get(position).getENDPOINT();
  String time=date.get(position).getTIME();
  String data=date.get(position).getDATE();
  holder.nnametrip.setText(title);
  holder.nstartpoint.setText(startpoint);
  holder.nendpoint.setText(endpoint);
  holder.ntime.setText(time);
  holder.ndata.setText(data);
    }

    @Override
    public int getItemCount() {
        return date.size();
    }
    public  class  ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener
    {
        private  static final  String TAG="MyViewHolder";
        TextView nnametrip,nstartpoint,nendpoint,ntime,ndata;

ImageView btnnotes,btnstart,btnmenu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nnametrip=itemView.findViewById(R.id.nTitle);
            nstartpoint=itemView.findViewById(R.id.nStartpoint);
            nendpoint=itemView.findViewById(R.id.nEndpoint);
            ntime=itemView.findViewById(R.id.nTime);
            ndata=itemView.findViewById(R.id.nDate);
            btnmenu=itemView.findViewById(R.id.btn_menu);
            btnmenu.setOnClickListener(this);
            btnnotes=itemView.findViewById(R.id.btn_notes);
            btnnotes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 Intent intent =new Intent(v.getContext(),notes.class);
                 v.getContext().startActivity(intent);

                }
            });
            btnstart=itemView.findViewById(R.id.btn_start);
            btnstart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String sStartpoint=nstartpoint.getText().toString().trim();
                            String sEndpoint=nendpoint.getText().toString().trim();
                            try{

                                Uri uri= Uri.parse("https://www.google.co.in/maps/dir/"+sStartpoint+"/"+sEndpoint);
                                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                                intent.setPackage("com.google.android.apps.maps");
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                               v.getContext().startActivity(intent);

                            }catch (ActivityNotFoundException e)
                            {

                                Uri uri=Uri.parse("https://play.google.com/store/apps/details?id=com.goole.android.apps.maps");
                                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                v.getContext().startActivity(intent);
                            }
                        }
            });

        }

        @Override
        public void onClick(View v) {
            Showmenu(v);
        }
        private  void Showmenu(View view)
        {
            PopupMenu popupMenu=new PopupMenu(view.getContext(),view);
            popupMenu.inflate(R.menu.menu_details);
            popupMenu.show();
        }
    }




}




