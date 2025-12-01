package model;

public class Malt {
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
}