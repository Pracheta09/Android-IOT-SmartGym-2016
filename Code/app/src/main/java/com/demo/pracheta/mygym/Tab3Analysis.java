package com.demo.pracheta.mygym;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Tab3Analysis extends Fragment {

    // Count = 0 on day with no activity
    int calories[] = {9,7,15,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("dd-mm");
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String nowDate = formatter.format(c.getTime());
    String[] separateCurrentDate = nowDate.split("-");
    String timeStamp1 = separateCurrentDate[1];
    String timeStamp = separateCurrentDate[2];
    int num1 = Integer.parseInt(timeStamp1);
    int num = Integer.parseInt(timeStamp);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        GymDatabase myDb = new GymDatabase(getContext());
        try {
            myDb.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String all_calories = myDb.show_calories();
        String parts[] = all_calories.split(",");
       /* for(int i=0;i<parts.length;i++) {
            calories[i] =Integer.parseInt(parts[i]);
        }*/

        int month = Integer.parseInt(parts[parts.length-1]);
        View rootView = inflater.inflate(R.layout.tab3_analysis, container, false);
        BarChart chart = (BarChart) rootView.findViewById(R.id.bargraph);
        BarData data = new BarData(getXAxisValues(), getDataSet());
        if(num1== month ) {
            chart.setData(data);
        }
        chart.animateXY(2000, 2000);
        chart.setDescription("");
        chart.setDrawGridBackground(false);

        YAxis yAxis = chart.getAxisRight();
        yAxis.setDrawGridLines(false);
        yAxis.setEnabled(false);
        yAxis = chart.getAxisLeft();
        yAxis.setTextColor(Color.rgb(255,170,0));

        XAxis xAxis = chart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(Color.rgb(255,170,0));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setSpaceBetweenLabels(0);
        chart.invalidate();

        return rootView;
    }
    //int steps[]=new int[31];


    public ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        int i;
        for(i=0;i<num;i++)
        {
            BarEntry v1e1 = new BarEntry(calories[i], i); // 1
            valueSet1.add(v1e1);
        }
        BarEntry v1e1 = new BarEntry(Float.parseFloat(Tab1ChooseActivity.con), num-1); // 1

        valueSet1.add(v1e1);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "calories per Day");
        barDataSet1.setColor(Color.rgb(255,0,0));
        //barDataSet1.setColors(new int[] {Color.rgb(255,0,0),Color.rgb(0,255,0)}, getContext());
        barDataSet1.setDrawValues(false);
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    public ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        if(num1==1||num1==3||num1==5||num1==7||num1==8||num1==10||num1==12) {
            xAxis.add("1");
            xAxis.add("2");
            xAxis.add("3");
            xAxis.add("4");
            xAxis.add("5");
            xAxis.add("6");
            xAxis.add("7");
            xAxis.add("8");
            xAxis.add("9");
            xAxis.add("10");
            xAxis.add("11");
            xAxis.add("12");
            xAxis.add("13");
            xAxis.add("14");
            xAxis.add("15");
            xAxis.add("16");
            xAxis.add("17");
            xAxis.add("18");
            xAxis.add("19");
            xAxis.add("20");
            xAxis.add("21");
            xAxis.add("22");
            xAxis.add("23");
            xAxis.add("24");
            xAxis.add("25");
            xAxis.add("26");
            xAxis.add("27");
            xAxis.add("28");
            xAxis.add("29");
            xAxis.add("30");
            xAxis.add("31");
        }
        else if(num1==2)
        {
            xAxis.add("1");
            xAxis.add("2");
            xAxis.add("3");
            xAxis.add("4");
            xAxis.add("5");
            xAxis.add("6");
            xAxis.add("7");
            xAxis.add("8");
            xAxis.add("9");
            xAxis.add("10");
            xAxis.add("11");
            xAxis.add("12");
            xAxis.add("13");
            xAxis.add("14");
            xAxis.add("15");
            xAxis.add("16");
            xAxis.add("17");
            xAxis.add("18");
            xAxis.add("19");
            xAxis.add("20");
            xAxis.add("21");
            xAxis.add("22");
            xAxis.add("23");
            xAxis.add("24");
            xAxis.add("25");
            xAxis.add("26");
            xAxis.add("27");
            xAxis.add("28");
            xAxis.add("29");
        }

        else
        {
            xAxis.add("1");
            xAxis.add("2");
            xAxis.add("3");
            xAxis.add("4");
            xAxis.add("5");
            xAxis.add("6");
            xAxis.add("7");
            xAxis.add("8");
            xAxis.add("9");
            xAxis.add("10");
            xAxis.add("11");
            xAxis.add("12");
            xAxis.add("13");
            xAxis.add("14");
            xAxis.add("15");
            xAxis.add("16");
            xAxis.add("17");
            xAxis.add("18");
            xAxis.add("19");
            xAxis.add("20");
            xAxis.add("21");
            xAxis.add("22");
            xAxis.add("23");
            xAxis.add("24");
            xAxis.add("25");
            xAxis.add("26");
            xAxis.add("27");
            xAxis.add("28");
            xAxis.add("29");
            xAxis.add("30");
        }
        return xAxis;
    }

}
