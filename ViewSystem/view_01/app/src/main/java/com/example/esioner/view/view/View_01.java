package com.example.esioner.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;


public class View_01 extends View {
    private static final String TAG = View_01.class.getSimpleName();

    private VelocityTracker velocityTracker;
    private Context mContext;
    private GestureDetector gestureDetector;

    public View_01(Context context) {
        this(context, null);
    }

    public View_01(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public View_01(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, -1);
    }

    public View_01(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        int touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        Log.d(TAG, "View_01: touchSlop = " + touchSlop);
        mContext = context;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        return true;
    }

    Scroller scroller = new Scroller(mContext);

    //缓慢滚动到指定位置
    private void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        //1000 ms 内滑向 delta 的位置
        scroller.startScroll(scrollX, 0, delta, 0, 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        //使用 scroller
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }

    }

    /**
     * 使用 VelocityTracker
     */
    public void initVelocity(MotionEvent event) {
        //在 OnTouchEvent 中追踪 event 的速度
        //先指定追踪手指滑动的速度
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            //初始化 VelocityTracker 对象
            velocityTracker = VelocityTracker.obtain();
            //让 velocityTracker 追踪此 event 的速度
            velocityTracker.addMovement(event);
            //获取速度之前先计算速度，计算公式：速度 = （终点位置 - 起点位置）/时间，时间就 computeCurrentVelocity 中传入的参数，单位为 ms
            velocityTracker.computeCurrentVelocity(1000);
            //获取水平速度
            int xVelocity = (int) velocityTracker.getXVelocity();
            //获取竖直速度
            int yVelocity = (int) velocityTracker.getYVelocity();
            Log.d(TAG, "onTouchEvent: xVelocity = " + xVelocity);
            Log.d(TAG, "onTouchEvent: yVelocity = " + yVelocity);
            //使用完成之后，释放对象
            velocityTracker.clear();
            velocityTracker.recycle();
        }
    }

    public void initGestureDe() {
        //新建一个实现 OnGestureListener 接口的对象，并实现其内部方法
        GestureDetector.OnGestureListener onGestureListener = new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                Log.d(TAG, "onDown: ");
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                Log.d(TAG, "onShowPress: ");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.d(TAG, "onSingleTapUp: ");
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.d(TAG, "onScroll: ");
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                Log.d(TAG, "onLongPress: ");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.d(TAG, "onFling: ");
                return false;
            }
        };
        //2. 新建一个 GestureDetector 对象，并将 实现了 onGestureListener接口的对象作为参数传递
        gestureDetector = new GestureDetector(onGestureListener);
        //解决屏幕长按后无法拖动的问题
        gestureDetector.setIsLongpressEnabled(false);

        //3. 接管目标 view 中的 onTouchEvent 方法
//        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }
}
