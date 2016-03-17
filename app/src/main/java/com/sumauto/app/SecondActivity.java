package com.sumauto.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.sumauto.common.util.ActivityDismissUtils;

public class SecondActivity extends AppCompatActivity {


    private ActivityDismissUtils.SwipeDismiss swipeDismiss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        swipeDismiss = ActivityDismissUtils.getSwipeDismiss(this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new DemoFragment();
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        swipeDismiss.process(ev);
        return super.dispatchTouchEvent(ev);
    }
}
