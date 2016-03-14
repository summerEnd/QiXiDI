package com.sumauto.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sumauto.app.adapter.FloatHeadRecyclerView;
import com.sumauto.app.adapter.base.BaseHolder;
import com.sumauto.app.adapter.base.ListAdapter;
import com.sumauto.app.bean.Bean;
import com.sumauto.app.bean.DemoBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DemoAdapter           adapter;
    private FloatHeadRecyclerView mFloatHeaderView;
    private DemoHolder mViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFloatHeaderView = (FloatHeadRecyclerView) findViewById(R.id.floatHeaderView);
        adapter = new DemoAdapter(this, getBeans());
        mViewHolder = new DemoHolder(LayoutInflater.from(this).inflate(R.layout.head_view, mFloatHeaderView, false));

        mFloatHeaderView.setCallback(new FloatHeadRecyclerView.Callback() {
            @Override
            public void invalidateHeader(RecyclerView.ViewHolder holder, int position) {
                DemoHolder demoHolder = (DemoHolder) holder;
                demoHolder.setData(adapter.getDataList().get(position));
            }
        });

        mFloatHeaderView.getRecyclerView().setAdapter(adapter);
        mFloatHeaderView.setHeaderViewHolder(mViewHolder);
    }

    @NonNull
    private ArrayList<Bean> getBeans() {
        ArrayList<Bean> beans = new ArrayList<>();
        String[] stringArray = getResources().getStringArray(R.array.debug_string_array);
        for (String item : stringArray) {
            String title = RandomUtils.randomArticle(6, 18);
            String content = item + RandomUtils.randomArticle(6, 100);
            beans.add(new DemoBean(title, content));
        }
        return beans;
    }

    public class DemoAdapter extends ListAdapter {

        public DemoAdapter(Context context, List<Bean> data) {
            super(context, data);
        }

        @Override
        public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new DemoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
        }
    }

    private class DemoHolder extends BaseHolder {
        final TextView tv_date;
        final TextView tv_content;

        public DemoHolder(View itemView) {
            super(itemView);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }

        @Override
        protected void onInit(Object data) {
            super.onInit(data);
            DemoBean bean = (DemoBean) data;
            tv_date.setText(bean.getName());
            if (tv_content != null) tv_content.setText(bean.getContent());
        }
    }
}