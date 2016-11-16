package com.example.yahya.senddatatoserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import app.RequestQueueSingleton;

public class MainActivity extends AppCompatActivity {
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
                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        curl+"?op=2&msg="+message.getText().toString(),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //System.out.println("I C RESPONSE");
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //System.out.println("ERROR HAPPENED!");
                    }
                });
                message.setText("");
                // Add the request to the RequestQueue.
                RequestQueueSingleton.getInstance().addToRequestQueue(stringRequest);
            }
        });

    }
}
