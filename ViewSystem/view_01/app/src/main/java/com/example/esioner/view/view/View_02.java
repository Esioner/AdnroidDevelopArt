package com.example.esioner.view.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Scroller;


public class View_02 extends View {
    private static final String TAG = View_02.class.getSimpleName();

    public View_02(Context context) {
        this(context, null);
    }

    public View_02(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public View_02(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, -1);
    }

    public View_02(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        //补间动画
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 100, 0, 0);
        translateAnimation.setDuration(100);
        //如果为 false ，动画执行完成后，view 还会恢复到原来的位置
        translateAnimation.setFillAfter(true);
        startAnimation(translateAnimation);
        //属性动画
        View targetView = this;
        ObjectAnimator.ofFloat(targetView, "translationX", 0, 100).setDuration(100).start();


        ViewGroup.LayoutParams layoutParams = targetView.getLayoutParams();
        layoutParams.width += 100;
        layoutParams.height += 100;
        //这两个方法均可以达到目的 ：targetView.requestLayout();
        targetView.setLayoutParams(layoutParams);

    }


    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public void scrollBy(int x, int y) {
        super.scrollBy(x, y);
    }
}
