package com.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanandroid.R;

import com.example.handler.MyHander;
import com.example.model.Mobile;

import java.util.ArrayList;

public class MobileAdapter extends BaseAdapter {

    private Activity context;
    int item_layout;
    private ArrayList<Mobile> listMobile;

    //    Hàm khởi tạo adapter
    public MobileAdapter(Activity context, int item_layout, ArrayList<Mobile> listMobile) {
        this.context = context;
        this.item_layout = item_layout;
        this.listMobile = listMobile;
    }

    @Override
    public int getCount() {
        return listMobile.size();
    }

    @Override
    public Object getItem(int i) {
        return listMobile.get(i);
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
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);
            holder.imvImage = view.findViewById(R.id.imvImage);
            holder.txtName = view.findViewById(R.id.txtName);
            holder.txtScreen = view.findViewById(R.id.txtScreen);
            holder.txtCPU = view.findViewById(R.id.txtCPU);
            holder.txtRam = view.findViewById(R.id.txtRam);
            holder.txtPrice = view.findViewById(R.id.txtPrice);
            holder.txtCard = view.findViewById(R.id.txtCard);
            holder.txtCategory = view.findViewById(R.id.txtCategory);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Binding
        Mobile mobile = listMobile.get(i);

        holder.imvImage.setImageResource(mobile.getImageThumb());
        holder.txtCategory.setText(mobile.getCategory());
        holder.txtName.setText(mobile.getName());
        holder.txtScreen.setText(mobile.getScreenSize());
        holder.txtCPU.setText(mobile.getCPU());
        holder.txtRam.setText(mobile.getRam());
        holder.txtCard.setText(mobile.getStorage());
        holder.txtPrice.setText(MyHander.formatPrice(String.format("%.0f", mobile.getPrice())) + " VNĐ");

        return view;
    }


    public class ViewHolder {
        ImageView imvImage;
        TextView txtName, txtScreen, txtCPU, txtRam, txtPrice, txtCard, txtCategory;
    }
}
