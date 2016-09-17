package com.example.dell.exchange_cell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

public class Sell extends AppCompatActivity {

    private Button ok1;
    private EditText ed3,ed4;
    Firebase mFirebase, sell,  child2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        Intent intent = getIntent();
        Firebase.setAndroidContext(this);

        ok1 = (Button)findViewById(R.id.button1);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4 = (EditText) findViewById(R.id.editText4);

        mFirebase= new Firebase("https://exchange-cell.firebaseio.com/");
        sell = mFirebase.child("Sell");

        ok1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String,Object> mapSell = new HashMap<String, Object>();
                String name2 = ed3.getText().toString();
                mapSell.put(ed3.getText().toString(),"");
                sell.updateChildren(mapSell);
                child2 = sell.child(name2);
                Map<String,Object> maps = new HashMap<String, Object>();
                maps.put("Price",ed4.getText().toString());
                child2.updateChildren(maps);

                Toast.makeText(getApplicationContext(),"Sell-Child created sucessfully",Toast.LENGTH_SHORT).show();

            }
        });


    }
}
