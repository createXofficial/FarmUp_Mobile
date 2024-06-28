package com.createx.farmup.view.adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.createx.farmup.model.entity.Report;

import java.util.ArrayList;
import java.util.Objects;

public class ReportDiffCallback extends DiffUtil.Callback {
    ArrayList<Report> oldReports;
    ArrayList<Report> newReports;

    public ReportDiffCallback(ArrayList<Report> oldReports, ArrayList<Report> newReports) {
        this.oldReports = oldReports;
        this.newReports = newReports;
    }

    @Override
    public int getOldListSize() {
        return oldReports == null ? 0 : oldReports.size();
    }

    @Override
    public int getNewListSize() {
        return newReports == null ? 0 : newReports.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return Objects.equals(oldReports.get(oldItemPosition).getId(), newReports.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldReports.get(oldItemPosition).equals(newReports.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
