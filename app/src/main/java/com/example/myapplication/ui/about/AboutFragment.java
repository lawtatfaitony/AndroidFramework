package com.example.myapplication.ui.about;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Config;
import com.example.myapplication.R;
import com.example.myapplication.ui.gallery.GalleryFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutFragment extends Fragment {

    private static final String TAG = "AboutFragment";
    private AboutViewModel aboutViewModel;
    private NavController navController ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AboutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.e(TAG, "func::onCreate:  ----  > onCreate ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(TAG, "func::onCreateView:  ----  > inflater = "+ inflater.toString());
        Log.d(TAG, "func::onCreateView:  ----  > inflater = "+ inflater.toString());
        Log.e(TAG, "func::onCreateView:  ----  > inflater = "+ inflater.toString());
        Log.v(TAG, "func::onCreateView:  ----  > inflater = "+ inflater.toString());

        aboutViewModel = ViewModelProviders.of(this).get(AboutViewModel.class);
        View root = inflater.inflate(R.layout.fragment_about, container, false);

        // 獲取應用程式的 Context
        Context context = requireContext().getApplicationContext();
        // 獲取按鈕的引用
        LinearLayout linearLayout = root.findViewById(R.id.linearLayout_about);
        final WebView webView1 = linearLayout.findViewById(R.id.webView1);
        //加載about us url
        webView1.loadUrl(Config.MACAO_ABOUT);
        // 設置 WebView 的佈局參數
        webView1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1));
        //Button btnStartToBuy =  webView1.findViewById(R.id.btn_I_know_start_to_buy);// Button btnStartToBuy = requireView().findViewById(R.id.btn_I_know_start_to_buy); // root.findViewById(R.id.btn_I_know_start_to_buy);
        Button btnStartToBuy = root.findViewById(R.id.btn_I_know_start_to_buy);
        //btnStartToBuy.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,0));

        // 創建一個 LayoutParams 對象，設置為 MATCH_PARENT 寬度和 WRAP_CONTENT 高度
        // 設置按鈕位於父視圖的底部
//        ViewGroup parent = (ViewGroup) btnStartToBuy.getParent();
//
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        params.gravity = Gravity.BOTTOM;
//        btnStartToBuy.setLayoutParams(params);
        //parent.invalidate();

        // 設置按鈕的點擊監聽器
        btnStartToBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext().getApplicationContext(), "AboutFragment btnStartToBuy", Toast.LENGTH_LONG).show();
                // 在這裡添加點擊事件的處理邏輯
                //Method I
                // 獲取 NavController
//                navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
//                // 導航到目標 Fragment
//                navController.navigate(R.id.nav_gallery);
                //Method II
                GalleryFragment galleryFragment = new GalleryFragment();
                // 使用 getChildFragmentManager() 替換當前的 Fragment
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, galleryFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return root;


    }
}