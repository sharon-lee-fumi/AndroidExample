package com.pointclickcare.brian.bundletest.ui.recyclerview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pointclickcare.brian.bundletest.R;
import com.pointclickcare.brian.bundletest.databinding.FragmentRecyclerViewBinding;
import com.pointclickcare.brian.bundletest.model.Alarm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecyclerViewExampleFragment extends Fragment {
    FragmentRecyclerViewBinding binding;
    List<Alarm> alarmList = new ArrayList<>();
    AlarmListAdapter adapter;

    public static RecyclerViewExampleFragment newInstance() {
        RecyclerViewExampleFragment fragment = new RecyclerViewExampleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alarmList = generateAlarmData(100);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRecyclerViewBinding.inflate(getLayoutInflater());

        adapter = new AlarmListAdapter();
        adapter.setSource(alarmList);
        adapter.setDaysString(getResources().getStringArray(R.array.days_string));

        binding.listAlarm.setAdapter(adapter);
        binding.listAlarm.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return binding.getRoot();
    }

    private List<Alarm> generateAlarmData(int count) {
        List<Alarm> alarmList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Alarm alarm = new Alarm();
            alarm.name = "Alarm_" + String.valueOf(i);
            alarm.time = new Date(new Date().getTime() + (long) (Math.random() * 10000));
            Boolean[] days = new Boolean[7];
            for (int j = 0; j < days.length; j++) {
                days[j] = (long) (Math.random() * 100) % 2 != 0;
            }
            alarm.days = days;
            alarmList.add(alarm);
        }

        return alarmList;
    }
}
