package com.pointclickcare.brian.bundletest.ui.RestfulApi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pointclickcare.brian.bundletest.databinding.FragmentRestfulApiBinding;
import com.pointclickcare.brian.bundletest.ui.RestfulApi.services.TimeZoneDBClient;

public class RestfulAPIExampleFragment extends Fragment {
    FragmentRestfulApiBinding binding;
    ZoneListAdapter adapter;

    public static RestfulAPIExampleFragment newInstance() {
        RestfulAPIExampleFragment fragment = new RestfulAPIExampleFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRestfulApiBinding.inflate(getLayoutInflater());

        adapter = new ZoneListAdapter();
        binding.listZone.setAdapter(adapter);
        binding.listZone.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        new TimeZoneDBClient().getZoneList(adapter);
    }
}
