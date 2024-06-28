package com.createx.farmup.model.entity;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity(tableName = "report")
public class Report extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "report_id")
    private Integer reportId;
    @ColumnInfo(name = "report_title")
    private String title;
    @ColumnInfo(name = "report_content")
    private String content;
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    private LocalDateTime dateTime = LocalDateTime.now();

    public Report(Integer reportId, String title, String content) {
        this.reportId = reportId;
        this.title = title;
        this.content = content;
    }

    public Report() {}

    @Bindable
    public Integer getId() {
        return reportId;
    }

    public void setId(Integer id) {
        this.reportId = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        notifyPropertyChanged(BR.content);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String createDateFormatted() {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss"));
    }

    @NonNull
    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report)) return false;
        Report report = (Report) o;
        return Objects.equals(reportId, report.reportId)
                && Objects.equals(title, report.title)
                && Objects.equals(content, report.content)
                && Objects.equals(dateTime, report.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportId, title, content, dateTime);
    }
}
