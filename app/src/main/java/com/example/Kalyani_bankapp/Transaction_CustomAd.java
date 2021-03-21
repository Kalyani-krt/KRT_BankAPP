package com.example.Kalyani_bankapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Kalyani_bankapp.Transaction_CustomAd.myViewHolder;

import java.util.ArrayList;
import java.util.List;

public class Transaction_CustomAd extends RecyclerView.Adapter<myViewHolder> {
    Activity activity;
    DataBaseHelper dbh;
    private List<Transaction_Itemmodel> itemModels;
    private List<Transaction_Itemmodel>backupitem;
    private Context context;


    public Transaction_CustomAd(Activity activity, List<Transaction_Itemmodel> itemModels, Context context) {
        this.activity = activity;
        this.itemModels = itemModels;
        this.context = context;
        backupitem=new ArrayList<>(itemModels);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.transaction_row,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, final int position) {
        dbh=new DataBaseHelper(context);
        Cursor cursor=dbh.getData();
        holder.txt_sender.setText(itemModels.get(position).getSender());
        holder.txt_amount.setText(itemModels.get(position).getAmount());
        holder.txt_receiver.setText(itemModels.get(position).getReceiver());
        /*
        * holder.TransLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,TransferMoney.class);
                intent.putExtra("sender",itemModels.get(position).getSender());
                intent.putExtra("amount",itemModels.get(position).getAmount());
                intent.putExtra("receiver",itemModels.get(position).getReceiver());
                activity.startActivityForResult(intent,1);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView txt_sender,txt_amount,txt_receiver;
        LinearLayout TransLayout;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_sender=itemView.findViewById(R.id.txt_sender);
            txt_amount=itemView.findViewById(R.id.txt_amount);
            txt_receiver=itemView.findViewById(R.id.txt_receiver);
            TransLayout=itemView.findViewById(R.id.TransLayout);

        }
    }}
