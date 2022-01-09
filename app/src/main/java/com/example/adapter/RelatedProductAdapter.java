package com.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid.R;
import com.example.handler.MyHander;
import com.example.model.Mobile;

import java.util.ArrayList;


public class RelatedProductAdapter extends RecyclerView.Adapter<RelatedProductAdapter.ViewHolder> {

    ArrayList<Mobile> listMobile;
    Context context;

    //    Hàm khởi tạo adapter
    public RelatedProductAdapter(ArrayList<Mobile> listMobile, Context context) {
        this.listMobile = listMobile;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View customItemView = inflater.inflate(R.layout.item_related_product, parent, false);
        return new ViewHolder(customItemView);
    }

    //    hàm binding view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imvImageRelated.setImageResource(listMobile.get(position).getImageThumb());
        holder.txtNameRelated.setText(listMobile.get(position).getName());
        holder.txtPriceRelated.setText(MyHander.formatPrice(String.format("%.0f", listMobile.get(position).getPrice())) + " VNĐ");

    }

    @Override
    public int getItemCount() {
        return listMobile.size();
    }

    //    view holder recycleview
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvImageRelated;
        TextView txtNameRelated, txtPriceRelated;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imvImageRelated = itemView.findViewById(R.id.imvImageRelated);
            this.txtNameRelated = itemView.findViewById(R.id.txtNameRelated);
            this.txtPriceRelated = itemView.findViewById(R.id.txtPriceRelated);
        }
    }
}
