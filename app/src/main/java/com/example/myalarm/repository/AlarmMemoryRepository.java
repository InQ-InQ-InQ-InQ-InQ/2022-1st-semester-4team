package com.example.myalarm.repository;

import com.example.myalarm.Alarm;

import java.util.ArrayList;
import java.util.List;

public class AlarmMemoryRepository implements AlarmRepository {

    static private List<Alarm> repository = new ArrayList<>();

    @Override
    public Alarm createAlarm(Alarm alarm) {
        repository.add(alarm);
        return alarm;
    }

    @Override
    public List<Alarm> getAlarmList() {
        return repository;
    }
}
