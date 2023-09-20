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
