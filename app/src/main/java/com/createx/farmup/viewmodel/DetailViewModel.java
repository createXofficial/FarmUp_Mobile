package com.createx.farmup.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.createx.farmup.model.entity.Report;
import com.createx.farmup.model.repository.ReportRepository;

import java.util.List;

public class DetailViewModel extends AndroidViewModel {
    // repository
    private final ReportRepository repository;

    // live data
    private LiveData<List<Report>> allReports;
    public DetailViewModel(@NonNull Application application) {
        super(application);
        repository = new ReportRepository(application);
    }

    public LiveData<List<Report>> getAllReports() {
        allReports = repository.getReports();
        return allReports;
    }

    public void insertReport(Report report) {
        repository.insertReport(report);
    }

    public void updateReport(Report report) {
        repository.updateReport(report);
    }

    public void deleteReport(Report report) {
        repository.deleteReport(report);
    }
}
