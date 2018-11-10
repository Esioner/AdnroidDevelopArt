package com.example.esioner.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * @author Esioner
 * 滑动冲突的解决方式 ： 外部拦截法，重写容器的 onInterceptTouchEvent
 */
public class OutInterceptMethod extends ViewGroup {
    public OutInterceptMethod(Context context) {
        super(context);
    }

    public OutInterceptMethod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OutInterceptMethod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public OutInterceptMethod(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean interecepted = false;
        boolean isNeedIntercept = true;
        //获取当前触摸点的位置
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //不能直接拦截 ACTION_DOWN 事件，因为一旦父容器拦截了ACTION_DOWN 事件，
                // 那么后续的 ACTION_MOVE & ACTION_UP 都会交给父容器来处理，就没法传递给子 view 了
                interecepted = false;
                break;
            case MotionEvent.ACTION_MOVE:
                //父容器判断是否需要拦截，需要就返回 true，不需要就返回 false
                //判断是否需要拦截
                if (!isNeedIntercept) {
                    interecepted = true;
                } else {
                    interecepted = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                interecepted = false;
                break;
            default:
                break;
        }
        int mLastXIntercept = x;
        int mLastYIntercept = y;

        return interecepted;
    }
}
