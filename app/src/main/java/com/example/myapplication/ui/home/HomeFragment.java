package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.myapplication.R;
import com.example.myapplication.utils.DateUtil;
import com.example.myapplication.utils.SoftKeyboardUtils;
import java.util.Calendar;
import java.util.Date;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView tv_time;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private void initview(View root) {
        RelativeLayout rl_time = root.findViewById(R.id.rl_time);
        tv_time = root.findViewById(R.id.tv_time);
        rl_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectStartTime();
            }
        });
        tv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectStartTime();
            }
        });
    }

    /**
     * 时间选择器
     */
    private void selectStartTime() {
        if (SoftKeyboardUtils.isSoftShowing(getActivity())) {
            SoftKeyboardUtils.hideSoftKeyboard(getActivity());
        }
        Calendar selectedDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.set(2200, 12, 31);
        TimePickerView pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tv_time.setText(DateUtil.getTime(date, "yyyy年MM月dd日hhxiaomm-ss"));
            }
        })
        .setDate(selectedDate)
        .setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
        .build();
        pvTime.show();
    }
}