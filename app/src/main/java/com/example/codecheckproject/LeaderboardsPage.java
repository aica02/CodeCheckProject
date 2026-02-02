package com.example.codecheckproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class LeaderboardsPage extends AppCompatActivity {

    RecyclerView recyclerView;
    Button homeBtn, javaBtn, cssBtn, mysqlBtn;
    Spinner difficultySpinner;

    List<LeaderboardItem> allScores = new ArrayList<>();
    LeaderboardAdapter adapter;

    String selectedCategory = "Java";
    String selectedDifficulty = "All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards_page);

        recyclerView = findViewById(R.id.leaderboardRecycler);
        homeBtn = findViewById(R.id.homeBtn);
        javaBtn = findViewById(R.id.javaBtn);
        cssBtn = findViewById(R.id.cssBtn);
        mysqlBtn = findViewById(R.id.mysqlBtn);
        difficultySpinner = findViewById(R.id.difficultySpinner);

        allScores = loadLeaderboard();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LeaderboardAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                new String[]{"All", "Easy", "Medium", "Hard"}) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                view.setTextColor(getResources().getColor(android.R.color.white));
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView view = (TextView) super.getDropDownView(position, convertView, parent);
                view.setTextColor(getResources().getColor(android.R.color.white));
                view.setBackgroundColor(getResources().getColor(R.color.dark_green));
                return view;
            }
        };
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(spinnerAdapter);


        javaBtn.setOnClickListener(v -> { selectedCategory = "Java"; loadByFilters(); });
        cssBtn.setOnClickListener(v -> { selectedCategory = "CSS"; loadByFilters(); });
        mysqlBtn.setOnClickListener(v -> { selectedCategory = "MySQL"; loadByFilters(); });

        difficultySpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                selectedDifficulty = ((String) parent.getItemAtPosition(position)).toLowerCase();
                loadByFilters();
            }
            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });

        homeBtn.setOnClickListener(v -> {
            startActivity(new Intent(LeaderboardsPage.this, HomePage.class));
            finish();
        });

        loadByFilters();
    }

    void loadByFilters() {
        List<LeaderboardItem> filtered = new ArrayList<>();
        for (LeaderboardItem item : allScores) {
            String itemDiff = item.difficulty.toLowerCase();
            if (item.category.equals(selectedCategory) &&
                    (selectedDifficulty.equals("all") || itemDiff.equals(selectedDifficulty))) {
                filtered.add(item);
            }
        }

        Collections.sort(filtered, (a, b) -> {
            if (b.score != a.score) return b.score - a.score;
            return a.time - b.time;
        });

        adapter.update(filtered);
        updateCategoryButtonColors(selectedCategory);
    }

    void updateCategoryButtonColors(String selectedCategory) {
        int normalColor = getResources().getColor(R.color.green);
        int activeColor = getResources().getColor(R.color.dark_green);

        javaBtn.setBackgroundTintList(android.content.res.ColorStateList.valueOf(normalColor));
        cssBtn.setBackgroundTintList(android.content.res.ColorStateList.valueOf(normalColor));
        mysqlBtn.setBackgroundTintList(android.content.res.ColorStateList.valueOf(normalColor));

        switch (selectedCategory) {
            case "Java": javaBtn.setBackgroundTintList(android.content.res.ColorStateList.valueOf(activeColor)); break;
            case "CSS": cssBtn.setBackgroundTintList(android.content.res.ColorStateList.valueOf(activeColor)); break;
            case "MySQL": mysqlBtn.setBackgroundTintList(android.content.res.ColorStateList.valueOf(activeColor)); break;
        }
    }

    List<LeaderboardItem> loadLeaderboard() {
        SharedPreferences prefs = getSharedPreferences("leaderboard", MODE_PRIVATE);
        int size = prefs.getInt("size", 0);
        List<LeaderboardItem> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String difficulty = prefs.getString("difficulty_" + i, "easy");
            list.add(new LeaderboardItem(
                    prefs.getString("date_" + i, ""),
                    prefs.getInt("score_" + i, 0),
                    prefs.getInt("time_" + i, 0),
                    prefs.getString("category_" + i, "Java"),
                    difficulty.toLowerCase()
            ));
        }
        return list;
    }

    static class LeaderboardItem {
        String date, category, difficulty;
        int score, time;

        LeaderboardItem(String date, int score, int time, String category, String difficulty) {
            this.date = date;
            this.score = score;
            this.time = time;
            this.category = category;
            this.difficulty = difficulty;
        }
    }

    static class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {
        List<LeaderboardItem> list;
        LeaderboardAdapter(List<LeaderboardItem> list) { this.list = list; }
        void update(List<LeaderboardItem> newList) { list = newList; notifyDataSetChanged(); }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.leaderboard_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            LeaderboardItem item = list.get(position);
            holder.dateText.setText(item.date);
            holder.scoreText.setText(String.valueOf(item.score));
            holder.timeText.setText(item.time + "s");
            holder.difficultyText.setText(capitalize(item.difficulty));

            if (position % 2 == 0) {
                holder.itemContainer.setBackgroundColor(holder.itemView.getResources().getColor(R.color.light_green));
            } else {
                holder.itemContainer.setBackgroundColor(holder.itemView.getResources().getColor(R.color.dark_green));
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            LinearLayout itemContainer;
            TextView dateText, scoreText, timeText, difficultyText;

            ViewHolder(View itemView) {
                super(itemView);
                itemContainer = itemView.findViewById(R.id.itemContainer);
                dateText = itemView.findViewById(R.id.dateText);
                scoreText = itemView.findViewById(R.id.scoreText);
                timeText = itemView.findViewById(R.id.timeText);
                difficultyText = itemView.findViewById(R.id.difficultyText);
            }
        }

        private String capitalize(String str) {
            if (str == null || str.isEmpty()) return str;
            return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
        }
    }

}
