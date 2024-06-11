package com.createx.farmup.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.createx.farmup.R;

public class FarmerDetailsActivity extends AppCompatActivity {
    CardView weatherCard, diseaseCard, pestCard;
    public static final int REQUEST_VIDEO_CAPTURE = 1;

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

        weatherCard = findViewById(R.id.weather_card);
        diseaseCard = findViewById(R.id.disease_card);
        pestCard = findViewById(R.id.pest_card);

        weatherCard.setOnClickListener(v -> {
            // Handle weather card click
            dispatchTakeVideoIntent();
        });
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