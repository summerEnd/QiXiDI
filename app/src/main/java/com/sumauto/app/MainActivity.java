package com.sumauto.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sumauto.app.dummy.DummyContent;
import com.sumauto.widget.CheckableLinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener{

    CheckableLinearLayout checkedTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void onTabClick(View v) {
        int id = v.getId();
        if (id == R.id.tab_main_publish) {
            return;
        }
        if (v == checkedTab) return;
        FragmentManager fragmentManager = getSupportFragmentManager();
        String tag = String.valueOf(id);
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        FragmentTransaction ft = fragmentManager.beginTransaction();

        if (fragment == null) {
            switch (id) {
                case R.id.tab_main_home:
                    fragment = new HomeFragment();
                    break;
                case R.id.tab_main_search:
                    fragment = new SearchFragment();
                    break;
                case R.id.tab_main_newsFeed:
                    fragment = new NewsFeedFragment();
                    break;
                case R.id.tab_main_mine:
                    fragment = new MineFragment();
                    break;
            }
            if (fragment != null) ft.add(R.id.layout_fragment_container, fragment, tag);
        }
        else {
            ft.show(fragment);
        }

        CheckableLinearLayout tab = (CheckableLinearLayout) v;
        tab.setChecked(true);
        if (checkedTab != null) {
            Fragment oldFragment=fragmentManager.findFragmentByTag(String.valueOf(checkedTab.getId()));
            if (oldFragment!=null){
                ft.hide(oldFragment);
            }
            checkedTab.setChecked(false);
        }
        ft.commit();
        checkedTab = tab;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}