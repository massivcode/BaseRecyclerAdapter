package com.tistory.massivcode.baseadapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tistory.massivcode.baseadapter.library.BaseRecyclerAdapter;

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


    public ModelAdapter(@Nullable List<Model> data, ViewType viewType) {
        super(data, viewType);
    }

    @Override
    protected ModelViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_model, parent, false);

        return new ModelViewHolder(itemView);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);

        return new HeaderViewHolder(headerView);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        View footerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer, parent, false);

        return new FooterViewHolder(footerView);
    }

    @Override
    protected void onBindItemViewHolder(ModelViewHolder holder, Model model) {
        holder.mContentsTextView.setText(model.getContents());
        holder.mTitleTextView.setText(model.getTitle());
    }

    @Override
    protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, Header headerItem) {
        HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        HeaderItem item = (HeaderItem) headerItem;

        headerViewHolder.mHeaderTextView1.setText(item.getString1());
        headerViewHolder.mHeaderTextView2.setText(item.getString2());
    }

    @Override
    protected void onBindFooterViewHolder(RecyclerView.ViewHolder holder, Footer footerItem) {
        FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
        FooterItem item = (FooterItem) footerItem;

        footerViewHolder.mFooterTextView1.setText(item.getString1());
        footerViewHolder.mFooterTextView2.setText(item.getString2());
    }


    static class ModelViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTextView, mContentsTextView;

        ModelViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.item_model_title_tv);
            mContentsTextView = (TextView) itemView.findViewById(R.id.item_model_contents_tv);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView mHeaderTextView1, mHeaderTextView2;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            mHeaderTextView1 = (TextView) itemView.findViewById(R.id.item_header_tv1);
            mHeaderTextView2 = (TextView) itemView.findViewById(R.id.item_header_tv2);
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView mFooterTextView1, mFooterTextView2;

        public FooterViewHolder(View itemView) {
            super(itemView);
            mFooterTextView1 = (TextView) itemView.findViewById(R.id.item_footer_tv1);
            mFooterTextView2 = (TextView) itemView.findViewById(R.id.item_footer_tv2);
        }
    }
}
