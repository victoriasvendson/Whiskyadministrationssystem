package model;

import java.io.Serializable;

public class Malt implements Serializable {
    private String mark;
    private String kornsort;

    public Malt(String mark, String kornsort) {
        this.mark = mark;
        this.kornsort = kornsort;
    }

    public String getMark() {
        return mark;
    }

    public String getKornsort() {
        return kornsort;
    }

    @Override
    public String toString() {
        return "Kornsort: " + kornsort + "\nMark: " + mark;
    }
}