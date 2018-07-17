package com.software.wen.androidchartsdemo;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.software.wen.view.ChartDemo;
import com.software.wen.view.ColumnView;

public class MainActivity extends Activity {
   //  private ColumnView mChart;
    private ChartDemo mChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // mChart= (ColumnView) findViewById(R.id.cv_chart);
        mChart= (ChartDemo) findViewById(R.id.cd_chart);
        int [][] columnInfo=new int[][]{
                {6, Color.BLUE},
                {12, Color.RED},
                {9, Color.GREEN},
                {4, Color.YELLOW},
                {10, Color.LTGRAY},
                {8, Color.DKGRAY},
                {3, Color.GRAY},
        };
        mChart.setColumnInfo(columnInfo);
        mChart.setXAxisValue(10,9);
        mChart.setYAxisValue(10,7);
    }
}
