package com.createx.farmup.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.createx.farmup.R;
import com.createx.farmup.databinding.MainBinding;
import com.createx.farmup.model.entity.Farmer;
import com.createx.farmup.view.FarmerDetailsActivity;

import java.util.ArrayList;

public class FarmerAdapter extends RecyclerView.Adapter<FarmerAdapter.FarmerViewHolder> {
    private OnFarmerItemClickListener listener;
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
        MainBinding mainBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.main,
                parent,
                false
        );
        return new FarmerViewHolder(mainBinding);
    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder(@NonNull FarmerViewHolder holder, int position) {
        Farmer farmer = farmers.get(position);
        holder.mainBinding.setFarmer(farmer);
//        holder.bind(farmer);
//        holder.itemView.setOnClickListener(v -> {
//            // Get the data associated with the clicked item
//            String farmerName = farmer.getName();
//            String farmerBio = farmer.getBio();
//            String farmerImageUrl = farmer.getImageUrl();
//
//            // Create an Intent to start the next activity
//            Intent intent = new Intent(v.getContext(), FarmerDetailsActivity.class);
//            intent.putExtra("farmer_name", farmerName);
//            intent.putExtra("farmer_bio", farmerBio);
//            intent.putExtra("farmer_image_url", farmerImageUrl);
//
//            // Start the next activity
//            v.getContext().startActivity(intent);
//        });
    }

    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return farmers != null ? farmers.size() : 0;
    }

    public class FarmerViewHolder extends RecyclerView.ViewHolder {
        private final MainBinding mainBinding;

        public FarmerViewHolder(MainBinding mainBinding) {
            super(mainBinding.getRoot());
            this.mainBinding = mainBinding;

            mainBinding.getRoot().setOnClickListener(view -> {
                int clickedPosition = getAdapterPosition();
                if (listener != null && clickedPosition != RecyclerView.NO_POSITION) {
                    listener.onFarmerItemClick(farmers.get(clickedPosition));
                }
            });
        }

        public void bindImage(int image) {
            Glide.with(mainBinding.farmerImage.getContext())
                    .load(image)
                    .into(mainBinding.farmerImage);
        }

//        public void bind(Farmer farmer) {
//            farmerName.setText(farmer.getName());
//            farmerBio.setText(farmer.getBio());
//            // Load image into farmerImage
//            Glide.with(itemView.getContext())
//                    .load(farmer.getImageUrl())
//                    .into(farmerImage);
//        }
    }

    public interface OnFarmerItemClickListener {
        void onFarmerItemClick(Farmer farmer);
    }

    public void setListener(OnFarmerItemClickListener listener) {
        this.listener = listener;
    }
}

