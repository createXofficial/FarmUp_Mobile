package com.createx.farmup.model.repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.createx.farmup.model.dao.ReportDao;
import com.createx.farmup.model.db.ReportDatabase;
import com.createx.farmup.model.entity.Report;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReportRepository {
    private ReportDao reportDao;

    private LiveData<List<Report>> reports;

    public ReportRepository(Application application) {
        ReportDatabase reportDatabase = ReportDatabase.getInstance(application);
        reportDao = reportDatabase.getReportDao();
    }

    public LiveData<List<Report>> getReports() {
        reports = reportDao.getAllReports();
        return reports;
    }

    public void insertReport(Report report) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(() -> handler.post(() -> {
            // insert a report
            reportDao.insert(report);
        }));
    }

    public void updateReport(Report report) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(() -> handler.post(() -> {
            // update a report
            reportDao.update(report);
        }));
    }

    public void deleteReport(Report report) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(() -> handler.post(() -> {
            // delete a report
            reportDao.delete(report);
        }));
    }
}
