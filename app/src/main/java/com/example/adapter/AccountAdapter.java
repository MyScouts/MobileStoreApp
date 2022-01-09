package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.doanandroid.LoginActivity;
import com.example.doanandroid.R;
import com.example.model.Account;

import java.util.ArrayList;

public class AccountAdapter extends BaseAdapter {

    private LoginActivity context;
    private int item_layout;
    private ArrayList<Account> listAccount;

    public AccountAdapter(LoginActivity context, int item_layout, ArrayList<Account> listAccount) {
        this.context = context;
        this.item_layout = item_layout;
        this.listAccount = listAccount;
    }


    @Override
    public int getCount() {
        return listAccount.size();
    }

    @Override
    public Object getItem(int i) {
        return listAccount.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);

            holder.edtEmailLogin = view.findViewById(R.id.edtEmailLogin);
            holder.edtPassLogin = view.findViewById(R.id.edtPassLogin);
            holder.btnLogin = view.findViewById(R.id.btnLogin);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Account account = listAccount.get(i);
        holder.edtEmailLogin.setText(account.getEmail());
        holder.edtPassLogin.setText(account.getPassword());


        return view;
    }

    public class ViewHolder {

        EditText edtEmailLogin, edtPassLogin;
        Button btnLogin, btnRegisterLogin;
    }
}