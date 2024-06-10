package com.createx.farmup.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.createx.farmup.R;
import com.createx.farmup.model.Farmer;
import com.createx.farmup.view.FarmerDetailsActivity;

import java.util.ArrayList;

public class FarmerAdapter extends RecyclerView.Adapter<FarmerAdapter.FarmerViewHolder> {
    private final Context context;
    private final ArrayList<Farmer> farmers;

    // Provide a suitable constructor
    public FarmerAdapter(Context context, ArrayList<Farmer> farmers) {
        this.context = context;
        this.farmers = farmers;
    }

    // Create new views
    @NonNull
    @Override
    public FarmerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main, parent, false);
        return new FarmerViewHolder(view);
    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder(@NonNull FarmerViewHolder holder, int position) {
        Farmer farmer = farmers.get(position);
        holder.bind(farmer);
        holder.itemView.setOnClickListener(v -> {
            // Get the data associated with the clicked item
            String farmerName = farmer.getName();
            String farmerBio = farmer.getBio();
            String farmerImageUrl = farmer.getImageUrl();

            // Create an Intent to start the next activity
            Intent intent = new Intent(v.getContext(), FarmerDetailsActivity.class);
            intent.putExtra("farmer_name", farmerName);
            intent.putExtra("farmer_bio", farmerBio);
            intent.putExtra("farmer_image_url", farmerImageUrl);

            // Start the next activity
            v.getContext().startActivity(intent);
        });
    }

    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return farmers.size();
    }

    public static class FarmerViewHolder extends RecyclerView.ViewHolder {
        private final ImageView farmerImage;
        private final TextView farmerName;
        private final TextView farmerBio;

        public FarmerViewHolder(View view) {
            super(view);
            farmerImage = view.findViewById(R.id.farmer_image);
            farmerName = view.findViewById(R.id.farmer_name);
            farmerBio = view.findViewById(R.id.farmer_bio);
        }

        public void bind(Farmer farmer) {
            farmerName.setText(farmer.getName());
            farmerBio.setText(farmer.getBio());
            // Load image into farmerImage
            Glide.with(itemView.getContext())
                    .load(farmer.getImageUrl())
                    .into(farmerImage);
        }
    }
}

