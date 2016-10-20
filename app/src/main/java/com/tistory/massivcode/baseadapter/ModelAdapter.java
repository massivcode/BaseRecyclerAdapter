package com.tistory.massivcode.baseadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Copyright 2016 Pureum Choe
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * <p>
 * Created by prChoe on 2016-10-17.
 */

public class ModelAdapter extends BaseRecyclerAdapter<Model, ModelAdapter.ModelViewHolder> {
    private OnRecyclerItemClickListener mOnItemClickListener;
    private OnRecyclerItemLongClickListener mOnItemLongClickListener;

    public ModelAdapter(List<Model> mData) {
        super(mData);
    }

    @Override
    public ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_model, parent, false);
        final ModelViewHolder holder = new ModelViewHolder(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onRecyclerItemClicked(itemView, holder.getAdapterPosition());
                }
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mOnItemLongClickListener != null) {
                    mOnItemLongClickListener.onRecyclerItemLongClicked(itemView, holder.getAdapterPosition());
                    return true;
                }

                return false;
            }
        });

        return holder;
    }


    @Override
    public void onBindViewHolder(ModelViewHolder holder, int position) {
        Model item = getItem(position);
        holder.mTitleTextView.setText(item.getTitle());
        holder.mContentsTextView.setText(item.getContents());
    }

    @Override
    public void setOnItemClickListener(OnRecyclerItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @Override
    public void setOnItemLongClickListener(OnRecyclerItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
    }


    public static class ModelViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitleTextView, mContentsTextView;

        public ModelViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.item_model_title_tv);
            mContentsTextView = (TextView) itemView.findViewById(R.id.item_model_contents_tv);
        }
    }
}
