package com.createx.farmup.model.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.createx.farmup.model.Converters;
import com.createx.farmup.model.dao.ReportDao;
import com.createx.farmup.model.entity.Report;

@Database(entities = {Report.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class ReportDatabase extends RoomDatabase {

    public abstract ReportDao getReportDao();

    // Singleton instance
    private static ReportDatabase instance;

    public static synchronized ReportDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ReportDatabase.class,
                    "report_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
