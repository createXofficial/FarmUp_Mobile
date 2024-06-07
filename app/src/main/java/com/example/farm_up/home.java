package com.example.farm_up;

import android.os.Bundle;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class home extends AppCompatActivity {
    ArrayList<Farmer> farmers = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Add some test data
        farmers.add(new Farmer("Farmer 1", "Farmer 1 Bio", R.drawable.farmer));
        farmers.add(new Farmer("Farmer 2", "Farmer 2 Bio", R.drawable.farmer));
        farmers.add(new Farmer("Farmer 3", "Farmer 3 Bio", R.drawable.farmer));

        init();
        RequestJsonData();
    }

    public void init() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void RequestJsonData() {
        Log.d("request", "called ");
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://cv2-staging.cad4tb.care/api/v2/patients",
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("results");
                        fetchFarmersData(jsonArray);
                    } catch (Exception e) {

                        Log.e("onResponseError", "onResponse: ", e);
                    }
                }, volleyError -> Log.d("onErrorResponse", "onErrorResponse: " + volleyError)) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                String token = "58516a26bb6c6db5f326604f729c311d36865176";
                headers.put("Authorization", "token " + token);
                return headers;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void fetchFarmersData(JSONArray jsonArray) {

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                farmers.add(new Farmer(
                        jsonObject.getString("PatientID"),
                        jsonObject.getString("id"),
                       R.drawable.farmer
                ));

            } catch (Exception e) {
                Log.e("response error", "onResponse: ", e );
            }
        }
        FarmerAdapter adapter = new FarmerAdapter(this,farmers);
        recyclerView.setAdapter(adapter);
    }
}