package com.exemple.exercicecours4.models;

public class DataStore {

    private static DataStore sharedInstance = new DataStore();

    public static DataStore getInstance() {
        return sharedInstance;
    }

    private Counter counter;
    private TemperatureConverter temperatureConverter;

    private DataStore() {
        counter = new Counter();
        temperatureConverter = new TemperatureConverter();
    }

    public Counter getCounter() {
        return counter;
    }

    public TemperatureConverter getTemperatureConverter() {
        return temperatureConverter;
    }
}
