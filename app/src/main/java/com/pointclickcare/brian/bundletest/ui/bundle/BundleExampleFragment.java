package com.pointclickcare.brian.bundletest.ui.bundle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pointclickcare.brian.bundletest.util.Extra;
import com.pointclickcare.brian.bundletest.databinding.FragmentBundleBinding;


public class BundleExampleFragment extends Fragment {
    private FragmentBundleBinding binding;
    private String message;

    public BundleExampleFragment() {
    }

    public BundleExampleFragment(String message) {
        this.message = message;
    }

    public static BundleExampleFragment newInstance(String message) {
        BundleExampleFragment fragment = new BundleExampleFragment();
        Bundle args = new Bundle();
        args.putString(Extra.DATA, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.message = getArguments().getString(Extra.DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBundleBinding.inflate(getLayoutInflater());
        binding.setMessage(message);
        return binding.getRoot();
    }
}
