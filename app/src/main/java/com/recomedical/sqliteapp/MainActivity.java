package com.recomedical.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText healthcare_id, firstname, lastname, healthcare_email, role;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        healthcare_id = findViewById(R.id.healthcare_id);
        firstname = findViewById(R.id.first_name);
        lastname = findViewById(R.id.last_name);
        healthcare_email = findViewById(R.id.healthcare_email);
        role = findViewById(R.id.role);

        insert = findViewById(R.id.btn_insert);
        update = findViewById(R.id.btn_update);
        delete = findViewById(R.id.btn_delete);
        view = findViewById(R.id.btn_view);
        DB = new DBHelper (this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String healthcare_idTXT = healthcare_id.getText().toString();
                String firstnameTXT = firstname.getText().toString();
                String lastnameTXT = lastname.getText().toString();
                String healthcare_emailTXT = healthcare_email.getText().toString();
                String roleTXT = role.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(healthcare_idTXT,firstnameTXT, lastnameTXT, healthcare_emailTXT, roleTXT);
                if (checkinsertdata == true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry NOT Inserted", Toast.LENGTH_SHORT).show();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String healthcare_idTXT = healthcare_id.getText().toString();
                String firstnameTXT = firstname.getText().toString();
                String lastnameTXT = lastname.getText().toString();
                String healthcare_emailTXT = healthcare_email.getText().toString();
                String roleTXT = role.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(healthcare_idTXT,firstnameTXT, lastnameTXT, healthcare_emailTXT, roleTXT);
                if (checkupdatedata == true)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Insert ID to Update", Toast.LENGTH_SHORT).show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String healthcare_idTXT = healthcare_id.getText().toString();


                Boolean checkdeletedata = DB.deletedata(healthcare_idTXT);
                if (checkdeletedata == true)
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Insert ID to Delete details", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Cursor res = DB.getdata();
                if(res.getCount()==0) {
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext() ){
                    buffer.append("ID: "+ res.getString(0)+"\n");
                    buffer.append("First Name: "+ res.getString(1)+"\n");
                    buffer.append("Last Name: "+ res.getString(2)+"\n");
                    buffer.append("Email: "+ res.getString(3)+"\n");
                    buffer.append("Role: "+ res.getString(4)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}