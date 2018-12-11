package com.example.recycleview_01.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.recycleview_01.R;

public class RecycleDecoration extends RecyclerView.ItemDecoration {

    private final Drawable drawable;

    public RecycleDecoration(Context context) {
        //在构造方法中，讲分割线的样子拿到
        drawable = ContextCompat.getDrawable(context, R.drawable.recycle_divider);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //画垂直和水平的线
        drawHorizontal(c,parent);
        drawVertical(c,parent);
    }
    //垂直
    private void drawVertical(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + drawable.getIntrinsicWidth();
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(c);
        }
    }

    //水平
    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        //根据孩子的坐标位置计算我们要画的分割线位置
        for(int i = 0;i<childCount;i++){
            View childAt = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int left = childAt.getLeft() - params.leftMargin;
            int right = childAt.getRight() - params.rightMargin + drawable.getIntrinsicWidth();
            int top = childAt.getBottom() + params.bottomMargin;
            int bottom = top + drawable.getIntrinsicHeight();
            drawable.setBounds(left,top,right,bottom);
            drawable.draw(c);
        }
    }
}
