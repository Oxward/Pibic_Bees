package andersonfds.pibic.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import andersonfds.pibic.R;

public class ChartsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
        BarChart chart = findViewById(R.id.chart);

        BarData data = new BarData(getDataSet(), getDataSet2());
        chart.setData(data);
        Description d = new Description();
        d.setText("My Chart");
        chart.setDescription(d);
        chart.animateXY(2000, 2000);
    }

    private BarDataSet getDataSet() {

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(1f, 0.5f); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(2f, 1.0f); // Feb
        valueSet1.add(v1e2);
        BarEntry v2e3 = new BarEntry(3f, 2.0f); // Feb
        valueSet1.add(v2e3);
        BarEntry v2e4 = new BarEntry(4f, 2.0f); // Feb
        valueSet1.add(v2e4);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
        barDataSet1.setColor(Color.rgb(0, 155, 0));

        return barDataSet1;
    }

    private BarDataSet getDataSet2() {

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(5f, 1.5f); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(6f, 2.0f); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(7f, 2.0f); // Feb
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(8f, 2.0f); // Feb
        valueSet2.add(v2e4);

        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Brand 2");
        barDataSet2.setColor(Color.rgb(255, 0, 0));

        return barDataSet2;
    }
}
