package com.exemple.exercicecours3.models;

import java.util.ArrayList;

public class TemperatureConverter {

    public static class Result {
        final public float celsius;
        final public float fahrenheit;

        private Result(float celsius, float fahrenheit) {
            this.celsius = celsius;
            this.fahrenheit = fahrenheit;
        }
    }

    private ArrayList<Result> conversionsLog = new ArrayList<>();

    public Result convertFromCelsius(float valueC) {
        float valueF = valueC * 1.8f + 32;
        Result res = new Result(valueC, valueF);

        conversionsLog.add(res);
        return res;
    }

    public Result convertFromFahrenheit(float valueF) {
        float valueC = (valueF - 32) / 1.8f;
        Result res = new Result(valueC, valueF);

        conversionsLog.add(res);
        return res;
    }
}
