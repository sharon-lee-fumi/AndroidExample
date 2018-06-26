package com.pointclickcare.brian.bundletest.ui.RestfulApi.services;

import com.pointclickcare.brian.bundletest.model.Zone;
import com.pointclickcare.brian.bundletest.model.ZoneList;
import com.pointclickcare.brian.bundletest.model.ZoneTime;
import com.pointclickcare.brian.bundletest.ui.RestfulApi.ZoneListAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeZoneDBClient {
    private TimeZoneDBService service = ServiceGenerator.createService(TimeZoneDBService.class);

    public void getZoneList(ZoneListAdapter adapter) {
        service.getTimeZoneList(TimeZoneDBService.apiKey, "json").enqueue(new Callback<ZoneList>() {
            @Override
            public void onResponse(Call<ZoneList> call, Response<ZoneList> response) {
                // 200: OK, 202: Accepted
                if (response.code() == 200 || response.code() == 202) {
                    // Todo: this code is ugly and refer to specific UI component in model class. How can we change this in much nicer way?
                    List<Zone> zoneList = response.body().getZones();
                    adapter.setSource(zoneList);
                    adapter.notifyDataSetChanged();
                } else {
                    // error
                }
            }

            @Override
            public void onFailure(Call<ZoneList> call, Throwable t) {
                // error
            }
        });
    }

    public void getZoneTime(String zone) {
        service.getZoneTime(TimeZoneDBService.apiKey, "json", "zone", zone).enqueue(new Callback<ZoneTime>() {
            @Override
            public void onResponse(Call<ZoneTime> call, Response<ZoneTime> response) {
                // 200: OK, 202: Accepted
                if (response.code() == 200 || response.code() == 202) {
                    ZoneTime zoneTime = response.body();
                    // do something
                } else {
                    // error
                }
            }

            @Override
            public void onFailure(Call<ZoneTime> call, Throwable t) {
                // error
            }
        });
    }
}
