package com.createx.farmup;

import static com.createx.farmup.view.FarmerDetailsActivity.FARMER_BIO;
import static com.createx.farmup.view.FarmerDetailsActivity.FARMER_ID;
import static com.createx.farmup.view.FarmerDetailsActivity.FARMER_IMAGE;
import static com.createx.farmup.view.FarmerDetailsActivity.FARMER_NAME;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.createx.farmup.model.entity.Farmer;
import com.createx.farmup.view.FarmerDetailsActivity;
import com.createx.farmup.view.adapter.FarmerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Farmer> farmers = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Add some test data
        farmers.add(new Farmer("Farmer 1", "Farmer 1 Bio", R.drawable.farm));
        farmers.add(new Farmer("Farmer 2", "Farmer 2 Bio", R.drawable.image));
        farmers.add(new Farmer("Farmer 3", "Farmer 3 Bio", R.drawable.pex));
        farmers.add(new Farmer("Farmer 4", "Farmer 4 Bio", R.drawable.pexel));
        farmers.add(new Farmer("Farmer 5", "Farmer 5 Bio", R.drawable.profile));

        init();
//        requestJsonData();
    }

    public void init() {
        recyclerView = findViewById(R.id.farmer_recycler_view);
        recyclerView.hasFixedSize();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FarmerAdapter adapter = new FarmerAdapter(this);
        adapter.setFarmers(farmers);

        adapter.setListener(farmer -> {
            long selectedFarmerId = farmer.getFarmerId();
            Intent intent = new Intent(this, FarmerDetailsActivity.class);

            intent.putExtra(FARMER_ID, selectedFarmerId);
            intent.putExtra(FARMER_NAME, farmer.getName());
            intent.putExtra(FARMER_IMAGE, farmer.getImage());
            intent.putExtra(FARMER_BIO, farmer.getBio());

            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }

//    private void requestJsonData() {
//        Log.d("request", "called ");
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET,
//                "https://cv2-staging.cad4tb.care/api/v2/patients",
//                response -> {
//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                        JSONArray jsonArray = jsonObject.getJSONArray("results");
//                        fetchFarmersData(jsonArray);
//                    } catch (Exception e) {
//
//                        Log.e("onResponseError", "onResponse: ", e);
//                    }
//                }, volleyError -> Log.d("onErrorResponse", "onErrorResponse: " + volleyError)) {
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String, String> headers = new HashMap<>();
//                String token = "58516a26bb6c6db5f326604f729c311d36865176";
//                headers.put("Authorization", "token " + token);
//                return headers;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }

//    private void fetchFarmersData(JSONArray jsonArray) {
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//            try {
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                farmers.add(new Farmer(
//                        jsonObject.getString("PatientID"),
//                        jsonObject.getString("id"),
//                        R.drawable.farmer
//                ));
//
//            } catch (Exception e) {
//                Log.e("response error", "onResponse: ", e );
//            }
//        }
//        FarmerAdapter adapter = new FarmerAdapter(this);
//        adapter.setFarmers(farmers);
//
//        adapter.setListener(farmer -> {
//            Intent intent = new Intent(this, FarmerDetailsActivity.class);
//        });
//        recyclerView.setAdapter(adapter);
//    }
}