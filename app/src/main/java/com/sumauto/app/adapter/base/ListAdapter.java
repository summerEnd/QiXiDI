package com.sumauto.app.adapter.base;

import android.content.Context;

import com.sumauto.app.bean.Bean;
import com.sumauto.app.bean.DemoBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lincoln on 2016/3/14.
 * 基于List的adapter
 */
public abstract class ListAdapter extends BaseAdapter {
    private List<? super Bean> data;

    public ListAdapter(Context context) {
        super(context);
        data = new ArrayList<>();
    }

    public ListAdapter(Context context, List<Bean> data) {
        super(context);
        this.data = data;
    }

//    public ListAdapter(Context context, Object... objects) {
//        super(context);
//        data = new ArrayList<>();
//        if (objects instanceof Bean[]) {
//            Collections.addAll(data, (Bean[]) objects);
//        }
//        else {
//            for (Object object : objects) {
//                data.add(new DemoBean(object.toString()));
//            }
//        }
//    }


    public List<? super Bean> getDataList() {
        return data;
    }

    public void setDataList(List<Bean> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.setData(getDataList().get(position));
    }
}
