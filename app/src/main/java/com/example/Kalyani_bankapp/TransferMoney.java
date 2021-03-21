package com.example.Kalyani_bankapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class TransferMoney extends AppCompatActivity {
    TextView txt_name, txt_mobile, txt_email, txt_accno, txt_ifsc, txt_bal;
    DataBaseHelper dbh;
    String name, mobile, email, accno, ifsc, bal,bal1;
    Button btn_transfer;
    String tranf_amount,ed_acc,str;
    ArrayList<ItemModel>itemModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_money);
        txt_name = findViewById(R.id.txt_name);
        txt_email = findViewById(R.id.txt_email);
        txt_mobile = findViewById(R.id.txt_mobile);
        txt_accno = findViewById(R.id.txt_accno);
        txt_ifsc = findViewById(R.id.txt_ifsc);
        txt_bal = findViewById(R.id.txt_bal);
        btn_transfer=findViewById(R.id.btn_transfer);
        dbh = new DataBaseHelper(TransferMoney.this);
        getIntentData();
        itemModels = new ArrayList<>();

        btn_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TransferMoney.this);
                LinearLayout layout=new LinearLayout(TransferMoney.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                final EditText amt = new EditText(TransferMoney.this); //amount
                final EditText Acc = new EditText(TransferMoney.this);
                layout.addView(amt);
                layout.addView(Acc);
                amt.setInputType(InputType.TYPE_CLASS_NUMBER);
                Acc.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(layout);
                amt.setHint("Enter Amount");
                Acc.setHint("Enter AccNo");
                builder.setCancelable(false);
                builder.setMessage("Enter the amount and Person to Send Money!!");
                builder.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                   //empty
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setView(layout);
                final AlertDialog dialog=builder.create();
                dialog.show();
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(amt.getText().toString())) {
                            amt.setError("Required !");
                            return;
                        }
                        if (TextUtils.isEmpty(Acc.getText().toString())) {
                            Acc.setError("Required !");
                            return;
                        }
                        tranf_amount= String.valueOf(amt.getText());
                        ed_acc= String.valueOf(Acc.getText());

                       Boolean res= dbh.checkip(ed_acc);
                        if(res==true){
                            if(ed_acc.equals(accno)){
                                Toast.makeText(TransferMoney.this,"Receiver's AccNo can't be same as yours !!",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(TransferMoney.this,"All Okay",Toast.LENGTH_SHORT).show();
                                Transfer();
                            }
                        }  else{
                            Toast.makeText(TransferMoney.this,"Invalid Account Number!",Toast.LENGTH_SHORT).show();
                        } dialog.cancel();
                    }
                });
            }
        });
      }
    public  void Transfer(){
        Cursor c= ((dbh.getName(ed_acc)));
        String name= (String) txt_name.getText();
        Double amount= Double.parseDouble(tranf_amount);
        String SenderAcc=accno;

        if (c.moveToFirst()){
            do{
                str = c.getString(c.getColumnIndex("name"));
            }while(c.moveToNext());
        }
        c.close();

        Boolean res= dbh.bal(amount,ed_acc);
        if (res==true){
           Toast.makeText(this, "All Okay", Toast.LENGTH_SHORT).show();
                boolean result = dbh.insertData(amount,name,str);
                    if (result == true) {
                        dbh.updateData(amount,ed_acc);
                        dbh.DeductAmount(amount,SenderAcc);
                        Toast.makeText(this, "Transfer Done SuccessFully !", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(TransferMoney.this,DataShow.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Something Wrong", Toast.LENGTH_SHORT).show();
                    }
        }else {
            //txt_bal.setError("Insuufcient Money :(");
            Toast.makeText(this, "Insufficient Money :( ", Toast.LENGTH_SHORT).show();
        }

    }
    public void getIntentData() {
        mobile = getIntent().getExtras().getString("mobile");
        name = getIntent().getExtras().getString("name");
        email = getIntent().getExtras().getString("email");
        accno = getIntent().getExtras().getString("accno");
        ifsc = getIntent().getExtras().getString("ifsc");
        bal = getIntent().getExtras().getString("balance");
        txt_name.setText(name);
        txt_email.setText(email);
        txt_mobile.setText(mobile);
        txt_accno.setText(accno);
        txt_ifsc.setText(ifsc);
        txt_bal.setText(bal);

    }
}