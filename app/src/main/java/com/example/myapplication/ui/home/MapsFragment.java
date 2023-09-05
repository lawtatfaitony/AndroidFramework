package com.example.myapplication.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.myapplication.R;

public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            // 創建一個 LatLng 對象，表示標記的位置
            LatLng sydney = new LatLng(-35, 152);
            // 創建一個 MarkerOptions 對象，表示標記的屬性
            MarkerOptions markerSydneyOptions = new MarkerOptions()
                    .position(sydney)
                    .title("Sydney");
            // 在地圖上添加標記
            googleMap.addMarker(markerSydneyOptions);
            // 將地圖移動到標記的位置
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 創建一個 LatLng 對象，表示標記的位置
            LatLng hongKong = new LatLng(22.2964, 114.1747);
            // 創建一個 MarkerOptions 對象，表示標記的屬性
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(hongKong)
                    .title("Hong Kong Tsim Sha Tsui");
            // 在地圖上添加標記
            googleMap.addMarker(markerOptions);
            // 將地圖移動到標記的位置
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(hongKong));

            // 將地圖縮放到指定的級別
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(17));
            // 獲取當前的縮放級別
            float zoomLevel = googleMap.getCameraPosition().zoom;
            // 將地圖放大三級
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(zoomLevel + 13));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}