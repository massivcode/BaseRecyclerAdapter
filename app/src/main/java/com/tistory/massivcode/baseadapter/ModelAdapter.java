package com.tistory.massivcode.baseadapter;

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

public class ModelAdapter extends BaseRecyclerAdapter {

    public ModelAdapter(List<Model> mData) {
        super(mData);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ModelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_model, parent, false));
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        ModelViewHolder viewHolder = (ModelViewHolder) holder;
        Model item = (Model) getItem(position);
        viewHolder.mTitleTextView.setText(item.getTitle());
        viewHolder.mContentsTextView.setText(item.getContents());
    }

    private static class ModelViewHolder extends BaseViewHolder {
        public TextView mTitleTextView, mContentsTextView;

        public ModelViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.item_model_title_tv);
            mContentsTextView = (TextView) itemView.findViewById(R.id.item_model_contents_tv);
        }
    }
}
