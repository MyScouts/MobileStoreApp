package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageButton;

import com.example.adapter.CategoryAdapter;
import com.example.model.Category;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    GridView gvCategory;
    CategoryAdapter adapter;
    ArrayList<Category> listCategory;
    ImageButton ibBackCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        linkViews();
        loadData();
        addEvents();
    }

    private void loadData() {
        listCategory = new ArrayList<>();
        listCategory.add(new Category(R.drawable.iphone_logo, "Iphone"));
        listCategory.add(new Category(R.drawable.samssung_logo, "Samsung"));
        listCategory.add(new Category(R.drawable.oppo_logo, "Oppo"));
        listCategory.add(new Category(R.drawable.xiaomi_logo, "Xiaomi"));

        adapter = new CategoryAdapter(CategoryActivity.this, R.layout.item_category, listCategory);
        gvCategory.setAdapter(adapter);
    }

    private void linkViews() {
        gvCategory = findViewById(R.id.gvCategory);
        ibBackCategory = findViewById(R.id.ibBackCategory);
    }

    private void addEvents() {
        gvCategory.setOnItemClickListener((adapterView, view, i, l) -> {
            Category category = listCategory.get(i);
            MainActivity.mobileBrand = category.getName();
            finish();
        });
        ibBackCategory.setOnClickListener(view -> finish());
    }
}