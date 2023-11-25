package com.example.aplikasipergipergi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.aplikasipergipergi.databinding.ActivityMainBinding;
import com.example.aplikasipergipergi.fragments.FirstFragment;
import com.example.aplikasipergipergi.fragments.SecondFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        replaceFragment(new FirstFragment());

        activityMainBinding.bottomNavBar.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new FirstFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new SecondFragment());
                    break;
            }

            return true;
        });
    }

    void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main, fragment);
        fragmentTransaction.commit();
    }
}