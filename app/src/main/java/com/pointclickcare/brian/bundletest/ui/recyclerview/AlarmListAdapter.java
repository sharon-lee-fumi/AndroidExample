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
import com.pointclickcare.brian.bundletest.databinding.ListAnotherTypeBinding;
import com.pointclickcare.brian.bundletest.model.Alarm;

import java.util.ArrayList;
import java.util.List;

public class AlarmListAdapter extends RecyclerView.Adapter<AlarmListAdapter.ViewHolder> {
    private List<ListItem> listItems = new ArrayList<>();
    private String[] daysString;

    void setSource(List<ListItem> list) {
        listItems = list;
    }

    void setDaysString(String[] daysString) {
        this.daysString = daysString;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                viewType == 0 ? R.layout.list_alarm : R.layout.list_another_type, parent, false);
        return new ViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder.binding instanceof ListAlarmBinding) {
            ListAlarmBinding binding = (ListAlarmBinding) holder.binding;
            Alarm alarm = (Alarm) listItems.get(position).data;
            boolean visible = listItems.get(position).selected;

            binding.setDaysStr(daysString);
            binding.setAlarm(alarm);
            binding.setVisibleExtraView(visible);
        } else if (holder.binding instanceof ListAnotherTypeBinding) {
            ListAnotherTypeBinding binding = (ListAnotherTypeBinding) holder.binding;
            String headerStr = (String) listItems.get(position).data;
            binding.setHeaderString(headerStr);
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        ListItem listItem = listItems.get(position);
        if (listItem.data instanceof Alarm) {
            return 0;
        } else {
            return 1;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;

        ViewHolder(View itemView, ViewDataBinding binding) {
            super(itemView);
            this.binding = binding;

            itemView.setOnClickListener((view) -> {
                int position = getAdapterPosition();
                listItems.get(position).selected ^= true;
                binding.setVariable(BR.visibleExtraView, listItems.get(position).selected);
            });
        }
    }
}
