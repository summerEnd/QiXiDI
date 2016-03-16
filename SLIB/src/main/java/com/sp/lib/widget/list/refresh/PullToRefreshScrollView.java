package com.sp.lib.widget.list.refresh;

import android.content.Context;
import android.support.v4.view.ScrollingView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import com.sp.lib.common.util.SLog;

/**
 * 封装了ScrollView的下拉刷新
 *
 * @author Li Hong
 * @since 2013-8-22
 */
public class PullToRefreshScrollView extends PullToRefreshBase<ScrollView> {
    /**
     * 构造方法
     *
     * @param context context
     */
    public PullToRefreshScrollView(Context context) {
        this(context, null);
    }

    /**
     * 构造方法
     *
     * @param context context
     * @param attrs   attrs
     */
    public PullToRefreshScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 构造方法
     *
     * @param context  context
     * @param attrs    attrs
     * @param defStyle defStyle
     */
    public PullToRefreshScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * @see com.sp.lib.widget.list.refresh.PullToRefreshBase#createRefreshableView(android.content.Context, android.util.AttributeSet)
     */
    @Override
    protected ScrollView createRefreshableView(Context context, AttributeSet attrs) {
        ScrollViewEx scrollView = new ScrollViewEx(context);
        return scrollView;
    }

    /**
     * @see com.sp.lib.widget.list.refresh.PullToRefreshBase#isReadyForPullDown()
     */
    @Override
    protected boolean isReadyForPullDown() {
        return !((ScrollViewEx) mRefreshableView).canScrollVertically(-1);
    }

    /**
     * @see com.sp.lib.widget.list.refresh.PullToRefreshBase#isReadyForPullUp()
     */
    @Override
    protected boolean isReadyForPullUp() {
        return !((ScrollViewEx) mRefreshableView).canScrollVertically(1);
    }

    /**
     * 增加上下滚动 是否可用判断的ScrollView
     */
    private class ScrollViewEx extends ScrollView{
        public ScrollViewEx(Context context) {
            super(context);
        }

        public ScrollViewEx(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public ScrollViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        public boolean canScrollVertically(int direction) {
            final int offset = computeVerticalScrollOffset();
            final int range = computeVerticalScrollRange() - computeVerticalScrollExtent();
            if (range == 0) return false;
            if (direction < 0) {
                return offset > 0;
            } else {
                return offset < range - 1;
            }
//            return super.canScrollVertically(direction);
        }

    }
}
