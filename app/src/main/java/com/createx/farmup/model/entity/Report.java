package com.createx.farmup.model.entity;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Report {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime dateTime = LocalDateTime.now();

    public Report(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Report() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
                "id=" + id +
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
        return Objects.equals(id, report.id)
                && Objects.equals(title, report.title)
                && Objects.equals(content, report.content)
                && Objects.equals(dateTime, report.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, dateTime);
    }
}
