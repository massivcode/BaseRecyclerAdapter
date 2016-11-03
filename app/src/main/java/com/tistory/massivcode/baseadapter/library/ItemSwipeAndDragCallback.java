package com.tistory.massivcode.baseadapter.library;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

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
 * Created by prChoe on 2016-11-03.
 */

public class ItemSwipeAndDragCallback extends ItemTouchHelper.Callback {

    private OnItemDragListener mOnItemDragListener;
    private OnItemSwipeListener mOnItemSwipeListener;
    private static final int TYPE_CONTENTS = 1;

    public ItemSwipeAndDragCallback(OnItemDragListener mOnItemDragListener, OnItemSwipeListener mOnItemSwipeListener) {
        this.mOnItemDragListener = mOnItemDragListener;
        this.mOnItemSwipeListener = mOnItemSwipeListener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int viewHolderViewType = viewHolder.getItemViewType();
        int targetViewType = target.getItemViewType();

        if (viewHolderViewType == TYPE_CONTENTS && targetViewType == TYPE_CONTENTS) {
            mOnItemDragListener.onItemDrag(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (viewHolder.getItemViewType() == TYPE_CONTENTS) {
            mOnItemSwipeListener.onItemSwipe(viewHolder.getAdapterPosition());
        }
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }
}
