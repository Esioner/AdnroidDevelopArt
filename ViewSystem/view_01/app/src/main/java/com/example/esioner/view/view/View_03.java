package com.example.esioner.view.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

public class View_03 extends View {

    private Context mContext;
    private Scroller mScroller;

    public View_03(Context context) {
        this(context, null);
    }

    public View_03(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public View_03(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    public View_03(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mScroller = new Scroller(mContext);

        //方法1： 通过动画
        ObjectAnimator.ofFloat(this, "translationX", 0, 100).setDuration(1000).start();
        //方法2：通过监听动画的执行来不停的调用 scrollTo 方法来达到弹性滚动的效果
        final int startX = 0;
        final int deltaX = 0;
        final View view = this;
        ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                view.scrollTo(startX + (int) (deltaX * fraction), 0);
            }
        });
    }


    private static final int MESSAGE_SCROLL_TO = 1; // 定义消息类型 - 滚动消息
    private static final int FRAME_COUNT = 30; // 定义全部的动画的总帧数
    private static final int DELAY_TIME = 33; // 发送消息延迟的时间
    private int mCount = 0; // 当前已经到达的状态帧数
    private View targetView = this; //需要滚动的 view
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_SCROLL_TO:
                    mCount++;
                    if (mCount <= FRAME_COUNT) {//判断当前是否播放完成
                        float fraction = mCount / (float) FRAME_COUNT;
                        int scrollX = (int) (fraction * 100);//定义当前需要滚动的距离
                        targetView.scrollTo(scrollX, 0);//开始滚动
                        //滚动完成后发送延迟消息通知下一次滚动
                        mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAY_TIME);
                    }
                    break;
                default:
                    break;
            }

        }
    };

    public void scrollByThread() {

    }

    //

    /**
     * 缓慢移动到指定位置
     */
    private void smoothScrollTo(int destX, int destY) {
        //获取已经滑动的横向距离
        int scrollX = getScrollX();
        //获取还剩下的需要滑动的距离
        int deltaX = destX - scrollX;
        int scrollY = getScrollY();
        int deltaY = destY - scrollY;
        //1000 ms 中移动到指定的位置
        mScroller.startScroll(scrollX, scrollY, deltaX, deltaY, 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }

    }
}
