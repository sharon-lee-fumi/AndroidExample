package com.pointclickcare.brian.bundletest.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pointclickcare.brian.bundletest.R;
import com.pointclickcare.brian.bundletest.databinding.ActivityMainBinding;
import com.pointclickcare.brian.bundletest.model.Data;
import com.pointclickcare.brian.bundletest.util.Extra;

public class MainActivity extends AppCompatActivity {
    Data data = new Data();
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setData(data);
        binding.contentMain.buttonLaunch.setOnClickListener(
                view -> launchActivity(ExampleActivity.class, data.content.get())
        );
        data.content.set("initial value");
    }

    private void launchActivity(Class<?> clz, String message) {
        Intent intent = new Intent(this, clz);
        intent.putExtra(Extra.DATA, message);
        startActivity(intent);
    }
}
