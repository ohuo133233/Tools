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
import java.util.List;

/**
 * 使用指南
 * 支持功能列表
 * 1.复杂布局
 * 2.右滑动删除
 * 3.拖动排序
 * <p>
 * 注意事项
 * 1.当前BaseRecyclerViewAdapter设置长度最小长度为1
 *
 * @param <T> 类型
 */
public class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    private static final String TAG = "BaseRecyclerViewAdapter";

    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;

    @NonNull
    private final Context mContext;
    @LayoutRes
    private final int mLayoutId;
    @NonNull
    private final List<T> mDataList;

    private RecyclerView.ViewHolder mHolder;
    private BaseRecyclerViewAdapterBackCall mBaseRecyclerViewAdapterBackCall;
    private final Build<T> mBuild;
    // RecyclerView的根布局
    private View mRoot;
    // 是否是多布局
    private boolean isMultiLayout;
    private ArrayList<Integer> mLayoutIdList;

    private BaseRecyclerViewAdapter(@NonNull Build<T> build) {
        mBuild = build;
        this.mContext = mBuild.mContext;
        this.mLayoutId = mBuild.mLayoutId;
        this.mDataList = mBuild.mDataList;

        if (mBuild.mLayoutIdList != null && !mBuild.mLayoutIdList.isEmpty()) {
            isMultiLayout = true;
            this.mLayoutIdList = mBuild.mLayoutIdList;
        }
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (!isMultiLayout) {
            mRoot = LayoutInflater.from(mContext).inflate(this.mLayoutId, parent, false);
            return new BaseRecyclerViewHolder(mRoot);
        } else {
            switch (viewType) {
                case TYPE_ONE:
                    mRoot = LayoutInflater.from(mContext).inflate(mLayoutIdList.get(0), parent, false);
                    return new BaseRecyclerViewHolder(mRoot);
                case TYPE_TWO:
                    mRoot = LayoutInflater.from(mContext).inflate(this.mLayoutIdList.get(1), parent, false);
                    return new BaseRecyclerViewHolder(mRoot);
                case TYPE_THREE:
                    mRoot = LayoutInflater.from(mContext).inflate(this.mLayoutIdList.get(2), parent, false);
                    return new BaseRecyclerViewHolder(mRoot);
                default:
                    mRoot = LayoutInflater.from(mContext).inflate(this.mLayoutIdList.get(0), parent, false);
                    return new BaseRecyclerViewHolder(mRoot);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder holder, int position) {
        mHolder = holder;
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
        Log.d(TAG, "mDataList size: " + mDataList.size());
        return Math.max(mDataList.size(), 1);
    }


    public void setBaseRecyclerViewAdapterBackCall(BaseRecyclerViewAdapterBackCall baseRecyclerViewAdapterBackCall) {
        this.mBaseRecyclerViewAdapterBackCall = baseRecyclerViewAdapterBackCall;
    }

    public interface BaseRecyclerViewAdapterBackCall {
        void onBindViewHolder(@NonNull BaseRecyclerViewHolder holder, int position);
    }


    public static class Build<T> {
        @NonNull
        private Context mContext;
        @LayoutRes
        private int mLayoutId;
        // 数据
        @NonNull
        private List<T> mDataList;
        // 多布局的布局List
        private ArrayList<Integer> mLayoutIdList;

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

        public Build setLayoutIdList(@LayoutRes int... mLayoutId) {

            this.mLayoutIdList = new ArrayList<>();
            for (int id : mLayoutId) {
                this.mLayoutIdList.add(id);
            }
            return this;
        }

        public BaseRecyclerViewAdapter<T> build() {
            return new BaseRecyclerViewAdapter<>(this);
        }
    }
}