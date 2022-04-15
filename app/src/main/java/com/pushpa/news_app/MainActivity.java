package com.pushpa.news_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager view_pager;
    private TabLayout tab_layout;
    private BottomNavigationView btmNav;
    MainActivity.SectionsPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        btmNav = findViewById(R.id.btnNav);
        toolbar.setNavigationIcon(R.drawable.ic_menu__white_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mero News");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        view_pager = findViewById(R.id.view_pager);
        setupViewPager(view_pager);

        tab_layout = findViewById(R.id.tab_layout);
        tab_layout.setupWithViewPager(view_pager);



        btmNav.setOnNavigationItemSelectedListener(new
                BottomNavigationView.OnNavigationItemSelectedListener()  {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                    if(item.getTitle().equals("Recents")){
                       startActivity(new Intent(MainActivity.this,RecentActivity.class));

                    }
                    if(item.getTitle().equals("Favorites")){
                        startActivity(new Intent(MainActivity.this, FavoriteActivity.class));

                    }
                    if(item.getTitle().equals("Nearby")){
                        startActivity(new Intent(MainActivity.this,NearbyActivity.class));
                    }
                    return true;
                    }
                });

    }



    private void setupViewPager(ViewPager viewPager) {
        MainActivity.SectionsPagerAdapter adapter = new MainActivity.SectionsPagerAdapter(getSupportFragmentManager());
        this.adapter =  new MainActivity.SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(HomeFragment.newInstance(), "HOME");
        adapter.addFragment(TravelFragment.newInstance(), "HEALTH");
        adapter.addFragment(EntertainmentFragment.newInstance(), "ENTERTAINMENT");
        adapter.addFragment(TechFragment.newInstance(), "TECH");
        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("TAG", "onOptionsItemSelected: "+item.getTitle()+"Yo ho so" );


        if (item.getItemId() == android.R.id.home) {

            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);}

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
