package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.language.LanguageUtils;
import com.example.myapplication.ui.gallery.GreenPageFragment;
import com.example.myapplication.ui.home.ui.login.LoginFragment;
import com.example.myapplication.ui.setting.SettingFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_about,R.id.nav_system_setting,R.id.nav_fragment_green_page,R.id.nav_fragment_blue_page,R.id.nav_maps_fragment)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    /**
     * right-top-menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu); //right-top-menu
        return true;
    }

    /**
     * 重写上下文菜单的创建方法
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflator = new MenuInflater(this);
        inflator.inflate(R.menu.activity_main_and_sub_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 開始 Fragment 事務
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (item.getItemId()) {
            case R.id.action_login:
                //跳轉到login
//                LoginFragment myLoginFragment = new LoginFragment();
////                // 將 Fragment 添加到 Activity 中
////                // 請注意，R.id.fragment_container（R.id.drawer_layout） 是指定要添加 Fragment 的佈局的 ID。請確保您的 Activity 佈局中包含具有此 ID 的佈局。
////                fragmentTransaction.add(R.id.drawer_layout, myLoginFragment);
////                fragmentTransaction.commit();
                Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.nav_home_login);

                break;
            case R.id.action_settings:
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
//                //跳转到 SettingFragment (这个语法是导致View重叠在一起)
//                // 創建 Fragment 實例
//                SettingFragment mySettingFragment = new SettingFragment();
//                // 將 Fragment 添加到 Activity 中
//                fragmentTransaction.add(R.id.drawer_layout, mySettingFragment);
//                fragmentTransaction.commit();

                navController.navigate(R.id.nav_system_setting);
                break;
            //選擇一種語
            case R.id.lang_settings:
                Toast.makeText(this, "language setting", Toast.LENGTH_SHORT).show();
                // 在這裡處理按鈕點擊事件
                final String[] cities = {getString(R.string.lan_chinese), getString(R.string.lan_en),getString(R.string.lan_zh_HK),getString(R.string.Follow_the_system)};
                final String[] locals = LanguageUtils.locals; //{"zh-CN", "en","zh-HK","111"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle(R.string.select_language);  //R.string.select_language = "選擇一種語言"
                builder.setItems(cities, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String language= LanguageUtils.locals[which];
                        Log.e(TAG, "onClick: language   --- >"+language  );
                        Store.setLanguageLocal(MainActivity.this, language);
                        Intent intent =new Intent(Config.ACTION); //Config.ACTION ="com.example.myapplication.action"  //LanguageUtils.initLanguage(MainActivity.this)
                        intent.putExtra("msg", "EVENT_REFRESH_LANGUAGE");
                        sendBroadcast(intent);
                        LanguageUtils.initLanguage(MainActivity.this);
                    }
                }).setIcon(R.mipmap.ic_launcher_logo_round);
                builder.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}