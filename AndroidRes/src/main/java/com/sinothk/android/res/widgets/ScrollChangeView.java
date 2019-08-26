package com.sinothk.android.res.widgets;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/6 on 16:08
 *  项目:  android
 *  描述:  滚动显示隐藏 TitleBar
 *  更新:
 * <pre>
 */
public class ScrollChangeView extends ScrollView {

    private OnScrollListener listener;

    public void setOnScrollListener(OnScrollListener listener) {
        this.listener = listener;
    }

    public ScrollChangeView(Context context) {
        super(context);
    }

    public ScrollChangeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollChangeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnScrollChange(final Context context, final View titleView, final View baseView, final int normalColor, final int red, final int green, final int blue) {

        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {

                int i = dip2px(context, scrollY);
                int dp = px2dp(context, i);

                if (dp > 100) {
                    titleView.setBackgroundColor(Color.argb(255, red, green, blue));
                } else {
                    float scale = (float) scrollY / baseView.getHeight();
                    int alpha = (int) (255 * scale);
                    titleView.setBackgroundColor(Color.argb(alpha, red, green, blue));
                }
            }
        });
    }

    //将px转换为dp
    public static int px2dp(Context context, int pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    //将像素转换为px
    public static int dip2px(Context context, int dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //设置接口
    public interface OnScrollListener {
        void onScroll(int scrollY);
    }

    //重写原生onScrollChanged方法，将参数传递给接口，由接口传递出去
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (listener != null) {

            //这里我只传了垂直滑动的距离
            listener.onScroll(t);
        }
    }
}

