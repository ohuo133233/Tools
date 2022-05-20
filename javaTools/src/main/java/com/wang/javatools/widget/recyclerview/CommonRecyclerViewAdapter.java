package com.wang.javatools.widget.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * 使用指南
 * 支持功能列表
 * 1.复杂布局
 * 2.右滑动删除
 * 3.拖动排序
 * <p>
 * 注意事项
 * 1.当前BaseRecyclerViewAdapter设置长度最小长度为1
 */
public class CommonRecyclerViewAdapter extends RecyclerView.Adapter<CommonRecyclerViewHolder> {
    private static final String TAG = "CommonRecyclerViewAdapter";

    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;

    private int mSize;

    @NonNull
    private final Context mContext;
    @LayoutRes
    private final int mLayoutId;
    private BaseRecyclerViewAdapterBackCall mBaseRecyclerViewAdapterBackCall;
    private final Build mBuild;
    // RecyclerView的根布局
    private View mRoot;
    // 是否是多布局
    private boolean isMultiLayout;
    private ArrayList<Integer> mLayoutIdList;

    private CommonRecyclerViewAdapter(@NonNull Build build) {
        mBuild = build;
        this.mContext = mBuild.mContext;
        this.mLayoutId = mBuild.mLayoutId;
        this.mSize = mBuild.mSize;

        if (mBuild.mLayoutIdList != null && !mBuild.mLayoutIdList.isEmpty()) {
            isMultiLayout = true;
            this.mLayoutIdList = mBuild.mLayoutIdList;
        }
    }

    @NonNull
    @Override
    public CommonRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (!isMultiLayout) {
            mRoot = LayoutInflater.from(mContext).inflate(this.mLayoutId, parent, false);
            return new CommonRecyclerViewHolder(mRoot);
        } else {
            switch (viewType) {
                case TYPE_ONE:
                    mRoot = LayoutInflater.from(mContext).inflate(mLayoutIdList.get(0), parent, false);
                    return new CommonRecyclerViewHolder(mRoot);
                case TYPE_TWO:
                    mRoot = LayoutInflater.from(mContext).inflate(this.mLayoutIdList.get(1), parent, false);
                    return new CommonRecyclerViewHolder(mRoot);
                case TYPE_THREE:
                    mRoot = LayoutInflater.from(mContext).inflate(this.mLayoutIdList.get(2), parent, false);
                    return new CommonRecyclerViewHolder(mRoot);
                default:
                    mRoot = LayoutInflater.from(mContext).inflate(this.mLayoutIdList.get(0), parent, false);
                    return new CommonRecyclerViewHolder(mRoot);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CommonRecyclerViewHolder holder, int position) {
        if (mBaseRecyclerViewAdapterBackCall != null) {
            mBaseRecyclerViewAdapterBackCall.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ONE;
        }
        if (position == 1) {
            return TYPE_TWO;
        }
        if (position == 2) {
            return TYPE_THREE;
        }
        return TYPE_ONE;
    }


    @Override
    public int getItemCount() {
        Log.d(TAG, "mSize: " + mSize);
        return mSize;
    }

    public void setSize(int mSize) {
        this.mSize = mSize;
    }

    public void setBaseRecyclerViewAdapterBackCall(BaseRecyclerViewAdapterBackCall baseRecyclerViewAdapterBackCall) {
        this.mBaseRecyclerViewAdapterBackCall = baseRecyclerViewAdapterBackCall;
    }

    public interface BaseRecyclerViewAdapterBackCall {
        void onBindViewHolder(@NonNull CommonRecyclerViewHolder holder, int position);
    }


    public static class Build {
        @NonNull
        private Context mContext;
        @LayoutRes
        private int mLayoutId;

        private int mSize = 1;

        // 多布局的布局List
        private ArrayList<Integer> mLayoutIdList;

        public Build setContext(@NonNull Context mContext) {
            this.mContext = mContext;
            return this;
        }


        public Build setLayoutId(@LayoutRes int mLayoutId) {
            this.mLayoutId = mLayoutId;
            return this;
        }

        public Build setLayoutIdList(@LayoutRes int... mLayoutId) {

            this.mLayoutIdList = new ArrayList<>();
            for (int id : mLayoutId) {
                this.mLayoutIdList.add(id);
            }
            return this;
        }

        public Build setSize(int mSize) {
            this.mSize = mSize;
            return this;
        }

        public CommonRecyclerViewAdapter build() {
            return new CommonRecyclerViewAdapter(this);
        }
    }
}