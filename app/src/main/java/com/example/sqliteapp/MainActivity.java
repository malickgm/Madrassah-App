package com.example.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,roll,sabaq,sabaqi,manzil;
    Button add,update,delete,show,repo;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.etName);
        roll=findViewById(R.id.etRollNumber);
        sabaq=findViewById(R.id.etSabaq);
        sabaqi=findViewById(R.id.etSabaqi);
        manzil=findViewById(R.id.etManzil);

        add=findViewById(R.id.btnAdd);
        update=findViewById(R.id.btnUpdate);
        delete=findViewById(R.id.btnDelete);
        show=findViewById(R.id.btnViewRecords);
        repo=findViewById(R.id.btnRepo);

        DB=new DBHelper(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt=name.getText().toString();
                String rollTxt= roll.getText().toString();
                String sabaqTxt= sabaq.getText().toString();
                String sabaqiTxt= sabaqi.getText().toString();
                String manzilTxt=manzil.getText().toString();

                boolean checkInsert= DB.insertUserData(nameTxt,rollTxt,sabaqTxt,sabaqiTxt,manzilTxt);

                if(checkInsert==true){
                    Toast.makeText(MainActivity.this,"New Entry Added",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(MainActivity.this,"New Entry Not Added",Toast.LENGTH_SHORT).show();

                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt=name.getText().toString();
                String rollTxt= roll.getText().toString();
                String sabaqTxt= sabaq.getText().toString();
                String sabaqiTxt= sabaqi.getText().toString();
                String manzilTxt=manzil.getText().toString();

                boolean checkUpdate= DB.UpdateUserData(nameTxt,rollTxt,sabaqTxt,sabaqiTxt,manzilTxt);

                if(checkUpdate==true){
                    Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(MainActivity.this,"Can't Update Data",Toast.LENGTH_SHORT).show();

                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // String nameTxt=name.getText().toString();
                String rollTxt= roll.getText().toString();

                boolean checkdelete= DB.deleteUserData(rollTxt);

                if(checkdelete==true){
                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(MainActivity.this,"Can't Delete Data",Toast.LENGTH_SHORT).show();

                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor allData=DB.showUserData();
                if(allData.getCount()==0){
                    Toast.makeText(MainActivity.this,"No data Present",Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer=new StringBuffer();
                while(allData.moveToNext()){
                    buffer.append("Name: "+allData.getString(0)+"\n");
                    buffer.append("Roll Number: "+allData.getString(1)+"\n");
                    buffer.append("Sabaq: "+allData.getString(2)+"\n");
                    buffer.append("Sabaqi: "+allData.getString(3)+"\n");
                    buffer.append("Manzil: "+allData.getString(4)+"\n");
                }

                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Students: ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        repo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://github.com/malickgm?tab=repositories";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


    }
}