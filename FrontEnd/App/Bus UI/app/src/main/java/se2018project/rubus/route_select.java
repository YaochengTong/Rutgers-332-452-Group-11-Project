package se2018project.rubus;

import android.app.Dialog;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

public class route_select extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    GoogleMap mGoogleMap;
    String selectedRoute;
    String selectedStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // google map
        if(googleServicesAvailable()) {
            initMap();
        } else {
            // don't load map
        }

        // set route spinner
        Spinner route = findViewById(R.id.route_spinner);
        ArrayAdapter<CharSequence> route_adapter = ArrayAdapter.createFromResource(this,
                R.array.routes_array, android.R.layout.simple_spinner_item);
        route_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        route.setAdapter(route_adapter);


        // set default stop spinner
        Spinner stop = findViewById(R.id.stop_spinner);
        ArrayAdapter<CharSequence> stop_adapter = ArrayAdapter.createFromResource(this,
                R.array.A_array, android.R.layout.simple_spinner_item);
        route_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stop.setAdapter(stop_adapter);

        // route spinner listener
        route.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Spinner stop = findViewById(R.id.stop_spinner);

                // get currently selected stop text as String
                selectedRoute = parent.getItemAtPosition(position).toString();

                // fill stop spinner based upon route selection
                switch(selectedRoute) {

                    case "A (Busch and College Ave.)":
                        ArrayAdapter<CharSequence> A_adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.A_array, android.R.layout.simple_spinner_item);
                        A_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        stop.setAdapter(A_adapter);
                        break;

                    case "B (Busch and Livingston)":
                        ArrayAdapter<CharSequence> B_adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.B_array, android.R.layout.simple_spinner_item);
                        B_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        stop.setAdapter(B_adapter);
                        break;

                    case "C (Busch)":
                        ArrayAdapter<CharSequence> C_adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.C_array, android.R.layout.simple_spinner_item);
                        C_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        stop.setAdapter(C_adapter);
                        break;

                    case "EE (College Ave. and Cook Douglass)":
                        ArrayAdapter<CharSequence> EE_adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.EE_array, android.R.layout.simple_spinner_item);
                        EE_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        stop.setAdapter(EE_adapter);
                        break;

                    case "F (College Ave. and Cook Douglass)":
                        ArrayAdapter<CharSequence> F_adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.F_array, android.R.layout.simple_spinner_item);
                        F_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        stop.setAdapter(F_adapter);
                        break;

                    case "H (Busch and College Ave.)":
                        ArrayAdapter<CharSequence> H_adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.H_array, android.R.layout.simple_spinner_item);
                        H_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        stop.setAdapter(H_adapter);
                        break;

                    case "LX (Livingston and College Ave.)":
                        ArrayAdapter<CharSequence> LX_adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.LX_array, android.R.layout.simple_spinner_item);
                        LX_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        stop.setAdapter(LX_adapter);
                        break;

                    case "REX B (Busch. and Cook Douglass)":
                        ArrayAdapter<CharSequence> REXB_adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.REXB_array, android.R.layout.simple_spinner_item);
                        REXB_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        stop.setAdapter(REXB_adapter);
                        break;

                    case "REX L (Livingston and Cook Douglass)":
                        ArrayAdapter<CharSequence> REXL_adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.REXL_array, android.R.layout.simple_spinner_item);
                        REXL_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        stop.setAdapter(REXL_adapter);
                        break;

                    case "Weekend 1 (All Campuses)":
                        ArrayAdapter<CharSequence> WEEKEND1_adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.WEEKEND1_array, android.R.layout.simple_spinner_item);
                        WEEKEND1_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        stop.setAdapter(WEEKEND1_adapter);
                        break;

                    case "Weekend 2 (All Campuses)":
                        ArrayAdapter<CharSequence> WEEKEND2_adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.WEEKEND2_array, android.R.layout.simple_spinner_item);
                        WEEKEND2_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        stop.setAdapter(WEEKEND2_adapter);
                        break;

                    case "New Brunsquick 1":
                        ArrayAdapter<CharSequence> BRUNSQUICK1_adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.BRUNSQUICK1_array, android.R.layout.simple_spinner_item);
                        BRUNSQUICK1_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        stop.setAdapter(BRUNSQUICK1_adapter);
                        break;

                    case "New Brunsquick 2":
                        ArrayAdapter<CharSequence> BRUNSQUICK2_adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.BRUNSQUICK2_array, android.R.layout.simple_spinner_item);
                        BRUNSQUICK2_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        stop.setAdapter(BRUNSQUICK2_adapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // spinner listener
        stop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // get currently selected route text as String
                selectedStop = parent.getItemAtPosition(position).toString();
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
            finish();

        } else if (id == R.id.nav_maps) {

            Intent intent = new Intent(this, maps.class);
            startActivity(intent);

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

    // check for google play services
    public boolean googleServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if(isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Unable to connect to Google Play Services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private void initMap() {
        //MapFragment mapFragment = getFragmentManager().findFragmentById(R.id.mapFragment);
        //mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
    }

    public void go_button (View view) {
        Intent intent = new Intent(this, status.class);
        intent.putExtra("route", selectedRoute);
        intent.putExtra("stop", selectedStop);
        startActivity(intent);
    }

}
