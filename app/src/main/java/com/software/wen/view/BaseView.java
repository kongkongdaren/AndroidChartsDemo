package com.software.wen.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.software.wen.androidchartsdemo.R;

import java.util.Map;

/**
 * Created by wen on 2018/7/5.
 */

public abstract class BaseView extends View {

    private Paint mPaint;
    //视图的宽
    public int width;
    //视图的高
    public int height;
    //起点的X，Y坐标值
    public int originX=100;
    public int originY=800;
    //x轴低级划分
    public int axisDivideSizeX=9;
    public int axisDivideSizeY=7;
    //第一个维度为值，第二个维度为颜色
    public int[][]columnInfo;
    public float maxAxisValueY=700;
    public float maxAxisValueX=900;

    private String mGrapthTitle;
    private String mXAxisName;
    private String mYAxisName;
    private float mAxisTextSize;
    private int mAxisTextColor;


    public BaseView(Context context) {
        this(context,null);
    }

    public BaseView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SunnyGraphStyle);
        mGrapthTitle = typedArray.getString(R.styleable.SunnyGraphStyle_graphTitle);
        mXAxisName = typedArray.getString(R.styleable.SunnyGraphStyle_X_AxisName);
        mYAxisName = typedArray.getString(R.styleable.SunnyGraphStyle_Y_AxisName);
        mAxisTextColor = typedArray.getColor(R.styleable.SunnyGraphStyle_axisTextColor,context.getResources().getColor(android.R.color.black));
        mAxisTextSize = typedArray.getDimension(R.styleable.SunnyGraphStyle_axisTextSize,12);

        if(typedArray != null){
            typedArray.recycle();
        }

        initPaint();

    }

    private void initPaint() {
        if (mPaint==null){
            mPaint=new Paint();
            //抖动
            mPaint.setDither(true);
            //锯齿
            mPaint.setAntiAlias(true);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        width=getWidth()-originX;
        height=(originY>getHeight()?getHeight():originY)-400;
        //x轴
        drawAxisX(canvas,mPaint);
        //y轴
        drawAxisY(canvas,mPaint);
        //刻度
        drawAxisScaleMarkX(canvas,mPaint);
        drawAxisScaleMarkY(canvas,mPaint);
        //刻度值
        drawAxisScaleMarkValueX(canvas,mPaint);
        drawAxisScaleMarkValueY(canvas,mPaint);
        //箭头
        drawAxisArrowsX(canvas,mPaint);
        drawAxisArrowsY(canvas,mPaint);
        drawColumn(canvas,mPaint);
        //标题
        drawTitle(canvas,mPaint);
    }

    /**
     * 柱条
     * @param canvas
     * @param paint
     */
    protected abstract void drawColumn(Canvas canvas, Paint paint);

    /**
     * 画y轴的箭头
     * @param canvas
     * @param paint
     */
    private void drawAxisArrowsY(Canvas canvas, Paint paint) {
        Path mPathY=new Path();
        mPathY.moveTo(originX,originY-height-30);//起始点
        mPathY.lineTo(originX-10,originY-height);
        mPathY.lineTo(originX+10,originY-height);
        mPathY.close();
        canvas.drawPath(mPathY,paint);
        canvas.drawText(mYAxisName,originX-50,originY-height-30,paint);
    }

    /**
     * 画x轴的箭头
     * @param canvas
     * @param paint
     */
    private void drawAxisArrowsX(Canvas canvas, Paint paint) {
        Path mPathX=new Path();
        mPathX.moveTo(originX+width+30,originY);//起始点
        mPathX.lineTo(originX+width,originY-10);//下一点
        mPathX.lineTo(originX+width,originY+10);//下一点
        mPathX.close();
        canvas.drawPath(mPathX,paint);
        canvas.drawText(mXAxisName,originX+width,originY+30,paint);
    }

    /**
     *  画y轴的刻度值
     * @param canvas
     * @param paint
     */
    protected abstract void drawAxisScaleMarkValueY(Canvas canvas, Paint paint);

    /**
     * 画y轴的刻度
     * @param canvas
     * @param paint
     */
    protected abstract void drawAxisScaleMarkY(Canvas canvas, Paint paint);

    /**
     * 画x轴的刻度值
     * @param canvas
     * @param paint
     */
    protected abstract void drawAxisScaleMarkValueX(Canvas canvas, Paint paint);

    /**
     * 画x轴的刻度
     * @param canvas
     * @param paint
     */
    protected abstract void drawAxisScaleMarkX(Canvas canvas, Paint paint);

    /**
     * 画标题
     * @param canvas
     * @param paint
     */
    private void drawTitle(Canvas canvas, Paint paint) {
        //画标题
        if (mGrapthTitle != null) {
            //设置画笔绘制文字的属性
            mPaint.setColor(mAxisTextColor);
            mPaint.setTextSize(mAxisTextSize);
            mPaint.setFakeBoldText(true);
            canvas.drawText(mGrapthTitle, (getWidth()/2)-(paint.measureText(mGrapthTitle)/2), originY + 100, paint);
        }
    }

    /**
     * 画Y轴
     * @param canvas
     * @param paint
     */
    protected  abstract  void drawAxisY(Canvas canvas, Paint paint);

    /**
     * 画X轴
     * @param canvas
     * @param paint
     */
    protected abstract void drawAxisX(Canvas canvas, Paint paint);

    /**
     * 控件对外数据交互方法
     * @param columnInfo
     */
    public void setColumnInfo(int[][] columnInfo ){
        this.columnInfo=columnInfo;
    }

    public void setmGrapthTitle(String mGrapthTitle) {
        this.mGrapthTitle = mGrapthTitle;
    }

    public void setmXAxisName(String mXAxisName) {
        this.mXAxisName = mXAxisName;
    }

    public void setmYAxisName(String mYAxisName) {
        this.mYAxisName = mYAxisName;
    }

    public void setmAxisTextSize(float mAxisTextSize) {
        this.mAxisTextSize = mAxisTextSize;
    }

    public void setmAxisTextColor(int mAxisTextColor) {
        this.mAxisTextColor = mAxisTextColor;
    }

    /**
     * 控件X的最大值和多少等分
     * @param maxAisValueX
     * @param axisDividedSizex
     */
    public  void setXAxisValue(float maxAisValueX,int axisDividedSizex){
        this.maxAxisValueX=maxAisValueX;
        this.axisDivideSizeX=axisDividedSizex;
    }
    /**
     * 控件Y的最大值和多少等分
     * @param maxAxisValueY
     * @param axisDevidedSizeY
     */
    public  void setYAxisValue(float maxAxisValueY ,int axisDevidedSizeY){
        this.maxAxisValueY=maxAxisValueY;
        this.axisDivideSizeY=axisDevidedSizeY;
    }
}
