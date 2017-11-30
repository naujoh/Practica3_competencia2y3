package web.topicos.juho.clientsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import web.topicos.juho.clientsapp.R;

/**
 * Created by juho on 30/11/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public final String URL = "http://192.168.43.56:8000/api/clients/login";
    TextInputEditText tieEmail, tiePass;
    Button btnLogin;
    HashMap<String, String> params;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        tieEmail = (TextInputEditText) findViewById(R.id.tieEmail);
        tiePass = (TextInputEditText) findViewById(R.id.tiePass);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnLogin){
            String email = tieEmail.getText().toString();
            String pass = tiePass.getText().toString();

            if (!email.isEmpty() && !pass.isEmpty()) {
                params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", pass);
                RequestQueue requestQueue= Volley.newRequestQueue(this);
                JsonObjectRequest jsonobjectrequest =
                        new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(params),
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {

                                    logueo(response);
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(LoginActivity.this,
                                                  "Ingresa un email y una contraseña",
                                                        Toast.LENGTH_SHORT).show();
                                        error.printStackTrace();
                                    }
                                });
                requestQueue.add(jsonobjectrequest);
            } else
                Snackbar.make(findViewById(R.id.relativeLayoutLogin),
                        "Verifica que los datos ingresados sean correctos",
                              Snackbar.LENGTH_LONG).show();
        }
    }

    public void logueo(JSONObject response){
        Log.i("RESPONSE", response.length() + "");
        try{
            if(response.getBoolean("login")){
                JSONObject client = response.getJSONObject("client");
                System.out.println(client);
                Bundle params = new Bundle();
                params.putInt("clientId", client.getInt("idClient"));
                params.putString("name", client.getString("clientName"));
                params.putString("email", client.getString("email"));
                params.putString("address", client.getString("address"));
                params.putString("phone", client.getString("phoneNumber"));
                Intent i = new Intent(this, TabActivity.class);
                i.putExtras(params);
                startActivity(i);
            }else
                Toast.makeText(LoginActivity.this,
                        "Usuario o contraseña incorrectos",
                        Toast.LENGTH_SHORT).show();


        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
