package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.CartAdapter;
import com.example.database.CartDatabase;
import com.example.database.OrderDatabase;
import com.example.handler.MyHander;
import com.example.model.Cart;
import com.example.model.Mobile;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {


    ImageButton ibBack;
    Button btnOrder;
    ListView lvCartList;
    TextView txtTotalPriceCart, txtNumProduct;

    ArrayList<Cart> listCart;
    CartAdapter adapter;
    CartDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        linkViews();
        prepareDB();
        loadData();
        addEvents();
    }

    //    hàm khởi tạo
    private void prepareDB() {
        db = new CartDatabase(CartActivity.this);
        db.createSomeDefaultProduct();
    }

    //    hàm implement view
    private void linkViews() {
        ibBack = findViewById(R.id.imvbtnBack);
        btnOrder = findViewById(R.id.btnOrder);
        lvCartList = findViewById(R.id.lvCartList);
        txtTotalPriceCart = findViewById(R.id.txtTotalPriceCart);
        txtNumProduct = findViewById(R.id.txtNumProduct);
    }

    private void addEvents() {

//        bắt sự kiện khi nhấn nút order
        btnOrder.setOnClickListener(view -> {
            Cursor cursor = db.getData("SELECT * FROM " + CartDatabase.TABLE_NAME);
            if (cursor.getCount() == 0) {
                Toast.makeText(CartActivity.this, "Giỏ hàng trống rỗng!", Toast.LENGTH_SHORT).show();
            } else {
                Dialog dialogOder = new Dialog(CartActivity.this);
                dialogOder.setContentView(R.layout.dialog_payed);
                // link view to odercart
                Button btnSendRequest = dialogOder.findViewById(R.id.btnSendRequest);
                Button btnCancelDeleteCart = dialogOder.findViewById(R.id.btnCancelOder);
                //
                EditText edtFullName, edtAddress, edtPhone;
                Button btnCancelOder;
                edtFullName = dialogOder.findViewById(R.id.edtFullName);
                edtAddress = dialogOder.findViewById(R.id.edtAddress);
                edtPhone = dialogOder.findViewById(R.id.edtPhone);
                btnCancelOder = dialogOder.findViewById(R.id.btnCancelOder);
                btnSendRequest = dialogOder.findViewById(R.id.btnSendRequest);

//                bắt sự kiện khi nhấn nút xác nhận order
                btnSendRequest.setOnClickListener(view12 -> {
                    String fullName = edtFullName.getText().toString();
                    String address = edtAddress.getText().toString();
                    String phone = edtPhone.getText().toString();
                    if (fullName.equals("") || address.equals("") || phone.equals("")) {
                        Toast.makeText(CartActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                    } else {
                        OrderDatabase oderDb = new OrderDatabase(CartActivity.this);
                        for (Cart item : listCart) {
                            oderDb.queryData("INSERT INTO "
                                    + OrderDatabase.TABLE_NAME
                                    + " VALUES(null, "
                                    + MainActivity.accountId + ", "
                                    + item.getMobileId() + ", '"
                                    + item.getMobileName() + "', "
                                    + item.getPrice() + ", "
                                    + item.getNum()
                                    + ")");
                        }
                        db.queryData("DELETE FROM Cart");
                        loadData();
                        Toast.makeText(CartActivity.this, "Đặt hàng thành công", Toast.LENGTH_LONG).show();
                        dialogOder.dismiss();
                    }

                });
                btnCancelOder.setOnClickListener(view1 -> dialogOder.dismiss());
                dialogOder.setCanceledOnTouchOutside(false);
                dialogOder.show();
            }


        });

//        đóng màn hình
        ibBack.setOnClickListener(view -> finish());

    }

    //    load data cart
    private void loadData() {
        listCart = new ArrayList<>();
        Cursor cursor = db.getData("SELECT * FROM " + CartDatabase.TABLE_NAME + " WHERE AccountId = " + MainActivity.accountId);
        listCart.clear();

        double totalPriceCart = 0;
        while (cursor.moveToNext()) {
            listCart.add(new Cart(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getDouble(5),
                    cursor.getInt(6)
            ));
            totalPriceCart += cursor.getDouble(5) * cursor.getInt(6);
        }
        txtNumProduct.setText("Hiện có " + listCart.size() + " sản phẩm");
        txtTotalPriceCart.setText("Tổng tiền: " + MyHander.formatPrice(String.format("%.0f", totalPriceCart)) + " VNĐ");
        adapter = new CartAdapter(CartActivity.this, R.layout.item_cart, listCart);
        lvCartList.setAdapter(adapter);
    }


    //    bắt sự kiện khi nhấn vào nút xóa
    public void deleteProductInCart(Cart itemDelete) {
        Dialog dialogConfirmDelete = new Dialog(CartActivity.this);
        dialogConfirmDelete.setContentView(R.layout.dialog_confirm_delete);

        // link view => dialog_confirm_delete
        Button btnOkDeleteCart = dialogConfirmDelete.findViewById(R.id.btnOkDeleteCart);
        Button btnCancelDeleteCart = dialogConfirmDelete.findViewById(R.id.btnCancelDeleteCart);

        // events
        // delete
        btnOkDeleteCart.setOnClickListener(view -> {
            db.queryData("DELETE FROM Cart WHERE CartId = " + itemDelete.getCartId());
            dialogConfirmDelete.dismiss();
            loadData();
        });

        // cancel
        btnCancelDeleteCart.setOnClickListener(view -> dialogConfirmDelete.dismiss());

        // show dialog
        dialogConfirmDelete.setCanceledOnTouchOutside(false);
        dialogConfirmDelete.show();
    }

    public void editNumProductInCart(Cart itemEdit) {
        Dialog dialogEditNum = new Dialog(CartActivity.this);
        dialogEditNum.setContentView(R.layout.dialog_edit_num_cart);
        // link view => dialog_confirm_delete
        Button btnUpdate = dialogEditNum.findViewById(R.id.btnUpdateNumInCart);
        Button btnCancel = dialogEditNum.findViewById(R.id.btnCancelEditInCart);
        EditText edtNumEditDiaLogInCart = dialogEditNum.findViewById(R.id.edtNumEditDiaLogInCart);

        edtNumEditDiaLogInCart.setText(String.valueOf(itemEdit.getNum()));
        btnUpdate.setOnClickListener(view -> {
            int numUpdate = Integer.parseInt(edtNumEditDiaLogInCart.getText().toString());
            if (numUpdate <= 0) {
                Toast.makeText(CartActivity.this, "Số lương không hợp lệ!", Toast.LENGTH_SHORT).show();
            } else {
                db.queryData("UPDATE " + CartDatabase.TABLE_NAME + " SET "
                        + CartDatabase.COL_NUM + " = " + numUpdate
                        + " WHERE "
                        + CartDatabase.COL_CART_ID + " = " + itemEdit.getCartId());

                dialogEditNum.dismiss();
                loadData();
            }
        });

        btnCancel.setOnClickListener(view -> dialogEditNum.dismiss());

        // show dialog
        dialogEditNum.setCanceledOnTouchOutside(false);
        dialogEditNum.show();
    }
}