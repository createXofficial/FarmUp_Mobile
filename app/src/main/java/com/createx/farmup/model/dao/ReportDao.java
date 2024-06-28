package com.createx.farmup.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.createx.farmup.model.entity.Report;

import java.util.List;

@Dao
public interface ReportDao {
    @Insert
    void insert(Report report);

    @Update
    void update(Report report);

    @Delete
    void delete(Report report);

    @Query("SELECT * FROM report")
    LiveData<List<Report>> getAllReports();
}
