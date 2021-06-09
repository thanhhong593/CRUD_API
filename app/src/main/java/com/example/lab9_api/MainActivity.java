package com.example.lab9_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView tvDisplay;
    Button btnClick,btnDelete;
    RecyclerView recyclerView;
    RecyclervAdapter recyclervAdapter;
    List<Student> arrList = new ArrayList<>();
    String url = "https://60ade95c80a61f0017331f03.mockapi.io/student";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClick = (Button) findViewById(R.id.btnClick);

        recyclerView= findViewById(R.id.rclView);
        tvDisplay=findViewById(R.id.textView);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                GetData(url);



//               GetJson(url);
                GetArrayJson(url);
//                PostApi(url);
//                PutApi(url);
//                DeleteApi(url);


                recyclervAdapter = new RecyclervAdapter((ArrayList<Student>) arrList,MainActivity.this);
                recyclerView.setAdapter(recyclervAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));

            }
        });

    }
    private void GetData(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tvDisplay.setText(response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,
                        "Error make by API server!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void GetArrayJson(String url){
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0; i<response.length(); i++){
                            try {
                                JSONObject object = (JSONObject) response.get(i);
                                Student user = new Student();
                                user.setId(object.getString("id"));
                                user.setName(object.getString("name"));
                                user.setClass1(object.getString("class"));
                                user.setEmail(object.getString("email"));
                                arrList.add(user);
                                recyclervAdapter.setList((ArrayList)arrList);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error by get Json Array!", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void PutApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, url + '/' + 28, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("name", "Lâm");
                params.put("age", "30");

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void DeleteApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE, url + '/' + 1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}