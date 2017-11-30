package web.topicos.juho.clientsapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import web.topicos.juho.clientsapp.R;
import web.topicos.juho.clientsapp.adapters.CouponAdapter;
import web.topicos.juho.clientsapp.models.Coupon;

public class CouponsActivity extends AppCompatActivity {
    private final String url="http://192.168.43.56:8000/api/clients/coupons/";
    List<Coupon> coupons_list1=new ArrayList<Coupon>();
    RecyclerView rv_list_coupons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);
        rv_list_coupons = findViewById(R.id.rvcoupons_list);
        load_cupons();
    }

    public void load_cupons(){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET, url+getIntent().getIntExtra("clientId", 1), null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                generate_list(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(CouponsActivity.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        });
        requestQueue.add(jsonArrayRequest);
    }

    public void generate_list(JSONArray response)
    {
        for (int i=0;i<response.length();i++)
        {
            try
            {
                JSONObject jsonObject = response.getJSONObject(i);
                coupons_list1.add(new Coupon(
                        jsonObject.getInt("idCoupon"),
                        jsonObject.getString("couponCode"),
                        jsonObject.getString("validUntil"),
                        jsonObject.getString("description")
                ));

            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
        }
        rv_list_coupons.setAdapter(new CouponAdapter(this, coupons_list1));
        rv_list_coupons.setLayoutManager(new LinearLayoutManager(this));
    }
}
