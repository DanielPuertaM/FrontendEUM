package com.example.espacios_um.utils;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTypeAdapter extends  TypeAdapter<LocalDateTime> {


    @Override
    public void write(JsonWriter out, LocalDateTime value) throws IOException {
        out.value(value == null ? null : value.toString());
    }

    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        String str = in.nextString();
        return (str == null) ? null : LocalDateTime.parse(str);
    }
}