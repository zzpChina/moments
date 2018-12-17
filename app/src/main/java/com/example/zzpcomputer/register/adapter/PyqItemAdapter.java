package com.example.zzpcomputer.register.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zzpcomputer.register.R;
import com.example.zzpcomputer.register.Thread.HeadImgHttpThread;
import com.example.zzpcomputer.register.model.PyqItem;

import java.util.List;

/**
 * 适配器
 */
@SuppressWarnings("all")
public class PyqItemAdapter extends ArrayAdapter {
    private int resourceId;

    public PyqItemAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        PyqItem pyqItem = (PyqItem) getItem(position);
        LayoutView layoutView = new LayoutView();
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            layoutView.imageView = view.findViewById(R.id.headImg);
            layoutView.textView = view.findViewById(R.id.unamePyq);
            layoutView.textView2 = view.findViewById(R.id.mood);
            layoutView.imageView1 = view.findViewById(R.id.moodImage);
            view.setTag(layoutView);
        } else {
            view = convertView;
            layoutView = (LayoutView) view.getTag();
        }
        //获取头像
        HeadImgHttpThread headImgHttpThread = new HeadImgHttpThread(pyqItem.getHeadImg());
        headImgHttpThread.start();
        try {
            headImgHttpThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //获取动态选择的图片
        HeadImgHttpThread moodImg = new HeadImgHttpThread(pyqItem.getMoodImg());
        moodImg.start();
        try {
            moodImg.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        layoutView.imageView.setImageBitmap(headImgHttpThread.getResultBitmap());
        layoutView.imageView1.setTag(pyqItem.getMoodImg());
        layoutView.imageView1.setImageBitmap(moodImg.getResultBitmap());
        layoutView.textView.setText(pyqItem.getUname());
        layoutView.textView2.setText(pyqItem.getMood());
        return view;
    }

    /**
     * 布局类
     */
    private class LayoutView {
        private ImageView imageView;
        private TextView textView;
        private TextView textView2;
        private ImageView imageView1;
    }
}
