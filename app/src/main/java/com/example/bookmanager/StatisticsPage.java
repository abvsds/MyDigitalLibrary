package com.example.bookmanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;

import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.text.DecimalFormat;
import java.util.ArrayList;

public class StatisticsPage extends AppCompatActivity {
BarChart barchart, barchart1;
SqliteDB db;
ArrayList<BarEntry> barentries;
    ArrayList<BarEntry> barentries1;
TextView messagee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_page);

        ActionBar actionBar;
        actionBar=getSupportActionBar();
        ColorDrawable colorDrawable= new ColorDrawable(Color.parseColor("#FF6200EE"));
        actionBar.setBackgroundDrawable(colorDrawable);

        String value_name = getIntent().getStringExtra("Username");
        messagee= findViewById(R.id.message2);



            db = new SqliteDB(this);
          barchart=findViewById(R.id.barChart);
            barentries=new ArrayList<>();
            barentries = db.getBarEntries(value_name);
            BarDataSet barDataSet = new BarDataSet(barentries, "");
            barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
            barDataSet.setValueTextSize(10f);

            BarData barData = new BarData(barDataSet);
            barData.setBarWidth(0.7f);
            barData.setValueFormatter(new MyValueFormatter());


            barchart.setData(barData);

            barchart.setBackgroundColor(Color.TRANSPARENT);

            Description description = barchart.getDescription();
            description.setEnabled(true);
            description.setPosition(850, 50);
            description.setText("Number of books read per month");
            description.setTextSize(15f);



            XAxis xAxis = barchart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setGranularity(1f);
            xAxis.setDrawGridLines(false);
            final ArrayList<String> xAxisLabel = new ArrayList<>();
            xAxisLabel.add("start");
            xAxisLabel.add("January");
            xAxisLabel.add("February");
            xAxisLabel.add("March");
            xAxisLabel.add("April");
            xAxisLabel.add("May");
            xAxisLabel.add("June");
            xAxisLabel.add("July");
            xAxisLabel.add("August");
            xAxisLabel.add("September");
            xAxisLabel.add("October");
            xAxisLabel.add("November");
            xAxisLabel.add("December");

            xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabel) {
                public String getFormattedValue(int value) {
                    return xAxisLabel.get(value);
                }
            });
            xAxis.setLabelRotationAngle(-45);
            YAxis rightAxis = barchart.getAxisRight();
            rightAxis.setEnabled(false);
            YAxis yLeftAxis = barchart.getAxisLeft();
            yLeftAxis.setDrawGridLines(false);
            Legend legend = barchart.getLegend();
            legend.setEnabled(false);
            barchart.animateXY(3000, 3000);




        barchart1=findViewById(R.id.barChart1);
        barentries1=new ArrayList<>();
        barentries1 = db.getBarEntries_noPages(value_name);
        BarDataSet barDataSet1 = new BarDataSet(barentries1, "");
        barDataSet1.setColors(ColorTemplate.LIBERTY_COLORS);
        barDataSet1.setValueTextSize(10f);

        BarData barData1 = new BarData(barDataSet1);
        barData1.setBarWidth(0.7f);



        barchart1.setData(barData1);

        barchart1.setBackgroundColor(Color.TRANSPARENT);

        Description description1 = barchart1.getDescription();
        description1.setEnabled(true);
        description1.setPosition(890, 50);
        description1.setText("Number of days spent readind per month");
        description1.setTextSize(15f);




        XAxis xAxis1 = barchart1.getXAxis();
        xAxis1.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis1.setGranularity(1f);
        xAxis1.setDrawGridLines(false);

        final ArrayList<String> xAxisLabel1 = new ArrayList<>();
        xAxisLabel1.add("start");
        xAxisLabel1.add("January");
        xAxisLabel1.add("February");
        xAxisLabel1.add("March");
        xAxisLabel1.add("April");
        xAxisLabel1.add("May");
        xAxisLabel1.add("June");
        xAxisLabel1.add("July");
        xAxisLabel1.add("August");
        xAxisLabel1.add("September");
        xAxisLabel1.add("October");
        xAxisLabel1.add("November");
        xAxisLabel1.add("December");

        xAxis1.setValueFormatter(new IndexAxisValueFormatter(xAxisLabel1) {
            public String getFormattedValue(int value) {
                return xAxisLabel1.get(value);
            }
        });
        xAxis1.setLabelRotationAngle(-45);
        YAxis rightAxis1 = barchart1.getAxisRight();
        rightAxis1.setEnabled(false);
        YAxis yLeftAxis1 = barchart1.getAxisLeft();
        yLeftAxis1.setDrawGridLines(false);
        Legend legend1 = barchart1.getLegend();
        legend1.setEnabled(false);
        barchart1.animateXY(3000, 3000);







            if(barentries.size()==0 ){

                messagee.setText("There are not statistics for you. You have no books added in the list. ");
            }


    }


    private class MyValueFormatter extends ValueFormatter {
        private DecimalFormat mFormat;

        public MyValueFormatter() {
            mFormat = new DecimalFormat("###,###,##0");
        }

        @Override

        public String getFormattedValue(float value) {

            return mFormat.format(value);
        }
    }
}