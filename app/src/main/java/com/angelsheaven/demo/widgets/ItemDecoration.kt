package com.angelsheaven.demo.widgets

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * TODO Explain ItemDecoration purpose
 * @author Quan Nguyen
 * @see linkListArticlesFragment
 */
class ItemDecoration(private val padding: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        with(outRect){
            if(parent.getChildAdapterPosition(view)==0) {
                top = padding
            }
            left = padding
            bottom = padding
            right = padding
        }

    }
}
