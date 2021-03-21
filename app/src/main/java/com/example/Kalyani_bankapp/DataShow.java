package com.example.Kalyani_bankapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.ArrayList;

public class DataShow extends AppCompatActivity {
ArrayList<ItemModel>itemModels;
DataBaseHelper dbh;
RecyclerView recyclerView;
customAdapter customAdapter;
ArrayList<String>name,balance,mobile;
Button btnTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_show);
        balance = new ArrayList<>();
        name = new ArrayList<>();
        mobile = new ArrayList<>();
        btnTransaction=findViewById(R.id.btnTransaction);
        dbh = new DataBaseHelper(DataShow.this);
        recyclerView = findViewById(R.id.recyclerview);
        itemModels = new ArrayList<>();
        Animation anim1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.left_right);
        recyclerView.setAnimation(anim1);
        Cursor cursor = new DataBaseHelper(this).getData();
        while (cursor.moveToNext()) {
            ItemModel itemModel = new ItemModel(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5));
            itemModels.add(itemModel);
        }
        customAdapter = new customAdapter(DataShow.this, itemModels, this);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customAdapter.notifyDataSetChanged();

        btnTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DataShow.this,All_Transaction.class);
                startActivity(i);
            }
        });
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Are you sure you want to Quit?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();               //Finish all activities in stack and app closes.
                finish();                      // goes back to previus actitvity or closes if last activity
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }
}

