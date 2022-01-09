package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.database.AccountDatabase;

public class RegisterActivity extends AppCompatActivity {

    ImageButton imvbtnBackRegiger;
    EditText edtFullName, edtEmailRegister, edtPassRegister;
    Button btnRegister;
    AccountDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        linkViews();
        addEvents();
    }

    private void linkViews() {
        imvbtnBackRegiger = findViewById(R.id.imvbtnBackRegister);
        edtEmailRegister = findViewById(R.id.edtEmailRegister);
        edtPassRegister = findViewById(R.id.edtPassRegister);
        edtFullName = findViewById(R.id.edtFullName);
        btnRegister = findViewById(R.id.btnRegister);
    }

    private void addEvents() {
        imvbtnBackRegiger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //B1 check email tồn tài
                //B2 lưu dữ liệu
                String email = edtEmailRegister.getText().toString();
                String password = edtPassRegister.getText().toString();
                String displayName = edtFullName.getText().toString();
                if (email.equals("") || password.equals("") || displayName.equals("")) {

                    Toast.makeText(RegisterActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    db = new AccountDatabase(RegisterActivity.this);
                    db.queryData("INSERT INTO "
                            + AccountDatabase.TABLE_NAME
                            + " VALUES(null, '"
                            + email + "', '"
                            + password + "', '"
                            + displayName
                            + "')");
                    Toast.makeText(RegisterActivity.this, "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.putExtra("email", email);
                    intent.putExtra("pass", password);

                    startActivity(intent);

                }
            }
        });
    }
//
}