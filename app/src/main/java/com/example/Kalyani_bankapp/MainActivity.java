package com.example.Kalyani_bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final  int splash=999;
    TextView txt_welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_welcome=findViewById(R.id.txt_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,DataShow.class);
                startActivity(intent);
            }
        },splash);

        Animation anim1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim1);
        txt_welcome.setAnimation(anim1);
    }
}