package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.database.AccountDatabase;

public class LoginActivity extends AppCompatActivity {


    ImageButton ibBackLogin;
    EditText edtEmailLogin,edtPassLogin;
    Button btnLogin;

    AccountDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        linkView();
        getData();
        addEvents();
    }

    private void getData() {
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String pass = intent.getStringExtra("pass");
        edtEmailLogin.setText(email);
        edtPassLogin.setText(pass);
    }
    private void addEvents() {
        btnLogin.setOnClickListener(view -> {
            String email = edtEmailLogin.getText().toString();
            String password = edtPassLogin.getText().toString();
            db = new AccountDatabase(LoginActivity.this);
            Cursor cursor = db.getData("SELECT * FROM " + AccountDatabase.TABLE_NAME + " WHERE Email = '" + email + "' AND Password = '" + password + "'");

            // get data account
            String displayName = "";
            int accountId = 0;
            while (cursor.moveToNext()){
                displayName = cursor.getString(3);
                accountId = cursor.getInt(0);
            }

            if(cursor.getCount() == 0){
                Toast.makeText(LoginActivity.this, "Mật khẩu hoặc tài khoản không đúng!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("emailLogin", email);
                intent.putExtra("passLogin", password);
                intent.putExtra("displayNameLogin", displayName);
                intent.putExtra("accountId", accountId);
                startActivity(intent);
            }
        });
        ibBackLogin.setOnClickListener(view -> finish());
    }

    private void linkView() {
        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtPassLogin = findViewById(R.id.edtPassLogin);
        btnLogin = findViewById(R.id.btnLogin);
        ibBackLogin = findViewById(R.id.ibBackLogin);
    }

}