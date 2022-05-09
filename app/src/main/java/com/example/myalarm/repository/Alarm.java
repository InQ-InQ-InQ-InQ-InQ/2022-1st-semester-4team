package com.example.myalarm.repository;

public class Alarm {
    private boolean dateMod, dayMod;
    private int hour, minute;
    private boolean [] dayArray;
    private String name;
    private boolean sound, vibe, recall;

    public Alarm(boolean dateMod, boolean dayMod, int hour, int minute, boolean [] dayArray, String name, boolean sound, boolean vibe, boolean recall) {
        this.dateMod = dateMod;
        this.dayMod = dayMod;
        this.hour = hour;
        this.minute = minute;
        this.dayArray = dayArray;
        this.name = name;
        this.sound = sound;
        this.vibe = vibe;
        this.recall = recall;
    }
}
