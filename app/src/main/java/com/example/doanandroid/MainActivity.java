package com.example.doanandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;

import com.example.adapter.MobileAdapter;
import com.example.database.MobileDatabase;
import com.example.model.Mobile;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gvMobile;
    ImageButton ibCart, ibSearch;
    EditText edtSearch;


    ArrayList<Mobile> listMobile;
    MobileAdapter adapter;
    MobileDatabase db;

    public static String emailLogin = null;
    public static String passLogin = null;
    public static String displayNameLogin = null;
    public static int accountId = 0;

    // get product by category
    public static String mobileBrand = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataLogin();
        linkViews();
        prepareDB();
        addEvents();
    }

    public void getDataLogin() {
        Intent intent = getIntent();
        emailLogin = intent.getStringExtra("emailLogin");
        passLogin = intent.getStringExtra("passLogin");
        displayNameLogin = intent.getStringExtra("displayNameLogin");
        accountId = intent.getIntExtra("accountId", 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void linkViews() {
        gvMobile = findViewById(R.id.gvMobile);
        ibCart = findViewById(R.id.imvbtnCart);
        ibSearch = findViewById(R.id.ibSearch);
        edtSearch = findViewById(R.id.edtSearch);
    }

    private void prepareDB() {
        db = new MobileDatabase(this);
        db.createSomeDefaultProduct();
    }

    private void loadData() {
        listMobile = new ArrayList<>();
        Cursor cursor;
        if (mobileBrand == null) {
            // người dùng chưa chọn danh mục
            cursor = db.getData("SELECT * FROM " + MobileDatabase.TABLE_NAME);
        } else {
            // người dùng chọn danh mục
            cursor = db.getData("SELECT * FROM " + MobileDatabase.TABLE_NAME + " WHERE MobileBrand LIKE '%" + mobileBrand + "%'");
        }

        listMobile.clear();
        while (cursor.moveToNext()) {
            listMobile.add(new Mobile(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getDouble(10)
            ));
        }

        adapter = new MobileAdapter(MainActivity.this, R.layout.item_row, listMobile);
        gvMobile.setAdapter(adapter);
    }

    private void addEvents() {
        gvMobile.setOnItemClickListener((adapterView, view, i, l) -> {
            Mobile mobile = (Mobile) adapter.getItem(i);
            Intent intent = new Intent(MainActivity.this, MobileDetailActivity.class);
            intent.putExtra("id", mobile.getMobileId());
            intent.putExtra("name", mobile.getName());
            intent.putExtra("image", mobile.getImageThumb());
            intent.putExtra("cpu", mobile.getCPU());
            intent.putExtra("storage", mobile.getStorage());
            intent.putExtra("ram", mobile.getRam());
            intent.putExtra("pin", mobile.getPin());
            intent.putExtra("category", mobile.getCategory());
            intent.putExtra("brand", mobile.getBrand());
            intent.putExtra("price", mobile.getPrice());
            intent.putExtra("screen_size", mobile.getScreenSize());
            startActivity(intent);
        });

        // Open cart
        ibCart.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });

        ibSearch.setOnClickListener(view -> {
            String search = edtSearch.getText().toString();
            ArrayList<Mobile> listResultSearch = new ArrayList<>();
            for (Mobile item : listMobile) {
                if (item.getName().toUpperCase().contains(search.toUpperCase()) ||
                        item.getBrand().toUpperCase().contains(search.toUpperCase()) ||
                        item.getCategory().toUpperCase().contains(search.toUpperCase()) ||
                        item.getCPU().toUpperCase().contains(search.toUpperCase()) ||
                        item.getPin().toUpperCase().contains(search.toUpperCase()) ||
                        item.getStorage().toUpperCase().contains(search.toUpperCase()) ||
                        item.getScreenSize().toUpperCase().contains(search.toUpperCase()) ||
                        item.getRam().toUpperCase().contains(search.toUpperCase())
                ) {
                    listResultSearch.add(item);
                }
            }
            adapter = new MobileAdapter(MainActivity.this, R.layout.item_row, listResultSearch);
            gvMobile.setAdapter(adapter);
        });
    }


    // Tạo menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Sự kiện menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnCategory) {
            // open activity for adding new product
            Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
            startActivity(intent);

        } else if (item.getItemId() == R.id.mnAccount) {
            Intent intent = new Intent(MainActivity.this, AccountActivity.class);
            startActivity(intent);

        } else if (item.getItemId() == R.id.mnAllProduct) {
            mobileBrand = null;
            loadData();
        } else if (item.getItemId() == R.id.mnHistory) {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.mnAccount) {
            Intent intent = new Intent(MainActivity.this, AccountActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.mnLogout) {
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

}