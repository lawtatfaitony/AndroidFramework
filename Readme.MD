https://blog.csdn.net/albb_/article/details/89704277

#### Gradle问题: AAPT2 error和"aapt.exe'' finished with non-zero exit value 1或者AAPT2与Robelectric不兼容

# 詳盡描述如何解決高版本Graddle/SDK所出現的 AATP2 ERROR問題



***目標：基礎框架，功能包括NAVIGATOR跳轉；多語言；登錄***

```
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
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_about,R.id.nav_system_setting)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        加進去導致導航菜單無效
        //        navigationView.setNavigationItemSelectedListener(
        //                new NavigationView.OnNavigationItemSelectedListener() {
        //                    @Override
        //                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //                        switch (item.getItemId()) {
        //                            case R.id.nav_system_setting:
        //                                // 處理系統設置菜單項目的操作
        //                                return true;
        //                            case R.id.menu_item_green:
        //                                // 處理用戶設置菜單項目的操作
        //                                //Toast.makeText(this, "Click the green menu item", Toast.LENGTH_SHORT).show();
        //                                //TEST 2023-8-29 加載 FragmentGreenPage
        //                                FragmentManager fragmentManager = getSupportFragmentManager();
        //                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //                                fragmentTransaction.replace(R.id.drawer_layout, new GreenPageFragment());
        //                                fragmentTransaction.commit();
        //                                return true;
        //                            default:
        //                                return false;
        //                        }
        //                    }
        //                });
    }

```

https://blog.csdn.net/weixin_41659488/article/details/84450377  android:获取onCreateContextMenu（上下文菜单） 选项的方法

另一種方式是偵聽事件：

先繼承偵聽類：public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener

然後重寫：

```
@Override
public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
    switch (menuItem.getItemId()) {
        case R.id.nav_system_setting:
            // 處理系統設置菜單項目的操作
            return true;
        case R.id.nav_about:
            // 處理用戶設置菜單項目的操作
            return true;
        default:
            return super.onOptionsItemSelected(menuItem);
    }
}
```

跳轉的最簡易方法：

```scala
public class MapsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        FragmentManager myFragmentManager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) myFragmentManager.findFragmentById(R.id.map);
        GoogleMap mMap = mapFragment.getMap();

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-35, 152);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker near Sidney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
```

https://stackoverflow.com/questions/72898494/cordova-build-failed-with-aapt2-unknown-chunk-type-200

upgrade gradle plugin. I upgrade compileSdkVersion to 34 , And show those error, So I upgrade gradle plugin to 7.3.1, the error resolved

# **Android圖標資源**

https://fonts.google.com/icons?selected=Material+Icons:class:&icon.platform=android

Google MapView 的用法：

第一步：

dependencies {
    implementation 'com.google.android.gms:play-services-maps:17.0.0'
}

第二步：

<com.google.android.gms.maps.MapView
    android:id="@+id/mapView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />

第三步：

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapView mMapView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maps);
    
            mMapView = (MapView) findViewById(R.id.mapView);
            mMapView.onCreate(savedInstanceState);
            mMapView.getMapAsync(this);
        }
    
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            // 在這裡添加您的地圖代碼
        }
    
        @Override
        public void onResume() {
            super.onResume();
            mMapView.onResume();
        }
    
        @Override
        public void onPause() {
            super.onPause();
            mMapView.onPause();
        }
    
        @Override
        public void onDestroy() {
            super.onDestroy();
            mMapView.onDestroy();
        }
    
        @Override
        public void onLowMemory() {
            super.onLowMemory();
            mMapView.onLowMemory();
        }
    }
### EditText 接收來自web的文字

`<Ed`itTextandroid:id="@+id/editText"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:hint="請輸入網址"
android:inputType="textWebEditText" />`

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        mEditText = findViewById(R.id.editText);
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    // 獲取輸入的網址
                    String url = mEditText.getText().toString();
                    // 跳轉到網頁
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
      }
    }
