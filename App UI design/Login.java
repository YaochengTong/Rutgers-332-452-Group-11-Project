package com.example.a41157.rutgers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private ImageView LoginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginBtn = (ImageView)findViewById(R.id.login);
        LoginBtn.setOnClickListener(this);

        //Check Net
        if(Check.getNetState(this)==Check.NET_NONE) {
            Log.d("MWEATHER", "Net Disconnect");
            Toast.makeText(Login.this, "Net Disconnect", Toast.LENGTH_LONG).show();
        }
        else{
            Log.d("MWEATHER","Net Connect");
            Toast.makeText(Login.this,"Net Coneect", Toast.LENGTH_LONG).show();
        }

    // Example of a call to a native method
    TextView tv = (TextView) findViewById(R.id.sample_text);
    tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    public void onClick(View view) {
        if(v.getID()==R.id.login){
            Intent intent=new Intent(this, bus.class);
            startActivity(intent);
        }
    }
}
