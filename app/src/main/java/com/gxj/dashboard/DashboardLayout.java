package com.gxj.dashboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import edu.hrbeu.ice.dashboard.R;


/**
 * Created by gxj on 2017/3/29.
 */

public class DashboardLayout extends RelativeLayout {
    private ImageView img_dashboard;
    private ImageView img_arrow;
    private TextView tv_num;
    private float mIndex_num;
    private Context context;

    public DashboardLayout(Context context) {
        this(context, null, 0);
    }

    public DashboardLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashboardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(context);
    }

    public void initView(Context context) {
        View.inflate(context, R.layout.dashboar_view, DashboardLayout.this);
        img_dashboard = (ImageView) this.findViewById(R.id.img_dashboard);
        img_arrow = (ImageView) this.findViewById(R.id.img_arrow);
        tv_num = (TextView) this.findViewById(R.id.tv_num);
    }

    float mFromDegrees = 0;
    float mToDegrees = 0;

    public void setIndexNum(float index_num) {
        this.mIndex_num = index_num;
        if (mIndex_num > 100f) {
            mIndex_num = 100f;
        }
        if (mIndex_num < -100f) {
            mIndex_num = -100f;
        }

        mToDegrees = (index_num) * 240 / 200;
        RotateAnimation rotateAnimation = new RotateAnimation(mFromDegrees, mToDegrees, 27, 82);
        rotateAnimation.setDuration(500);
        rotateAnimation.setFillAfter(true);
        img_arrow.startAnimation(rotateAnimation);
        mFromDegrees = mToDegrees;
        setText();
    }

    public void setText() {
        tv_num.setText(mIndex_num + "");

    }
}

