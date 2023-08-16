package com.example.myapplication.ui.gallery;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.BaseViewModel;
import com.example.myapplication.R;

public class GalleryViewModel extends BaseViewModel {

    private MutableLiveData<String> mText;

    public GalleryViewModel() {

        mText = new MutableLiveData<>();

        mText.setValue("This is gallery fragment"); //"This is gallery fragment"
    }

    public LiveData<String> getText() {
        return mText;
    }

}