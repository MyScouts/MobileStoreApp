package com.example.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.doanandroid.HistoryActivity;
import com.example.doanandroid.R;
import com.example.handler.MyHander;
import com.example.model.Order;

import java.util.ArrayList;

public class HistoryAdapter extends BaseAdapter {
    private HistoryActivity context;
    private int item_history;
    private ArrayList<Order> listOrder;

    //    Hàm Khởi tạo Adapter
    public HistoryAdapter(HistoryActivity context, int item_history, ArrayList<Order> listOrder) {
        this.context = context;
        this.item_history = item_history;
        this.listOrder = listOrder;
    }

    public HistoryAdapter() {
    }

    @Override
    public int getCount() {
        return listOrder.size();
    }

    @Override
    public Object getItem(int i) {
        return listOrder.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    //    Hàm khởi tạo view
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new HistoryAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_history, null);

            holder.txtNameHistory = view.findViewById(R.id.txtNameHistory);
            holder.txtPriceHistory = view.findViewById(R.id.txtPriceHistory);
            holder.txtNumHistory = view.findViewById(R.id.txtNumHistory);
            view.setTag(holder);
        } else {
            holder = (HistoryAdapter.ViewHolder) view.getTag();
        }

        Order order = listOrder.get(i);
        holder.txtNameHistory.setText(order.getMobileName());
        holder.txtNumHistory.setText("SL: " + order.getNum() + "");
        holder.txtPriceHistory.setText(MyHander.formatPrice(String.format("%.0f", order.getPrice())) + " VNĐ");
        return view;
    }

    public class ViewHolder {
        TextView txtNameHistory, txtPriceHistory, txtNumHistory;
    }
}