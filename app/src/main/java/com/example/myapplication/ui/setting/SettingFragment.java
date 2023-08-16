package com.example.myapplication.ui.setting;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.myapplication.R;

public class SettingFragment extends Fragment {
    private static final String TAG = "SettingFragment";
    private SettingModel settingModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Log.e(TAG, "onCreateView: create   --- > Create");

        settingModel =
                ViewModelProviders.of(this).get(SettingModel.class);

        View root = inflater.inflate(R.layout.fragment_setting, container, false);

        final TextView textView = root.findViewById(R.id.text_setting);

        settingModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}