package com.tistory.massivcode.baseadapter.library;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.tistory.massivcode.baseadapter.library.BaseRecyclerAdapter.ViewType.CONTENTS_ONLY;
import static com.tistory.massivcode.baseadapter.library.BaseRecyclerAdapter.ViewType.FOOTER;
import static com.tistory.massivcode.baseadapter.library.BaseRecyclerAdapter.ViewType.HEADER;
import static com.tistory.massivcode.baseadapter.library.BaseRecyclerAdapter.ViewType.HEADER_FOOTER;

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
 * Created by prChoe on 2016-10-17.
 */

public abstract class BaseRecyclerAdapter<Item, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnItemDragListener, OnItemSwipeListener {

    public interface OnRecyclerItemClickListener {
        void onRecyclerItemClicked(View view, int position);
    }

    public interface OnRecyclerItemLongClickListener {
        void onRecyclerItemLongClicked(View view, int position);
    }

    /**
     * Header 임을 나타내기 위한 Marker interface
     */
    public interface Header {
    }

    /**
     * Footer 임을 나타내기 위한 Marker interface
     */
    public interface Footer {
    }


    /**
     * CONTENTS_ONLY : Header 와 Footer 없이 Contents 로만 이루어져 있음
     * <p>
     * HEADER : Header 와 Contents 로 이루어져 있음
     * <p>
     * FOOTER : Footer 와 Contents 로 이루어져 있음
     * <p>
     * HEADER_FOOTER : Header 와 Footer 그리고 Contents 로 이루어져 있음
     */
    public enum ViewType {
        CONTENTS_ONLY, HEADER, FOOTER, HEADER_FOOTER
    }

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_CONTENTS = 1;
    private static final int TYPE_FOOTER = 2;

    /**
     * RecyclerView 의 ViewType
     */
    private ViewType mViewType = CONTENTS_ONLY;

    /**
     * HeaderViewHolder 에 Binding 할 Data
     */
    private Header mHeader;

    /**
     * FooterViewHolder 에 Binding 할 Data
     */
    private Footer mFooter;

    /**
     * ItemViewHolder 에 Binding 할 DataSet
     */
    private List<Item> mData;

    private ItemTouchHelper mItemTouchHelper;

    public BaseRecyclerAdapter(@Nullable List<Item> data, ViewType viewType) {
        setViewType(viewType);
        setData(data);
    }


    private void setViewType(ViewType viewType) {
        switch (viewType) {
            case CONTENTS_ONLY:
                mViewType = CONTENTS_ONLY;
                break;
            case HEADER:
                mViewType = HEADER;
                break;
            case FOOTER:
                mViewType = FOOTER;
                break;
            case HEADER_FOOTER:
                mViewType = HEADER_FOOTER;
                break;
            default:
                mViewType = CONTENTS_ONLY;
                break;
        }
    }

    private void setData(@Nullable List<Item> data) {
        mData = data;

        if (mData == null) {
            mData = new ArrayList<>();
        }
    }

    public void setHeaderItem(Header header) {
        mHeader = header;
    }

    public void setFooterItem(Footer footer) {
        mFooter = footer;
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;

        switch (viewType) {
            case TYPE_CONTENTS:
                holder = onCreateItemViewHolder(parent, viewType);
                break;
            case TYPE_HEADER:
                holder = onCreateHeaderViewHolder(parent, viewType);
                holder.itemView.setFocusable(false);
                holder.itemView.setClickable(false);
                break;
            case TYPE_FOOTER:
                holder = onCreateFooterViewHolder(parent, viewType);
                break;
        }

        return holder;
    }

    protected abstract VH onCreateItemViewHolder(ViewGroup parent, int viewType);

    protected abstract RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType);

    protected abstract RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType);


    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        switch (viewType) {
            case TYPE_CONTENTS:
                onBindItemViewHolder((VH) holder, getItem(position));
                break;
            case TYPE_HEADER:
                onBindHeaderViewHolder(holder, mHeader);
                break;
            case TYPE_FOOTER:
                onBindFooterViewHolder(holder, mFooter);
                break;
        }
    }

    protected abstract void onBindItemViewHolder(VH holder, Item item);

    protected abstract void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, Header headerItem);

    protected abstract void onBindFooterViewHolder(RecyclerView.ViewHolder holder, Footer footerItem);

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        int size = mData.size();

        switch (mViewType) {
            case HEADER:
            case FOOTER:
                size += 1;
                break;
            case HEADER_FOOTER:
                size += 2;
                break;
        }

        return size;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = TYPE_CONTENTS;

        switch (mViewType) {
            case HEADER:
                if (position == 0) {
                    viewType = TYPE_HEADER;
                } else {
                    viewType = TYPE_CONTENTS;
                }
                break;
            case FOOTER:
                if (position == mData.size()) {
                    viewType = TYPE_FOOTER;
                } else {
                    viewType = TYPE_CONTENTS;
                }
                break;
            case HEADER_FOOTER:
                if (position == 0) {
                    viewType = TYPE_HEADER;
                } else if (position == mData.size() + 1) {
                    viewType = TYPE_FOOTER;
                } else {
                    viewType = TYPE_CONTENTS;
                }
                break;
        }

        return viewType;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        ItemTouchHelper.Callback callback = new ItemSwipeAndDragCallback(this, this);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onItemDrag(int startItemIndex, int endItemIndex) {
        int startDataIndex = startItemIndex;
        int endDataIndex = endItemIndex;

        switch (mViewType) {
            case HEADER_FOOTER:
            case HEADER:
                startDataIndex -= 1;
                endDataIndex -= 1;
                break;
        }

        Collections.swap(mData, startDataIndex, endDataIndex);
        notifyItemMoved(startItemIndex, endItemIndex);
    }

    @Override
    public void onItemSwipe(int itemIndex) {
        int dataIndex = itemIndex;

        switch (mViewType) {
            case HEADER_FOOTER:
            case HEADER:
                dataIndex -= 1;
                break;
        }

        mData.remove(dataIndex);
        notifyItemRemoved(itemIndex);
    }

    /**
     * 해당 포지션의 Item 을 리턴합니다. DataSet 의 사이즈가 0 일 경우 null 을 리턴합니다.
     *
     * @param position : Item 의 포지션
     * @return : 해당 포지션의 Item
     */
    public final Item getItem(int position) {
        Item item = null;

        switch (mViewType) {
            case HEADER:
                item = mData.get(position - 1);
                break;
            case FOOTER:
                item = mData.get(position);
                break;
            case HEADER_FOOTER:
                int virtualSize = mData.size() + 2;

                if (position < virtualSize) {
                    item = mData.get(position - 1);
                } else {
                    item = mData.get(position - 2);
                }
                break;
            case CONTENTS_ONLY:
                item = mData.get(position);
                break;
        }

        return item;
    }

    /**
     * 기존 DataSet 를 Clear 하고 새로운 DataSet 으로 데이터를 교체합니다.
     *
     * @param items : 갈아치워질 새로운 DataSet
     */
    public final void swapItems(List<Item> items) {
        mData.clear();
        if (items != null) {
            mData.addAll(items);
        }

        notifyDataSetChanged();
    }

    /**
     * 기존 DataSet 의 마지막에 새 item 을 삽입합니다.
     *
     * @param item : DataSet 에 삽입할 item
     */
    public final void insertItem(Item item) {
        mData.add(item);
        notifyItemInserted(mData.size() - 1);
    }

    /**
     * 지정된 position 에 새 item 을 삽입합니다.
     *
     * @param item     : DataSet 에 삽입할 item
     * @param position : 삽입할 DataSet 의 Position
     */
    public final void insertItem(Item item, int position) {
        mData.add(position, item);
        notifyDataSetChanged();
    }

    /**
     * 기존 DataSet 에서 item 과 동일한 item 을 제거합니다.
     *
     * @param item : 제거할 item
     * @return : 제거에 성공했으면 true, 기존 DataSet 에 포함되지 않아 실패했으면 false
     */
    public final boolean removeItem(Item item) {
        boolean isRemoved = false;

        int removePosition = mData.indexOf(item);
        isRemoved = mData.remove(item);

        if (removePosition != -1) {
            notifyItemRemoved(removePosition);
        }

        return isRemoved;
    }

    /**
     * 기존 DataSet 에서 position 에 위치한 Item 을 제거합니다.
     *
     * @param position : 제거할 item 의 position
     */
    public final void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 기존 DataSet 의 position 에 위치한 Item 을 수정합니다.
     *
     * @param position : 수정할 item 의 position
     * @param newItem  : 새 Item
     */
    public final void updateItem(int position, Item newItem) {
        mData.set(position, newItem);
        notifyItemChanged(position);
    }


    public abstract void setOnItemClickListener(OnRecyclerItemClickListener listener);

    public abstract void setOnItemLongClickListener(OnRecyclerItemLongClickListener listener);


}
