package com.example.esioner.view.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

public class View_02_TouchView extends View {
    private static String TAG = View_02_TouchView.class.getSimpleName();
    private View mView;

    public View_02_TouchView(Context context) {
        this(context, null);
    }

    public View_02_TouchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View_02_TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    public View_02_TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mView = this;
    }

    /**
     * 上一次触摸点的横纵坐标
     */
    int mLastX, mLastY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "================================================ START ================================================");
        //获取触摸点的横纵坐标
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        Log.d(TAG, "onTouchEvent: 触摸点位置 ： x = " + x + " , y = " + y);
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            int deltaX = x - mLastX;
            int deltaY = y - mLastY;
            mView.setX(x);
            mView.setY(y);
            invalidate();
            Log.d(TAG, "onTouchEvent: 平移距离 deltaX = " + deltaX + " , deltaY = " + deltaY);


        }
        mLastX = x;
        mLastY = y;
        Log.d(TAG, "================================================ END ================================================");
        return true;
    }
}
