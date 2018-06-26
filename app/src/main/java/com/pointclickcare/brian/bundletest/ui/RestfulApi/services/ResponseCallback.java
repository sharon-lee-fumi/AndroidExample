package com.pointclickcare.brian.bundletest.ui.RestfulApi.services;

import com.pointclickcare.brian.bundletest.model.Response;

interface ResponseCallback<T extends Response> {

    // This is a callback methods when RestApi responds
    void onResponse(T response);
}
