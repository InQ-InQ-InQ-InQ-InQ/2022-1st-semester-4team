package com.example.myalarm.repository;

import java.util.List;

public interface AlarmRepository {
    Alarm createAlarm(Alarm alarm);
    List<Alarm> getAlarmList();
}
