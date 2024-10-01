package com.createx.farmup.view;

import android.annotation.SuppressLint;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.net.Uri;
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

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class FarmerDetailsActivity extends AppCompatActivity {
    ActivityFarmerDetailsBinding binding;
    public static final int REQUEST_VIDEO_CAPTURE = 1;
    public static final int REQUEST_IMAGE_CAPTURE = 2;
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
            dispatchTakeMediaIntent();
        });

        binding.diseaseCard.setOnClickListener(v -> {
            // Handle disease card click
            dispatchTakeMediaIntent();
        });

        binding.pestCard.setOnClickListener(v -> {
            // Handle pest card click
            dispatchTakeMediaIntent();
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
    private void dispatchTakeMediaIntent() {
        final CharSequence[] options = { "Take Photo", "Record Video", "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose your action");
        builder.setItems(options, (dialog, item) -> {
            if (options[item].equals("Take Photo")) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityIfNeeded(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            } else if (options[item].equals("Record Video")) {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityIfNeeded(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
                }
            } else if (options[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private static final Logger LOGGER = Logger.getLogger(FarmerDetailsActivity.class.getName());

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            assert extras != null;
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            // Save the bitmap to a file
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            String imageFileName = "JPEG_" + timeStamp + ".jpg";
            try {
                OutputStream fOut;
                File file = new File(getExternalFilesDir(null), imageFileName); // the File to save , append increasing numeric counter to prevent files from getting overwritten.
                fOut = Files.newOutputStream(file.toPath());

                assert imageBitmap != null;
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
                fOut.flush(); // Not really required
                fOut.close(); // do not forget to close the stream

                MediaStore.Images.Media.insertImage(getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error saving image", e);
            }
        } else if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            String videoFileName = "VID_" + timeStamp + ".mp4";
            try {
                assert videoUri != null;
                InputStream inputStream = getContentResolver().openInputStream(videoUri);
                OutputStream outputStream = Files.newOutputStream(new File(getExternalFilesDir(null), videoFileName).toPath());
                byte[] buffer = new byte[1024];
                int length;
                while (true) {
                    assert inputStream != null;
                    if (!((length = inputStream.read(buffer)) > 0)) break;
                    outputStream.write(buffer, 0, length);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error saving video", e);
            }
        }
    }

}