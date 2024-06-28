package com.createx.farmup.model;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;

public class Converters {
    @TypeConverter
    public LocalDateTime fromTimeStamp(String value) {
        return LocalDateTime.parse(value);
    }

    @TypeConverter
    public String dateToTimeStamp(LocalDateTime date) {
        return date.toString();
    }
}
