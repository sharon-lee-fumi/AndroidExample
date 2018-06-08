package com.pointclickcare.brian.bundletest.ui.recyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pointclickcare.brian.bundletest.BR;
import com.pointclickcare.brian.bundletest.R;
import com.pointclickcare.brian.bundletest.databinding.ListAlarmBinding;
import com.pointclickcare.brian.bundletest.databinding.ListExtraBinding;
import com.pointclickcare.brian.bundletest.model.Alarm;

import java.util.ArrayList;
import java.util.List;

public class AlarmListAdapter extends RecyclerView.Adapter<AlarmListAdapter.ViewHolder> {
    private List<Alarm> mAlarmList = new ArrayList<>();
    private String[] daysString;

    void setSource(List<Alarm> list) {
        mAlarmList = list;
    }

    void setDaysString(String[] daysString) {
        this.daysString = daysString;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                viewType == 0 ? R.layout.list_alarm : R.layout.list_another_type, parent, false);
        binding.getRoot().setOnClickListener((view) -> binding.setVariable(BR.visibleExtraView, true));
        return new ViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder.binding instanceof ListAlarmBinding) {
            ListAlarmBinding binding = (ListAlarmBinding) holder.binding;

            binding.setDaysStr(daysString);

            // TODO: Now, these two sentences have two bugs. What are they?
            binding.setAlarm(mAlarmList.get(position));
            // the position is the cell's position which include list_another_type
            // so the alarm number skips counting by 5s

            binding.setVisibleExtraView(false);
            // click on the cell, VisibleExtraView is visible
            // then scroll up/down the RecyclerView, the visibleExtraView will be invisible

        } else if (holder.binding instanceof ListExtraBinding) {
            ListExtraBinding binding = (ListExtraBinding) holder.binding;
            binding.setVisible(true);
        }
    }

    @Override
    public int getItemCount() {
        return mAlarmList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 5 == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;

        ViewHolder(View itemView, ViewDataBinding binding) {
            super(itemView);
            this.binding = binding;
        }
    }
}
