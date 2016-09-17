package com.example.dell.exchange_cell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.annotations.Nullable;
import com.firebase.client.core.SnapshotHolder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button ok;
    Firebase mFirebase , buy,  child1;
    private EditText ed1,ed2;
    private String bookname, name2;
    private TextView textBuy;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent1 = getIntent();
        Firebase.setAndroidContext(this);

        ed1= (EditText)findViewById(R.id.editText1);
        ok = (Button) findViewById(R.id.button2);
        ed2= (EditText)findViewById(R.id.editText2);
        textBuy = (TextView) findViewById(R.id.text_buy);
        mFirebase= new Firebase("https://exchange-cell.firebaseio.com/");
        buy = mFirebase.child("Buy");


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = ed1.getText().toString();

                Map<String,Object> mapBuy = new HashMap<String, Object>();
                mapBuy.put(name1," ");
                Log.d("Test","map put success");
                buy.updateChildren(mapBuy);
                child1 = buy.child(name1);

                Map<String,Object> mapb = new HashMap<String, Object>();
                mapb.put("Book",ed2.getText().toString());
                child1.updateChildren(mapb);


                Toast.makeText(getApplicationContext(),"Buy-Child created sucessfully",Toast.LENGTH_SHORT).show();

                buy.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        Iterator i = dataSnapshot.getChildren().iterator();


                        while(i.hasNext())
                        {
                            try {
                                name2 = (String) ((DataSnapshot) i).getKey();

                                Log.d("Test" , ""+ (String) ((DataSnapshot) i).getKey()+ " : " + (String) ((DataSnapshot) i).getValue() );

                            }catch (Exception e){
                                e.printStackTrace();

                                e.getMessage();
                            }
                                bookname = (String) ((DataSnapshot)i.next()).getValue();

                            textBuy.append(name2 + " : " + bookname + "\n");

                        }


                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });




            }
        });





    }
}
