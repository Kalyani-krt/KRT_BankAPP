package com.example.Kalyani_bankapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class customAdapter extends RecyclerView.Adapter<customAdapter.myViewHolder> implements Filterable {
    Activity activity;
    DataBaseHelper dbh;
    private List<ItemModel> itemModels;
    private List<ItemModel>backupitem;
    private Context context;

    public customAdapter(Activity activity, List<ItemModel> itemModels, Context context) {
        this.activity = activity;
        this.itemModels = itemModels;
        this.context = context;
        backupitem=new ArrayList<>(itemModels);
    }

    public Filter getFilter() {
        return null;
    }
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.row,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, final int position) {
       dbh=new DataBaseHelper(context);
        Cursor cursor=dbh.getData();
        holder.txt_name.setText(itemModels.get(position).getName());
        holder.txt_balance.setText(itemModels.get(position).getBalance());
        holder.txt_mobile.setText(itemModels.get(position).getMobileno());
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,TransferMoney.class);
                intent.putExtra("ifsc",itemModels.get(position).getIfsc());
                intent.putExtra("mobile",itemModels.get(position).getMobileno());
                intent.putExtra("name",itemModels.get(position).getName());
                intent.putExtra("email",itemModels.get(position).getEmail());
                intent.putExtra("accno",itemModels.get(position).getAccno());
                intent.putExtra("balance",itemModels.get(position).getBalance());
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView txt_name,txt_email,txt_accno,txt_ifsc,txt_mobile,txt_balance;
        LinearLayout mainLayout;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name=itemView.findViewById(R.id.txt_name);
            txt_email=itemView.findViewById(R.id.txt_email);
            txt_balance=itemView.findViewById(R.id.txt_bal);
            txt_mobile=itemView.findViewById(R.id.txt_mobile);
            txt_accno=itemView.findViewById(R.id.txt_accno);
            txt_ifsc=itemView.findViewById(R.id.txt_ifsc);
            mainLayout=itemView.findViewById(R.id.mainLayout);

        }
    }

}
