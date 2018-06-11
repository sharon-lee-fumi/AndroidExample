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
    List<ListItem> listItems = new ArrayList<>();
    AlarmListAdapter adapter;

    public static RecyclerViewExampleFragment newInstance() {
        RecyclerViewExampleFragment fragment = new RecyclerViewExampleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listItems = generateAlarmData(100);
        addListHeaderAtRandomPlace(listItems);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRecyclerViewBinding.inflate(getLayoutInflater());

        adapter = new AlarmListAdapter();
        adapter.setSource(listItems);
        adapter.setDaysString(getResources().getStringArray(R.array.days_string));

        binding.listAlarm.setAdapter(adapter);
        binding.listAlarm.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return binding.getRoot();
    }

    private List<ListItem> generateAlarmData(int count) {
        List<ListItem> alarmList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Alarm alarm = new Alarm();
            alarm.name = "Alarm_" + String.valueOf(i);
            alarm.time = new Date(new Date().getTime() + (long) (Math.random() * 10000));
            Boolean[] days = new Boolean[7];
            for (int j = 0; j < days.length; j++) {
                days[j] = (long) (Math.random() * 100) % 2 != 0;
            }
            alarm.days = days;
            alarmList.add(new ListItem<>(alarm));
        }

        return alarmList;
    }

    private void addListHeaderAtRandomPlace(List<ListItem> list) {
        int numHeader = list.size() / 5;
        for (int i = 0; i < numHeader; i++) {
            ListItem headerListItem = new ListItem<>(getString(R.string.header_title));
            list.add((int) (list.size() * Math.random()), headerListItem);
        }
    }
}
