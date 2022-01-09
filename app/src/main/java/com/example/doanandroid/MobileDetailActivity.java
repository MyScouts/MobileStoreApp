package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.RelatedProductAdapter;
import com.example.database.CartDatabase;
import com.example.database.MobileDatabase;
import com.example.handler.MyHander;
import com.example.model.Mobile;

import java.util.ArrayList;

public class MobileDetailActivity extends AppCompatActivity {

    ImageButton imvbtnBack;
    Button btnAddToCart;
    ImageView imvThumbDetail;

    // related product
    RecyclerView rvRelatedProduct;
    RelatedProductAdapter relatedProductAdapter;

    TextView txtNameDetail,
            txtCpuDetail,
            txtRamDetail,
            txtCardDetail,
            txtPinDetail,
            txtScreenSizeDetail,
            txtPriceDetail,
            txtCategoryDetail;

    CartDatabase db = new CartDatabase(MobileDetailActivity.this);

    // get data
    private int mobileId, mobileImage;
    private String mobileName, mobileCategory, mobileScreenSize, mobileCpu, mobileStorage, mobileRam, mobilePin, mobileBrand;
    private double mobilePrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mobile);

        linkViews();
        getData();
        addEvents();
    }

    private void linkViews() {
        imvbtnBack = findViewById(R.id.imvbtnBack);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        imvThumbDetail = findViewById(R.id.imvThumbDetail);
        txtNameDetail = findViewById(R.id.txtNameDetail);
        txtCpuDetail = findViewById(R.id.txtCpuDetail);
        txtRamDetail = findViewById(R.id.txtRamDetail);
        txtCardDetail = findViewById(R.id.txtCartDetail);
        txtScreenSizeDetail = findViewById(R.id.txtScreenSizeDetail);
        txtPriceDetail = findViewById(R.id.txtPriceDetail);
        txtPinDetail = findViewById(R.id.txtPinDetail);
        txtCategoryDetail = findViewById(R.id.txtCategoryDetail);
        // related product
        rvRelatedProduct = findViewById(R.id.rvRelatedProduct);

    }

    private void addEvents() {
        imvbtnBack.setOnClickListener(view -> finish());

        btnAddToCart.setOnClickListener(view -> {
            CartDatabase cartDb = new CartDatabase(MobileDetailActivity.this);
            Cursor cursor = cartDb.getData("SELECT * FROM " + CartDatabase.TABLE_NAME + " WHERE MobileId = '" + mobileId + "'");
            if (cursor.getCount() > 0) {
                // đã tồn tại trong giỏ hàng
                Toast.makeText(MobileDetailActivity.this, "Đã tồn tại trong giỏ hàng!", Toast.LENGTH_SHORT).show();
            } else {
                // chưa tồn tại => add mới
                db.queryData("INSERT INTO "
                        + CartDatabase.TABLE_NAME
                        + " VALUES(null, "
                        + MainActivity.accountId + ", "
                        + mobileId + ", "
                        + mobileImage + ", '"
                        + mobileName + "', "
                        + mobilePrice + ", "
                        + 1 // sl mặc định khi thêm vào giỏ hàng = 1
                        + ")");
                Toast.makeText(MobileDetailActivity.this, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData() {
        Intent intent = getIntent();

        mobileId = intent.getIntExtra("id", 0);
        mobileImage = intent.getIntExtra("image", 0);
        mobileName = intent.getStringExtra("name");
        mobileScreenSize = intent.getStringExtra("screen_size");
        mobileCpu = intent.getStringExtra("cpu");
        mobileStorage = intent.getStringExtra("storage");
        mobileRam = intent.getStringExtra("ram");
        mobilePin = intent.getStringExtra("pin");
        mobileCategory = intent.getStringExtra("category");
        mobileBrand = intent.getStringExtra("brand");
        mobilePrice = intent.getDoubleExtra("price", 0);

        imvThumbDetail.setImageResource(mobileImage);
        txtNameDetail.setText(mobileName);
        txtScreenSizeDetail.setText("Màn hình: " + mobileScreenSize);
        txtCpuDetail.setText("Chip: " + mobileCpu);
        txtCardDetail.setText("Bộ nhớ: " + mobileStorage);
        txtRamDetail.setText("Ram: " + mobileRam);
        txtPinDetail.setText("Pin: " + mobilePin);
        txtPriceDetail.setText("Giá: " + MyHander.formatPrice(String.format("%.0f", mobilePrice)) + " vnđ");
        txtCategoryDetail.setText("Danh mục: " + mobileCategory);

        // related product
        getRelatedProduct();
    }


    public void getRelatedProduct() {
        MobileDatabase db = new MobileDatabase(MobileDetailActivity.this);
        ArrayList<Mobile> listRelatedProduct = new ArrayList<>();
        Cursor cursor = db.getData("SELECT * FROM " + MobileDatabase.TABLE_NAME + " WHERE MobileBrand = '" + mobileBrand + "'");
        while (cursor.moveToNext()) {
            if (cursor.getInt(0) != this.mobileId) {
                // loại trừ sản phẩm đang xem
                listRelatedProduct.add(new Mobile(
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
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvRelatedProduct.setLayoutManager(layoutManager);
        relatedProductAdapter = new RelatedProductAdapter(listRelatedProduct, getApplicationContext());
        rvRelatedProduct.setAdapter(relatedProductAdapter);
    }
}