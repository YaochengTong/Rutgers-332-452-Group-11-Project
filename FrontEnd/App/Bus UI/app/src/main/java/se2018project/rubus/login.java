package se2018project.rubus;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // password reset web page
        TextView Button = findViewById(R.id.forget_button);

        Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://netid.rutgers.edu/displayForgottenPasswordForm.htm"));
                startActivity(intent);
            }
        });

    }


    // called when the user taps the login button
    public void login_button(View view) {
        Intent intent = new Intent(this, route_select.class);

        EditText netid_input = findViewById(R.id.netid_input);
        String netid = netid_input.getText().toString();
        EditText password_input = findViewById(R.id.password_input);
        String password = password_input.getText().toString();

        // TODO temporary login dummy data until CAS integration
        if(true) {
            Toast.makeText(getApplicationContext(), "Login successful.",
                    Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }

        // toast error notification for incorrect netid/password
        else {
            Toast.makeText(getApplicationContext(), "Invalid NetID or Password.",
                    Toast.LENGTH_SHORT).show();
        }
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

    @Override
    public void onBackPressed() {

    }

}
