package com.example.doanandroid;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.database.AccountDatabase;

public class IntroActivity extends AppCompatActivity {

    Button btnRegisterIntro, btnLoginIntro;

    AccountDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        linkViews();
        prepareDB();
        addEvents();
    }

    private void linkViews() {
        btnRegisterIntro = findViewById(R.id.btnRegisterIntro);
        btnLoginIntro = findViewById(R.id.btnLoginIntro);
    }

    private void prepareDB() {
        db = new AccountDatabase(this);
        db.createSomeDefaultAccount();
    }


    private void addEvents() {
        btnRegisterIntro.setOnClickListener(view -> {
            Intent intent = new Intent(IntroActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        btnLoginIntro.setOnClickListener(view -> {
            Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}