package com.gxj.dashboard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import edu.hrbeu.ice.dashboard.R;

/**
 * Created by gxj on 2017/4/1.
 */
public class DashboardCustom extends View {

    private Paint paint;
    private Paint textPaint;

    int speed = 0;
    float radius;
    private Bitmap back;
    private Bitmap center;
    private Bitmap arrow;

    public DashboardCustom(Context context) {
        this(context, null, 0);
    }

    public DashboardCustom(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashboardCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DashboardCustom, defStyleAttr, 0);

        speed = a.getInt(R.styleable.DashboardCustom_speed, 0);

        paint = new Paint();
        textPaint = new Paint();

        radius = a.getDimension(R.styleable.DashboardCustom_radius, 200);
        back = BitmapFactory.decodeResource(getResources(), R.drawable.dash);
        arrow = BitmapFactory.decodeResource(getResources(), R.drawable.arrow);

        a.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width;
        int height;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            float needWidth = back.getWidth();
            int desired = (int) (getPaddingLeft() + needWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            float needWidth = back.getHeight();
            int desired = (int) (getPaddingTop() + needWidth + getPaddingBottom());
            height = desired;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int backWidth = back.getWidth();
        int backHeight = back.getHeight();
        //绘制表盘
        canvas.drawBitmap(back, 0, 0, paint);


        //绘制指针
        float arc = (speed % 100) * 240 / 200;
        Matrix matrix = new Matrix();
        matrix.postRotate(arc);


        Bitmap dstbmp = Bitmap.createBitmap(arrow, 0, 0, arrow.getWidth(),
                arrow.getHeight(), matrix, true);

        if (arc >= 0)
            canvas.drawBitmap(dstbmp, backWidth / 2 - arrow.getWidth() / 2 + (int) (arrow.getWidth() * Math.sin(arc * Math.PI / 180)), (int) (backWidth / 2 - arrow.getWidth() / 4 - arrow.getHeight() * Math.cos(arc * Math.PI / 180)), null);
        else {
            canvas.drawBitmap(dstbmp, backWidth / 2 - arrow.getWidth() / 2 + (int) (arrow.getHeight() * Math.sin(arc * Math.PI / 180)), (int) (backWidth / 2 - arrow.getWidth() / 4 - arrow.getHeight() * Math.cos(arc * Math.PI / 180)), null);
        }

        //中心数字
        paint.setStrokeWidth(4);
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        //抗锯齿
        paint.setAntiAlias(true);

        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(speed % 100 + "", backWidth / 2, backHeight, paint);

    }

    public void setSpeed(int speed) {
        this.speed = speed;
        invalidate();
    }
}
