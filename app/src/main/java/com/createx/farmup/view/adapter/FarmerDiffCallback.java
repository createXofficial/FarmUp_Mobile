package com.createx.farmup.view.adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.createx.farmup.model.entity.Farmer;

import java.util.ArrayList;

public class FarmerDiffCallback extends DiffUtil.Callback {
    ArrayList<Farmer> oldFarmers;
    ArrayList<Farmer> newFarmers;

    public FarmerDiffCallback(ArrayList<Farmer> oldFarmers, ArrayList<Farmer> newFarmers) {
        this.oldFarmers = oldFarmers;
        this.newFarmers = newFarmers;
    }

    @Override
    public int getOldListSize() {
        return oldFarmers == null ? 0 : oldFarmers.size();
    }

    @Override
    public int getNewListSize() {
        return newFarmers == null ? 0 : newFarmers.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldFarmers.get(oldItemPosition).getId() == newFarmers.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldFarmers.get(oldItemPosition).equals(newFarmers.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
