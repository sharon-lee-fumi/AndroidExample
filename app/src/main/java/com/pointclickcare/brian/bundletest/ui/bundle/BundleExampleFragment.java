package com.pointclickcare.brian.bundletest.ui.bundle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pointclickcare.brian.bundletest.R;
import com.pointclickcare.brian.bundletest.databinding.FragmentBundleBinding;
import com.pointclickcare.brian.bundletest.util.Extras;

import java.util.ArrayList;
import java.util.List;

public class BundleExampleFragment extends Fragment {
    private FragmentBundleBinding binding;
    private String message;
    private List<ClickEventListener> observers = new ArrayList<>();

    public BundleExampleFragment() {
    }

    public BundleExampleFragment(String message) {
        this.message = message;
    }

    public static BundleExampleFragment newInstance(String message) {
        BundleExampleFragment fragment = new BundleExampleFragment();
        Bundle args = new Bundle();
        args.putString(Extras.DATA, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.message = getArguments().getString(Extras.DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBundleBinding.inflate(getLayoutInflater());
        binding.setMessage(message);

        for (int i = 0; i < 5; i++) {
            TextView textView = new TextView(getContext());
            textView.setText(getString(R.string.text_click));
            textView.setOnClickListener(this::notifyClickEvent);
            textView.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);

            observers.add(view ->
                    textView.setTextColor(getResources().getColor(view != textView ?
                            android.R.color.primary_text_light : R.color.colorAccent, null))
            );
            binding.mainContainer.addView(textView);
        }

        return binding.getRoot();
    }

    private void notifyClickEvent(View view) {
        for (ClickEventListener listener : observers) {
            listener.onClicked(view);
        }
    }

    interface ClickEventListener {
        void onClicked(View view);
    }
}
