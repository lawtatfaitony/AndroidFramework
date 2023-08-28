package com.example.myapplication.ui.setting;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        //Pop up cloud url input
        Button button = root.findViewById(R.id.btn_save_cloud_url);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog(getActivity());
            }
        });

        return root;
    }
    public void showInputDialog(Context context) {
        String input_cloud_url_tips = getString(R.string.input_cloud_url_tips);
        String comfirm1 = getString(R.string.comfirm1);
        String cancel1  = getString(R.string.cancel1);

        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);

        final EditText input1 = new EditText(context);
       // input1.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_edit_text_bg));
        input1.setWidth(90);
        input1.setHeight(90);
        final EditText input2 = new EditText(context);
        //input2.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_edit_text_bg));
        input2.setWidth(90);
        input2.setHeight(18);
        final EditText input3 = new EditText(context);
       // input3.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_edit_text_bg));
        input3.setWidth(90);
        input3.setHeight(18);
        container.addView(input1);
        container.addView(input2);
        container.addView(input3);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(input_cloud_url_tips)
                .setView(container)
                .setPositiveButton(comfirm1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userInput1 = input1.getText().toString();
                        String userInput2 = input2.getText().toString();
                        String userInput3 = input3.getText().toString();
                        // 在這裡處理用戶輸入的值
                        String messages =  userInput1 + "://" + userInput2 + ":" + userInput3;
                        Log.e(TAG, "func::showInputDialog:  ----  > input messages = "+ messages);
                    }
                })
                .setNegativeButton(cancel1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }
}