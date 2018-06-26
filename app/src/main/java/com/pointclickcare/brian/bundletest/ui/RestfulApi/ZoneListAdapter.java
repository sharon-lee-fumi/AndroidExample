package com.pointclickcare.brian.bundletest.ui.RestfulApi;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pointclickcare.brian.bundletest.R;
import com.pointclickcare.brian.bundletest.databinding.ListZoneBinding;
import com.pointclickcare.brian.bundletest.model.Zone;
import com.pointclickcare.brian.bundletest.ui.RestfulApi.services.TimeZoneDBClient;

import java.util.ArrayList;
import java.util.List;

public class ZoneListAdapter extends RecyclerView.Adapter<ZoneListAdapter.ViewHolder> {
    private List<Zone> listZone = new ArrayList<>();

    public void setSource(List<Zone> list) {
        listZone = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListZoneBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.list_zone, parent, false);
        return new ViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Zone zone = listZone.get(position);
        holder.binding.setZoneInfo(zone);
        holder.zone = zone;
    }

    @Override
    public int getItemCount() {
        return listZone.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ListZoneBinding binding;
        Zone zone;

        ViewHolder(View itemView, ListZoneBinding binding) {
            super(itemView);
            this.binding = binding;

            itemView.setOnClickListener((view) ->
                    new TimeZoneDBClient().getZoneTime(zone.getZoneName())
            );
        }
    }
}
