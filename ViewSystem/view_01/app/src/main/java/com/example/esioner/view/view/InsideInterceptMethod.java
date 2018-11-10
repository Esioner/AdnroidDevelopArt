package com.example.esioner.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * @author Esioner
 * 滑动冲突的解决方式 ： 内部拦截法：需要配合 `requestDisallowInterceptTouchEvent()` 方法来进行工作，重写 `dispathTouchEvent()` 方法
 */
public class InsideInterceptMethod extends View {
    public InsideInterceptMethod(Context context) {
        super(context);
    }

    public InsideInterceptMethod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InsideInterceptMethod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public InsideInterceptMethod(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    int mLastX = 0;
    int mLastY = 0;
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        ViewParent parent = getParent();
        //获取当前触摸位置
        int x = (int) getX();
        int y = (int) getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //让父容器别对ACTION_DOWN事件进行拦截
                parent.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                //获取移动的距离
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                //表示父容器是否需要此事件的状态变量
                boolean isParentNeed = true;
                if (isParentNeed) {
                    //父容器需要此事件，允许容器拦截此事件
                    parent.requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        mLastY = y;
        mLastX = x;
        return super.dispatchTouchEvent(event);
    }
}
