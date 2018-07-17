package com.software.wen.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Created by wen on 2018/7/5.
 */

public  class ChartDemo extends BaseView {


    public ChartDemo(Context context) {
        super(context);
    }

    public ChartDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChartDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    /**
     * 柱条
     * @param canvas
     * @param paint
     */
    @Override
    protected void drawColumn(Canvas canvas, Paint paint) {
         if (columnInfo!=null){
             float cellWidth=width/axisDivideSizeX;
             for (int i=0;i<columnInfo.length;i++){
                 paint.setColor(columnInfo[i][1]);
                 float leftTopY=originY-height*columnInfo[i][0]/maxAxisValueY;
                 canvas.drawRect(originX+cellWidth*(i+1),leftTopY,originX+cellWidth*(i+2),originY,paint);
             }
         }
    }

    /**
     * y轴刻度值
     * @param canvas
     * @param paint
     */
    @Override
    protected void drawAxisScaleMarkValueY(Canvas canvas, Paint paint) {
        float cellHeight=height/axisDivideSizeY;
        float cellValue=maxAxisValueY/axisDivideSizeY;
        for (int i=0;i<axisDivideSizeY;i++){
            canvas.drawText(String.valueOf(cellValue*i),originX-30,originY-cellHeight*i+10,paint);
        }

    }

    /**
     * y轴刻度
     * @param canvas
     * @param paint
     */
    @Override
    protected void drawAxisScaleMarkY(Canvas canvas, Paint paint) {

        float cellHeight=height/axisDivideSizeY;
        for (int i=0;i<axisDivideSizeY-1;i++){
            canvas.drawLine(originX,(originY-cellHeight*(i+1)),originX+10,
                    (originY-cellHeight*(i+1)),paint);
        }
    }

    /**
     * x刻度值
     * @param canvas
     * @param paint
     */
    @Override
    protected void drawAxisScaleMarkValueX(Canvas canvas, Paint paint) {
        paint.setColor(Color.GRAY);
        paint.setTextSize(16);
        paint.setFakeBoldText(true);
        float cellWidth=width/axisDivideSizeX;
        for (int i=0;i<axisDivideSizeX;i++){
            canvas.drawText(String.valueOf(i),cellWidth*i+originX-35,originY+30,paint);
        }
    }

    /**
     * X刻度
     * @param canvas
     * @param paint
     */
    @Override
    protected void drawAxisScaleMarkX(Canvas canvas, Paint paint) {

        float cellWidth=width/axisDivideSizeX;
        for (int i=0;i<axisDivideSizeX-1;i++){
            canvas.drawLine(cellWidth*(i+1)+originX,originY,cellWidth*(i+1)+originX,originY-10,paint);
        }

    }

    @Override
    protected void drawAxisY(Canvas canvas, Paint paint) {
        if(paint!=null){
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(5);
            //参数说明：起始点左边x,y，终点坐标x,y，画笔
            canvas.drawLine(originX,originY,originX,originY-height,paint);
        }

    }

    @Override
    protected void drawAxisX(Canvas canvas, Paint paint) {
        if(paint!=null){
            paint.setColor(Color.BLACK);
            //设置画笔宽度
            paint.setStrokeWidth(5);
            //设置画笔抗锯齿
            paint.setAntiAlias(true);
            canvas.drawLine(originX,originY,originX+width,originY,paint);
        }

    }
}
