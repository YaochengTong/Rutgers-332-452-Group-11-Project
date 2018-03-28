package com.bus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class MainActivity extends Activity {

    private EditText ruid;
    private EditText psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ruid  = (EditText) findViewById(R.id.ruid);
        psw  = (EditText) findViewById(R.id.psw);



        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ru = ruid.getText().toString();
                String p = psw.getText().toString();
                if ("".equals(ru) || "".equals(p)) {
                    Toast.makeText(MainActivity.this, "Can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                App.ruid = ru;
                App.firebase.child("users").push().setValue(new User(ru));
                //firebase.child("users").setValue(new User(ru, p));
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,BusActivity.class);
                startActivity(intent);
            }
        });
    }
}
