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