package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.AccountDatabase;

public class AccountActivity extends AppCompatActivity {

    TextView txtEmailAccount, txtPassAccount, txtDisplayNameAccount;
    Button btnChangeYourInfo;
    ImageButton imvBackAccountNow;

    private String emailLogin = null;
    private String passLogin = null;
    private String displayNameLogin = null;
    private int accountId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acount);

        linkViews();
        getDataAccount();
        addEvents();
    }

    //    hàm initial view
    private void linkViews() {
        btnChangeYourInfo = findViewById(R.id.btnChangeYourInfo);
        imvBackAccountNow = findViewById(R.id.imvBackAccount);
        txtEmailAccount = findViewById(R.id.txtEmailAccount);
        txtPassAccount = findViewById(R.id.txtPassAccount);
        txtDisplayNameAccount = findViewById(R.id.txtDisplayNameAccount);
    }

    // hàm get & set account data vào view
    private void getDataAccount() {
        emailLogin = MainActivity.emailLogin;
        passLogin = MainActivity.passLogin;
        displayNameLogin = MainActivity.displayNameLogin;
        accountId = MainActivity.accountId;
        txtEmailAccount.setText("Email: " + emailLogin);
        txtPassAccount.setText("Mật khẩu: " + passLogin);
        txtDisplayNameAccount.setText("Tên hiển thị: " + displayNameLogin);
    }

    private void addEvents() {

        //Mở dialog update account
        btnChangeYourInfo.setOnClickListener(view -> {
            Dialog dialogEditAccount = new Dialog(AccountActivity.this);
            dialogEditAccount.setContentView(R.layout.dialog_edit_account);
            // link view => dialog_confirm_delete
            EditText edtEmailAccount = dialogEditAccount.findViewById(R.id.edtEmailAccount);
            EditText edtPasswordAccount = dialogEditAccount.findViewById(R.id.edtPasswordAccount);
            EditText edtDisplayNameAccount = dialogEditAccount.findViewById(R.id.edtDisplayNameAccount);
            Button btnUpdateAccount = dialogEditAccount.findViewById(R.id.btnUpdateAccount);
            Button btnCancelAccount = dialogEditAccount.findViewById(R.id.btnCancelAccount);
            // load data account
            edtEmailAccount.setText(emailLogin);
            edtPasswordAccount.setText(passLogin);
            edtDisplayNameAccount.setText(displayNameLogin);

            // bắt sự kiện update khi click vào nut update button
            btnUpdateAccount.setOnClickListener(view1 -> {
                String emailUpdate = edtEmailAccount.getText().toString();
                String passUpdate = edtPasswordAccount.getText().toString();
                String displayNameUpdate = edtDisplayNameAccount.getText().toString();
                AccountDatabase db = new AccountDatabase(AccountActivity.this);
                db.queryData("UPDATE " + AccountDatabase.TABLE_NAME + " SET "
                        + AccountDatabase.COL_EMAIL + " = '" + emailUpdate + "', "
                        + AccountDatabase.COL_PASSWORD + " = '" + passUpdate + "', "
                        + AccountDatabase.COL_DISPLAY_NAME + " = '" + displayNameUpdate + "'"
                        + " WHERE "
                        + AccountDatabase.COL_ACCOUNT_ID + " = " + accountId);
                // reset data login
                txtEmailAccount.setText("Email: " + emailUpdate);
                txtPassAccount.setText("Mật khẩu: " + passUpdate);
                txtDisplayNameAccount.setText("Tên hiển thị: " + displayNameUpdate);
                Toast.makeText(AccountActivity.this, "Cập nhật tài khoản thành công!", Toast.LENGTH_SHORT).show();
                dialogEditAccount.dismiss();
            });

//            cancle update
            btnCancelAccount.setOnClickListener(view12 -> dialogEditAccount.dismiss());

            dialogEditAccount.show();
            dialogEditAccount.setCanceledOnTouchOutside(false);

        });
//        close
        imvBackAccountNow.setOnClickListener(view -> finish());
    }
}
