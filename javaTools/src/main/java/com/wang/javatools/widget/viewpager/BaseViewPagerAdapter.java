package com.wang.javatools.widget.viewpager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BaseViewPagerAdapter<T> extends RecyclerView.Adapter<BaseViewPagerViewHolder> {
    private static final String TAG = "BaseRecyclerViewAdapter";
    @NonNull
    private Context mContext;
    @LayoutRes
    private int mLayoutId;
    @NonNull
    private List<T> mDataList;

    private RecyclerView.ViewHolder mHolder;
    private BaseBaseViewPagerAdapterBackCall mBaseBaseViewPagerAdapterBackCall;
    private Build<T> mBuild;

    public BaseViewPagerAdapter(@NonNull Build<T> build) {
        mBuild = build;
        build();
    }

    @NonNull
    @Override
    public BaseViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(mContext).inflate(this.mLayoutId, parent, false);
        return new BaseViewPagerViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewPagerViewHolder holder, int position) {
        mHolder = holder;
        if (mBaseBaseViewPagerAdapterBackCall != null) {
            mBaseBaseViewPagerAdapterBackCall.onBindViewHolder(holder, position);
        }
    }


    @Override
    public int getItemCount() {
        Log.d(TAG, "mDataList size: " + mDataList.size());
        return Math.max(mDataList.size(), 1);
    }

    private void build() {
        this.mContext = mBuild.mContext;
        this.mLayoutId = mBuild.mLayoutId;
        this.mDataList = mBuild.mDataList;
    }


    public void setBaseViewPagerViewAdapterBackCall(BaseBaseViewPagerAdapterBackCall baseBaseViewPagerAdapterBackCall) {
        this.mBaseBaseViewPagerAdapterBackCall = baseBaseViewPagerAdapterBackCall;
    }

    public interface BaseBaseViewPagerAdapterBackCall {
        void onBindViewHolder(@NonNull BaseViewPagerViewHolder holder, int position);
    }


    public static class Build<T> {
        @NonNull
        private Context mContext;
        @LayoutRes
        private int mLayoutId;
        @NonNull
        private List<T> mDataList;


        public Build<T> setContext(@NonNull Context mContext) {
            this.mContext = mContext;
            return this;
        }

        public Build<T> setLayoutId(@LayoutRes int mLayoutId) {
            this.mLayoutId = mLayoutId;
            return this;
        }

        public Build<T> setDataList(@NonNull List<T> mDataList) {
            this.mDataList = mDataList;
            return this;
        }

        public BaseViewPagerAdapter<T> build() {
            return new BaseViewPagerAdapter<>(this);
        }
    }
}