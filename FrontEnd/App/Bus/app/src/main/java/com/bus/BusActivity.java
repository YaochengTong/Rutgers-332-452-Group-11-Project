package com.bus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class BusActivity extends Activity {
    private ListView listView;
    List<String> datas = new ArrayList<>();
    {
        datas.add("A");
        datas.add("B");
        datas.add("C");
        datas.add("D");
        datas.add("EE");
        datas.add("F");
        datas.add("H");
        datas.add("REXB");
        datas.add("REXL");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(BusActivity.this, SelectActivity.class);
                intent.putExtra("bus", datas.get(i));
                startActivity(intent);
            }
        });
    }
}
