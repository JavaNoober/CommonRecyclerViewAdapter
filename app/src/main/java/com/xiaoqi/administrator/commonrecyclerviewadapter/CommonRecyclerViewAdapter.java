package com.xiaoqi.administrator.commonrecyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public abstract class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder>{

	public interface OnItemClickListener {
		void onItemClick (View view, int position);
	}

	public interface OnItemLongClickListener {
		void onItemLongClick (View view, int position);
	}

	private LayoutInflater mInflater;
	protected Context mContext;
	private List<T> mData;
	private int mItemLayoutId;
	private int[] mItemLayoutIds = new int[]{};
	private boolean hasDifferentLayouts = false;
	private OnItemClickListener mOnItemClickListener;
	private OnItemLongClickListener mOnItemLongClickListener;

	public CommonRecyclerViewAdapter(Context context, List<T> data, int itemLayoutId) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mItemLayoutId = itemLayoutId;
		this.mData = data;
	}

	public CommonRecyclerViewAdapter(Context context, List<T> data, int... itemLayoutIds) {
		hasDifferentLayouts = true;
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mItemLayoutIds = itemLayoutIds;
		this.mData = data;
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener){
		this.mOnItemClickListener = onItemClickListener;
	}

	public void setmOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
		this.mOnItemLongClickListener = onItemLongClickListener;
	}

	@Override
	public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if(!hasDifferentLayouts){
			return new RecyclerViewHolder(mInflater.inflate(mItemLayoutId, parent, false), mOnItemClickListener,
					mOnItemLongClickListener);
		}else {
			return new RecyclerViewHolder(mInflater.inflate(mItemLayoutIds[viewType], parent, false), mOnItemClickListener,
					mOnItemLongClickListener);
		}

	}

	@Override
	public void onBindViewHolder(RecyclerViewHolder holder, int position) {
		convert(holder, mData.get(position), position);
	}

	@Override
	public int getItemCount(){
		return mData.size();
	}


	//return的值的顺序必须为mItemLayoutIds中layout的顺序，即
	//mItemLayoutIds中layout1必须renturn 0;layout2 必须renturn 1;....
	@Override
	public int getItemViewType(int position) {
		return super.getItemViewType(position);
	}

	public abstract void convert(RecyclerViewHolder holder, T item, int position);
}
