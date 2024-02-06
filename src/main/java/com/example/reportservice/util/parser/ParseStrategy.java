package com.example.reportservice.util.parser;

public interface ParseStrategy<T> {
    T parse(String line);
}