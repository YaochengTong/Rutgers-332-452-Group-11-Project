package se2018project.rubus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class maps extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // set route spinner
        Spinner route = findViewById(R.id.routemap_spinner);
        ArrayAdapter<CharSequence> route_adapter = ArrayAdapter.createFromResource(this,
                R.array.routes_array, android.R.layout.simple_spinner_item);
        route_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        route.setAdapter(route_adapter);

        // not working in this API version?
        route.setPrompt("Select Route");

        // stop spinner listener
        route.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ImageView map = findViewById(R.id.map_imageview);

                // get currently selected stop text as String
                String currentRoute = parent.getItemAtPosition(position).toString();

                // fill stop spinner based upon route selection
                switch(currentRoute) {

                    case "A (Busch and College Ave.)":
                        map.setImageResource(R.drawable.a_route);
                        break;

                    case "B (Busch and Livingston)":
                        map.setImageResource(R.drawable.b_route);
                        break;

                    case "C (Busch)":
                        map.setImageResource(R.drawable.c_route);
                        break;

                    case "EE (College Ave. and Cook Douglass)":
                        map.setImageResource(R.drawable.ee_route);
                        break;

                    case "F (College Ave. and Cook Douglass)":
                        map.setImageResource(R.drawable.f_route);
                        break;

                    case "H (Busch and College Ave.)":
                        map.setImageResource(R.drawable.h_route);
                        break;

                    case "LX (Livingston and College Ave.)":
                        map.setImageResource(R.drawable.lx_route);
                        break;

                    case "REX B (Busch. and Cook Douglass)":
                        map.setImageResource(R.drawable.rexb_route);
                        break;

                    case "REX L (Livingston and Cook Douglass)":
                        map.setImageResource(R.drawable.rexl_route);
                        break;

                    case "Weekend 1 (All Campuses)":
                        map.setImageResource(R.drawable.weekend1_route);
                        break;

                    case "Weekend 2 (All Campuses)":
                        map.setImageResource(R.drawable.weekend2_route);
                        break;

                    case "New Brunsquick 1":
                        map.setImageResource(R.drawable.brunsquick1_route);
                        break;

                    case "New Brunsquick 2":
                        map.setImageResource(R.drawable.brunsquick2_route);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_selection) {

            Intent intent = new Intent(this, route_select.class);
            startActivity(intent);

        } else if (id == R.id.nav_maps) {

            Intent intent = new Intent(this, maps.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_settings) {

            Intent intent = new Intent(this, settings.class);
            startActivity(intent);

        } else if (id == R.id.nav_info) {

            Intent intent = new Intent(this, info.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {

            Intent intent = new Intent(this, login.class);
            Toast.makeText(getApplicationContext(), "Successfully logged out.",
                    Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
