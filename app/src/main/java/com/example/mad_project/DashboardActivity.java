package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private LineChart portfolioChart;
    private ChipGroup timeFilterGroup;
    private MaterialButton watchlistButton;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize views
        portfolioChart = findViewById(R.id.portfolioChart);
        timeFilterGroup = findViewById(R.id.timeFilterGroup);
        watchlistButton = findViewById(R.id.watchlistButton);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        // Setup chart
        setupChart();

        // Setup time filter chips
        setupTimeFilters();

        // Handle watchlist button click
        watchlistButton.setOnClickListener(v -> {
            startActivity(new Intent(this, WatchlistActivity.class));
        });

        // Setup bottom navigation
        setupBottomNavigation();
    }

    private void setupChart() {
        // Sample data
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 180000));
        entries.add(new Entry(1, 185000));
        entries.add(new Entry(2, 190000));
        entries.add(new Entry(3, 188000));
        entries.add(new Entry(4, 195000));
        entries.add(new Entry(5, 203557));

        LineDataSet dataSet = new LineDataSet(entries, "Portfolio Value");
        dataSet.setColor(getResources().getColor(R.color.purple_primary));
        dataSet.setLineWidth(2f);
        dataSet.setDrawCircles(false);
        dataSet.setDrawValues(false);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        LineData lineData = new LineData(dataSet);
        portfolioChart.setData(lineData);
        portfolioChart.getDescription().setEnabled(false);
        portfolioChart.getLegend().setEnabled(false);
        portfolioChart.setDrawGridBackground(false);
        portfolioChart.setDrawBorders(false);
        portfolioChart.getAxisLeft().setDrawGridLines(false);
        portfolioChart.getAxisRight().setEnabled(false);
        portfolioChart.getXAxis().setDrawGridLines(false);
        portfolioChart.getXAxis().setDrawAxisLine(false);
        portfolioChart.getAxisLeft().setDrawAxisLine(false);
        portfolioChart.getAxisLeft().setTextColor(getResources().getColor(R.color.white));
        portfolioChart.animateX(1000);
        portfolioChart.invalidate();
    }

    private void setupTimeFilters() {
        timeFilterGroup.setOnCheckedChangeListener((group, checkedId) -> {
            // Handle time filter changes
            // TODO: Update chart data based on selected time period
        });
    }

    private void setupBottomNavigation() {
        bottomNavigation.setSelectedItemId(R.id.navigation_home); // Set Home as selected
        
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                return true;
            } else if (itemId == R.id.navigation_explore) {
                startActivity(new Intent(this, ExploreActivity.class));
                return true;
            } else if (itemId == R.id.navigation_portfolio) {
                startActivity(new Intent(this, PortfolioActivity.class));
                return true;
            } else if (itemId == R.id.navigation_news) {
                // TODO: Navigate to News
                return true;
            }
            return false;
        });
    }
} 