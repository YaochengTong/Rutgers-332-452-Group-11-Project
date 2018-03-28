package com.bus;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SelectActivity extends Activity {
    List<String> datas = new ArrayList<>();
    private ListView listView1;
    private ListView listView2;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm");
    int selectStart;
    int selectEnd;
    {
        {
            datas.add("Rockoff Hall");
            datas.add("Lipman Hall");
            datas.add("Scott Hall");
            datas.add("Biel Road");
            datas.add("Katzenbach");
            datas.add("Gibbons");
            datas.add("Liberty Street");
            datas.add("Zimmerli Arts Museum");
            datas.add("Quads");
            datas.add("Werblin Back Entrance");
            datas.add("Hill Center");
            datas.add("Science Building");
            datas.add("Library of Science");
            datas.add("Busch Suites");
            datas.add("Busch Student Center");
            datas.add("Livingston Plaza");
            datas.add("Livingston Student Center");
            datas.add("Train Station");
            datas.add("Paterson Street");
            datas.add("Public Safety Building South");
            datas.add("Red Oak Lane");
            datas.add("Food Sciences Building");
            datas.add("Henderson");
            datas.add("College Hall");
            datas.add("Student Activities Center");
            datas.add("College Avenue Student Center");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        listView1 = (ListView) findViewById(R.id.listView1);
        listView2 = (ListView) findViewById(R.id.listView2);

        final String route = getIntent().getStringExtra("bus");
        listView1.setAdapter(new Adapter() {
            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                String name = datas.get(i);
                view = LayoutInflater.from(SelectActivity.this).inflate(R.layout.select_item, null);
                if (selectStart == i) {
                    view.setBackgroundColor(Color.GRAY);
                }
                TextView t = (TextView) view.findViewById(R.id.text);
                t.setText(name);
                return view;
            }
        });
        listView2.setAdapter(new Adapter() {
            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                String name = datas.get(i);
                view = LayoutInflater.from(SelectActivity.this).inflate(R.layout.select_item, null);
                if (selectEnd == i) {
                    view.setBackgroundColor(Color.GRAY);
                }
                TextView t = (TextView) view.findViewById(R.id.text);
                t.setText(name);
                return view;
            }
        });
        findViewById(R.id.click_bus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedItem1 = datas.get(selectStart);
                String selectedItem2 = datas.get(selectEnd);
                App.firebase.child("bus").push().setValue(new Bus(App.ruid, selectedItem1, selectedItem2, route, sdf.format(new Date()), sdfTime.format(new Date())));
                Intent intent = new Intent(SelectActivity.this,ResultActivity.class);
                intent.putExtra("start",selectedItem1);
                intent.putExtra("end",selectedItem2);
                intent.putExtra("route",route);
                startActivity(intent);
            }
        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectStart = i;
                ((Adapter) listView1.getAdapter()).notifyDataSetChanged();
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((Adapter) listView2.getAdapter()).notifyDataSetChanged();
                selectEnd = i;
            }
        });
    }

    class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            String name = datas.get(i);
            view = LayoutInflater.from(SelectActivity.this).inflate(R.layout.select_item, null);
            TextView t = (TextView) view.findViewById(R.id.text);
            t.setText(name);
            return view;
        }
    }
}
