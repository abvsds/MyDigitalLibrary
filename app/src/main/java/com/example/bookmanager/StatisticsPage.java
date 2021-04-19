package com.example.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;

public class StatisticsPage extends AppCompatActivity {
BarChart barchart;
SqliteDB db;
ArrayList<BarEntry> barentries;
TextView messagee;

PieChart pieChart;
ArrayList<PieEntry> pieentries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_page);
        String value_name = getIntent().getStringExtra("Username");
        messagee= findViewById(R.id.message2);
        db = new SqliteDB(this);
        barchart=findViewById(R.id.barChart);
        barentries=new ArrayList<>();
        barentries = db.getBarEntries(value_name);
        pieChart=findViewById(R.id.pieChart);
        // pieChart.setUsePercentValues(true);
        pieentries=new ArrayList<>();
        pieentries = db.getPieEntries(value_name);

        if(barentries.size()==0 || pieentries.size()==0){
            messagee.setText("There are not statistics for you. You have no books added in the two list. ");
        }
        else {
            
            BarDataSet barDataSet = new BarDataSet(barentries, "");
            barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
            barDataSet.setValueTextSize(10f);

            BarData barData = new BarData(barDataSet);
            barData.setBarWidth(0.7f);
            barData.setValueFormatter(new MyValueFormatter());


            barchart.setData(barData);

            barchart.setBackgroundColor(Color.WHITE);

            Description description = barchart.getDescription();
            description.setEnabled(true);
            description.setPosition(850, 50);
            description.setText("Number of books read per month");
            description.setTextSize(15f);

            barchart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(StatisticsPage.this, "This chart show you how many books read on each month!", Toast.LENGTH_SHORT).show();
                }
            });


            XAxis xAxis = barchart.getXAxis();
            //change the position of x-axis to the bottom
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            //set the horizontal distance of the grid line
            xAxis.setGranularity(1f);
            xAxis.setDrawGridLines(false);
//String[] months ={"January","February", "March", "April","May", "June", "July","August","September", "October", "November", "December"};
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
                //   @Override
                public String getFormattedValue(int value) {
                    return xAxisLabel.get(value);
                }
            });
            xAxis.setLabelRotationAngle(-45);
            YAxis rightAxis = barchart.getAxisRight();
            //hiding the right y-axis line, default true if not set
            rightAxis.setEnabled(false);
            YAxis yLeftAxis = barchart.getAxisLeft();
            yLeftAxis.setDrawGridLines(false);

            Legend legend = barchart.getLegend();
            legend.setEnabled(false);
            barchart.animateXY(3000, 3000);


         //   db = new SqliteDB(this);


            //  pieentries.add(new PieEntry(34));
            //   pieentries.add(new PieEntry(54, "b"));
            // pieentries.add(new PieEntry(12, "c"));
            PieDataSet pieDataSet = new PieDataSet(pieentries, "");
            pieDataSet.setSliceSpace(3f);
            pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);
            pieDataSet.setValueTextSize(20f);

            PieData pieData = new PieData(pieDataSet);
            pieChart.setData(pieData);
            pieChart.setBackgroundColor(Color.WHITE);
            pieChart.setDrawSlicesUnderHole(false);
            pieChart.setDrawHoleEnabled(true);

            //   pieChart.animateXY(3000, 3000);
            Description description1 = pieChart.getDescription();
            description1.setEnabled(true);
            description1.setPosition(910, 50);
            description1.setText("Books percent read per month from all books");
            description1.setTextSize(15f);

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