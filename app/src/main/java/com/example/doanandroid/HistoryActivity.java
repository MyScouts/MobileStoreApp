package com.example.doanandroid;


import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adapter.HistoryAdapter;
import com.example.database.OrderDatabase;
import com.example.model.Order;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ListView lvHistory;
    HistoryAdapter adapter;
    ArrayList<Order> listOrder;
    OrderDatabase db;
    ImageButton ibBackHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        linkViews();
        loadData();
        addEvents();
    }

    private void linkViews() {
        lvHistory = findViewById(R.id.lvHistory);
        ibBackHistory = findViewById(R.id.ibBackHistory);

    }

    private void addEvents() {
        ibBackHistory.setOnClickListener(view -> finish());
    }

    private void loadData() {
        listOrder = new ArrayList<>();
        db = new OrderDatabase(HistoryActivity.this);
        Cursor cursor = db.getData("SELECT * FROM " + OrderDatabase.TABLE_NAME + " WHERE AccountId = " + MainActivity.accountId);
        listOrder.clear();
        while (cursor.moveToNext()) {
            listOrder.add(new Order(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getDouble(4),
                    cursor.getInt(5)
            ));
        }

        adapter = new HistoryAdapter(HistoryActivity.this, R.layout.item_history, listOrder);
        lvHistory.setAdapter(adapter);
    }
}