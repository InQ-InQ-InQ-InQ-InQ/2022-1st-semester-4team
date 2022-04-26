package com.example.myalarm.repository;

import com.example.myalarm.Alarm;

import java.util.List;

public interface AlarmRepository {
    Alarm createAlarm(Alarm alarm);
    List<Alarm> getAlarmList();
}
