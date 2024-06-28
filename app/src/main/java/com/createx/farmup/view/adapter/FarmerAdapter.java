package com.createx.farmup.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.createx.farmup.R;
import com.createx.farmup.databinding.MainBinding;
import com.createx.farmup.model.entity.Farmer;

import java.util.ArrayList;

public class FarmerAdapter extends RecyclerView.Adapter<FarmerAdapter.FarmerViewHolder> {
    private OnFarmerItemClickListener listener;
    private final Context context;
    private ArrayList<Farmer> farmers;

    // Provide a suitable constructor
    public FarmerAdapter(Context context) {
        this.context = context;
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

    public void setFarmers(ArrayList<Farmer> newFarmers) {
        final DiffUtil.DiffResult result =
                DiffUtil.calculateDiff(new FarmerDiffCallback(farmers, newFarmers), false);

        farmers = newFarmers;
        result.dispatchUpdatesTo(FarmerAdapter.this);
    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder(@NonNull FarmerViewHolder holder, int position) {
        Farmer currentFarmer = farmers.get(position);
        holder.mainBinding.setFarmer(currentFarmer);
        holder.bindImage(currentFarmer.getImage());
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
                    .apply(new RequestOptions().circleCrop())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
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

