package com.example.myapplication.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private HomeViewModel homeViewModel;
    private EditText tv_time;
    private EditText edit_date;
    private EditText row_date;
    private EditText row_time;

    private EditText mEditTextLocation;
    private RelativeLayout rLayout;
    private MapView mapView;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        initview(root);  //date-view

        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });



        rLayout  = (RelativeLayout)root.findViewById(R.id.rl_edit_location);
        mEditTextLocation = root.findViewById(R.id.edit_location);
        mEditTextLocation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    // 獲取輸入的網址
                    String url = mEditTextLocation.getText().toString();
                    // 跳轉到網頁
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        mapView = (MapView) root.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return root;
    }

    private void initview(View root) {
        //-------------------------------------------------------------------------------------

        RelativeLayout rl_date = root.findViewById(R.id.rl_date);
        edit_date = rl_date.findViewById(R.id.edit_date);

        RelativeLayout rl_time = root.findViewById(R.id.rl_time);
        tv_time = root.findViewById(R.id.tv_time);

        LinearLayout ll_date_and_time = root.findViewById(R.id.ll_date_and_time);
        RelativeLayout rl_date_and_time = ll_date_and_time.findViewById(R.id.rl_date_and_time);
        RelativeLayout rl_date_and_time2 = ll_date_and_time.findViewById(R.id.rl_date_and_time2);
        //row_date row_time
        row_date = rl_date_and_time.findViewById(R.id.row_date);
        row_time = rl_date_and_time2.findViewById(R.id.row_time);
        //-------------------------------------------------------------------------------------



        tv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoftKeyboardUtils.hideSoftKeyboard(getActivity());
                selectStartTime();
            }
        });

        row_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoftKeyboardUtils.hideSoftKeyboard(getActivity());
                selectStartTime();
            }
        });

        row_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoftKeyboardUtils.hideSoftKeyboard(getActivity());
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
        Date futureDate = DateUtil.getFuturDate(30);
        Calendar cal = Calendar.getInstance();
        cal.setTime(futureDate);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        Calendar selectedDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, day);
        TimePickerView pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SoftKeyboardUtils.hideSoftKeyboard(getActivity());
                tv_time.setText(DateUtil.getTime(date, "yyyy-MM-dd hh:mm:ss"));
            }
        })
        .setDate(selectedDate)
        .setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
        .build();
        pvTime.show();

        //row_date
        TimePickerView pv_row_date = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SoftKeyboardUtils.hideSoftKeyboard(getActivity());
                row_date.setText(DateUtil.getTime(date, "yyyy-MM-dd"));
            }
        })
        .setDate(selectedDate)
        .setType(new boolean[]{true, true, true, false, false, false})// only yyyy-MM-dd
        .build();
        pv_row_date.show();

        //row_time
        TimePickerView pv_row_time = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SoftKeyboardUtils.hideSoftKeyboard(getActivity());
                row_time.setText(DateUtil.getTime(date, "HH:mm:ss"));
            }
        })
        .setDate(selectedDate)
        .setType(new boolean[]{false, false, false, true, true, true})// only yyyy-MM-dd
        .build();
        pv_row_time.show();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(getContext().getApplicationContext(), "GoogleMap onMapReady", Toast.LENGTH_LONG).show();
    }
}