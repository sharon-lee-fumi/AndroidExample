package com.pointclickcare.brian.bundletest.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.pointclickcare.brian.bundletest.R;
import com.pointclickcare.brian.bundletest.ui.bundle.BundleExampleFragment;
import com.pointclickcare.brian.bundletest.databinding.ActivityExampleBinding;
import com.pointclickcare.brian.bundletest.ui.recyclerview.RecyclerViewExampleFragment;
import com.pointclickcare.brian.bundletest.util.Extra;

public class ExampleActivity extends AppCompatActivity {
    ActivityExampleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_example);

        binding.viewPager.setAdapter(new TabFragmentAdapter(getSupportFragmentManager()));
        binding.tab.setupWithViewPager(binding.viewPager);
    }

    class TabFragmentAdapter extends FragmentPagerAdapter {
        public TabFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    String message = "";
                    Intent intent = getIntent();
                    if (intent != null) {
                        message = intent.getStringExtra(Extra.DATA);
                    }
                    return BundleExampleFragment.newInstance(message);
                case 1:
                    return RecyclerViewExampleFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            try {
                return getResources().getStringArray(R.array.tab_title)[position];
            } catch (IndexOutOfBoundsException e) {
                return "";
            }
        }
    }
}
