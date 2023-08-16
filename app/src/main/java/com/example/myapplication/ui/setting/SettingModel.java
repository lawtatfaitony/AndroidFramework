package com.example.myapplication.ui.setting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.language.LanguageUtils;
import com.example.myapplication.R;

public class SettingModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SettingModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is SettingModel fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}