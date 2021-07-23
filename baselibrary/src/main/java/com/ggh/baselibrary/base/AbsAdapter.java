package com.ggh.baselibrary.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：AbsAdapter
 * 创建时间：2020/6/30
 * 功能描述： 添加一个描述
 */
public abstract class AbsAdapter<T, VDB extends ViewDataBinding> extends RecyclerView.Adapter {
    protected Context mContext;
    protected int mPos;//父级列表当前选择位置
    protected List<T> mList = new ArrayList<>();
    private int layoutId;
    private VDB mBinding;
    private OnItemClickListener<T> onItemClickListener;
    protected int viewType;

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<T> getList() {
        return mList;
    }

    /**
     * 添加同步锁  确保这个流程能够执行完    主要解决   ConcurrentModificationException：同时修改异常
     *  在数据外层再添加一个数据壳
     * @param list
     */
    public synchronized void setList(List<T> list) {
        if (list == null)return;

        List<T> tList = new ArrayList<>();
        for (T t:list){
            tList.add(t);
        }

        if (mList.size()>0){
            for (T t: tList){
                mList.add(t);
            }

        }else {
            mList = list;
        }

        notifyDataSetChanged();
    }

    public void remove() {
        mList.clear();
        notifyDataSetChanged();
    }

    public void removeItem(int mPosition) {
        mList.remove(mPosition);
        notifyItemChanged(mPosition);
        notifyDataSetChanged();//为了数据同步防止错位

    }


    /**
     * 设置当前点击的位置
     *
     * @param pos
     * @return
     */
    public void setCurrentPos(int pos) {
        mPos = pos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }

        this.viewType = viewType;

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(this.mContext), getLayoutId(), parent, false);

        return new AbsViewHolder(mBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        mBinding = DataBindingUtil.getBinding(holder.itemView);
        //返回的是当前在适配器中条目的真实序号   防止删除item出现数组越界
        int po = holder.getAdapterPosition();
        this.onBindItem(mBinding, this.mList.get(po),po);
        if (mBinding != null) {
            mBinding.executePendingBindings();
        }

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClickItem(this.mList.get(po), po);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected abstract int getLayoutId();

    protected abstract void onBindItem(VDB binding, T item,int mPosition);

    public static class AbsViewHolder extends RecyclerView.ViewHolder {
        AbsViewHolder(View itemView) {
            super(itemView);
        }
    }

}
