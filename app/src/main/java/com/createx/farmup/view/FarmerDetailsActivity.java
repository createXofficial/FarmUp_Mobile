package com.createx.farmup.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.createx.farmup.R;
import com.createx.farmup.databinding.ActivityFarmerDetailsBinding;
import com.createx.farmup.model.entity.Farmer;

import java.util.Objects;

public class FarmerDetailsActivity extends AppCompatActivity {
    ActivityFarmerDetailsBinding binding;
    public static final int REQUEST_VIDEO_CAPTURE = 1;
    public static final String FARMER_ID = "farmer_id";
    public static final String FARMER_IMAGE = "farmer_image";
    public static final String FARMER_NAME = "farmer_name";
    public static final String FARMER_BIO = "farmer_bio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_farmer_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.farmer_bio), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = DataBindingUtil.setContentView(this, R.layout.activity_farmer_details);
        Farmer farmer = new Farmer();
        binding.setFarmer(farmer);

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Intent intent = getIntent();
        if (intent.hasExtra(FARMER_ID)) {
            farmer.setName(intent.getStringExtra(FARMER_NAME));
            Glide.with(this)
                            .load(intent.getIntExtra(FARMER_IMAGE, R.drawable.farmer))
                            .apply(new RequestOptions().circleCrop())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .skipMemoryCache(false)
                            .into(binding.profileImage);
            farmer.setBio(intent.getStringExtra(FARMER_BIO));
        } else {
            Toast.makeText(this, "No data sent", Toast.LENGTH_LONG).show();
        }


        binding.weatherCard.setOnClickListener(v -> {
            // Handle weather card click
            dispatchTakeVideoIntent();
        });

        binding.diseaseCard.setOnClickListener(v -> {
            // Handle disease card click
            dispatchTakeVideoIntent();
        });

        binding.pestCard.setOnClickListener(v -> {
            // Handle pest card click
            dispatchTakeVideoIntent();
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_app_bar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityIfNeeded(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        } else {
            //display error state to the user
            Toast.makeText(this, "Unable to open camera", Toast.LENGTH_SHORT).show();
        }
    }
}