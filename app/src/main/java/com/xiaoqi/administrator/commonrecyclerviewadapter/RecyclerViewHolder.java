package com.xiaoqi.administrator.commonrecyclerviewadapter;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

	private final SparseArray<View> mViews;
	private View mItemView;

	private CommonRecyclerViewAdapter.OnItemClickListener mOnItemClickListener;
	private CommonRecyclerViewAdapter.OnItemLongClickListener mOnItemLongClickListener;

	public RecyclerViewHolder(View itemView, CommonRecyclerViewAdapter.OnItemClickListener onItemClickListener,
	                          CommonRecyclerViewAdapter.OnItemLongClickListener onItemLongClickListener){
		super(itemView);
		this.mItemView = itemView;
		this.mOnItemClickListener = onItemClickListener;
		this.mOnItemLongClickListener = onItemLongClickListener;
		this.mViews = new SparseArray<>();
	}

	@Override
	public void onClick(View v) {
		if(mOnItemClickListener != null){
			mOnItemClickListener.onItemClick(v,getLayoutPosition());
		}
	}

	@Override
	public boolean onLongClick(View v) {
		if(mOnItemLongClickListener != null){
			mOnItemLongClickListener.onItemLongClick(v,getLayoutPosition());
		}
		return true;
	}

	private <T extends View> T getView(int id){
		View item = mViews.get(id);
		if(item == null){
			item = mItemView.findViewById(id);
			mViews.put(id, item);
		}
		return (T) item;
	}

	public RecyclerViewHolder setText(int id, CharSequence text){
		TextView textView = getView(id);
		textView.setText(text);
		return this;
	}

	public RecyclerViewHolder setTextColor(int id, int color){
		TextView textView = getView(id);
		textView.setTextColor(color);
		return this;
	}

	public RecyclerViewHolder setBackground(int id, int drawable){
		View view = getView(id);
		view.setBackground(ContextCompat.getDrawable(mItemView.getContext(), drawable));
		return this;
	}
}
