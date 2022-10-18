package com.andriawan.askme.utils.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.andriawan.askme.utils.Constants.DEFAULT_MARGIN
import com.andriawan.askme.utils.Constants.ONE
import com.andriawan.askme.utils.Constants.TWO
import com.andriawan.askme.utils.Constants.ZERO
import com.andriawan.askme.utils.Constants.dip

class ItemSpacingDecoration : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val isFirstPosition = position == ZERO
        val isLastPosition = position == (state.itemCount.minus(ONE))
        when {
            isFirstPosition -> outRect.set(
                view.context.dip(DEFAULT_MARGIN),
                ZERO,
                view.context.dip(DEFAULT_MARGIN / TWO),
                ZERO
            )
            isLastPosition -> outRect.set(ZERO, ZERO, view.context.dip(DEFAULT_MARGIN), ZERO)
            else -> outRect.set(ZERO, ZERO, view.context.dip(DEFAULT_MARGIN / TWO), ZERO)
        }
    }
}