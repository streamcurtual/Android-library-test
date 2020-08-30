package com.test.testmvvm.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class WaveView extends View {
    private Paint mPaint = new Paint();
    private int mWidth,mHeight;
    private PorterDuffXfermode lineMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER);
    private int[] lineColors = new int[]{0xFF111111, 0xFFFFFFFF, 0xFFFFFFFF, 0xFF111111};
    private float[] linepositions = new float[]{0f, 0.1f, 0.9f, 1};

    private LinearGradient shader = new LinearGradient(
            mWidth / 40, 0,
            mWidth * 39 / 40, 0,
            lineColors,
            linepositions,
            Shader.TileMode.MIRROR);

    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLine(canvas);
    }

    private void drawLine(Canvas canvas) {
//        onDrawLineTimes++;
        canvas.save();
        mPaint.setXfermode(lineMode);
        mPaint.setShader(shader);
        mPaint.setStrokeWidth(2);
        canvas.drawLine(mWidth / 40, mHeight / 2, mWidth * 39 / 40, mHeight / 2, mPaint);
        mPaint.setXfermode(null);
        mPaint.setShader(null);
        mPaint.clearShadowLayer();
        canvas.restore();

    }
}
