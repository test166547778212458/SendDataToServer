package com.example.yahya.senddatatoserver;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import app.RequestQueueSingleton;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    EditText message;
    Button send;
    String path="/getData.php";
    String server = "http://192.168.1.30/android";
    String curl = server+path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = (Button) findViewById(R.id.Send);
        message = (EditText) findViewById(R.id.editText2);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String encodedMessage = null;
                try {
                    encodedMessage = URLEncoder.encode(message.getText().toString(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        curl+"?op=2&msg="+encodedMessage,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, response.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                });
                message.setText("");
                // Add the request to the RequestQueue.
                RequestQueueSingleton.getInstance().addToRequestQueue(stringRequest);
            }
        });

    }
}
