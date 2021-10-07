package com.example.trip_app;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.annotations.NotNull;

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


    @NotNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.activity_list_view2,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NotNull Adapter.ViewHolder holder, int position) {
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
    public  class  ViewHolder extends RecyclerView.ViewHolder
    {
        private  static final  String TAG="MyViewHolder";
        TextView nnametrip,nstartpoint,nendpoint,ntime,ndata;
        EditText editText;

        ImageView btnnotes,btnstart,btndelete,btnedit,btnaddnotes;
        public ViewHolder(@NotNull View itemView) {
            super(itemView);

            nnametrip=itemView.findViewById(R.id.nTitle);
            nstartpoint=itemView.findViewById(R.id.nStartpoint);
            nendpoint=itemView.findViewById(R.id.nEndpoint);
            ntime=itemView.findViewById(R.id.nTime);
            ndata=itemView.findViewById(R.id.nDate);
            btnnotes=itemView.findViewById(R.id.btn_notes);
            editText =itemView.findViewById(R.id.edit_add_notes);
            btnnotes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(inflater.getContext());
                    builder.setTitle("Notes");


                    final TextView show = new TextView(inflater.getContext());
                    show.setText(nnametrip.getText().toString());
                    show.setTextSize(18);




                    builder.setView(show);

                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    builder.show();



                }
            });

            btnaddnotes=itemView.findViewById(R.id.btn_add_notes);
            btnaddnotes.setOnClickListener(new View.OnClickListener() {
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

            btndelete=itemView.findViewById(R.id.btn_delete);
            btndelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(inflater.getContext()).setTitle("Delete Trip")
                            .setMessage("Are you sure want to delete ?").setIcon(R.drawable.ic__delete).setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    date.remove(getAdapterPosition());
                                    notifyItemRemoved(getAdapterPosition());


                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    builder.show();
                }
            });

            btnedit = itemView.findViewById(R.id.btn_edit);
            btnedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2 =new Intent(v.getContext(),update.class);
                    v.getContext().startActivity(intent2);

                }
            });
        }


        }



    }




