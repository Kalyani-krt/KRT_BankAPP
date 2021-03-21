package com.example.Kalyani_bankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class All_Transaction extends AppCompatActivity {
 ArrayList<Transaction_Itemmodel> itemModels;
 Transaction_CustomAd customAd;
 TextView txt_sender,txt_amount,txt_receiver;
 ArrayList<String>sender,amount,receiver;
TextView no_data;
    DataBaseHelper dbh;
 RecyclerView recyclerView;
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__transaction);
        txt_sender=findViewById(R.id.txt_sender);
        txt_amount=findViewById(R.id.txt_amount);
        txt_receiver=findViewById(R.id.txt_receiver);
         sender = new ArrayList<>();
         amount = new ArrayList<>();
         receiver = new ArrayList<>();
         no_data=findViewById(R.id.no_data);
         dbh=new DataBaseHelper(All_Transaction.this);
         recyclerView=findViewById(R.id.recyclerview);
         itemModels = new ArrayList<>();
     Animation anim1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.left_right);
     recyclerView.setAnimation(anim1);


     Cursor cursor = new DataBaseHelper(this).getTransData();
     if(cursor!=null){
         while (cursor.moveToNext()) {
         Transaction_Itemmodel itemModel = new Transaction_Itemmodel(cursor.getString(0), cursor.getString(1),
                 cursor.getString(2));
         itemModels.add(itemModel);
         no_data.setVisibility(View.GONE);
         recyclerView.setVisibility(View.VISIBLE);
     }}else {
         no_data.setVisibility(View.VISIBLE);
         recyclerView.setVisibility(View.GONE);
     }

     customAd = new Transaction_CustomAd(All_Transaction.this, itemModels, this);
     recyclerView.setAdapter(customAd);
     recyclerView.setHasFixedSize(true);
     recyclerView.setLayoutManager(new LinearLayoutManager(this));
     customAd.notifyDataSetChanged();
  }
}