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
import com.example.model.Category;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {

    Activity context;
    int item_layout;
    ArrayList<Category> listCategory;

    //    Hàm khởi tạo adapter
    public CategoryAdapter(Activity context, int item_layout, ArrayList<Category> listCategory) {
        this.context = context;
        this.item_layout = item_layout;
        this.listCategory = listCategory;
    }

    @Override
    public int getCount() {
        return listCategory.size();
    }

    @Override
    public Object getItem(int i) {
        return listCategory.get(i);
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
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);

            // link view
            holder.imvCategoryThumb = view.findViewById(R.id.imvCategoryThumb);
            holder.txtCategoryName = view.findViewById(R.id.txtCategoryName);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Binding
        Category category = listCategory.get(i);
        holder.imvCategoryThumb.setImageResource(category.getImage());
        holder.txtCategoryName.setText(category.getName());

        return view;
    }

    public class ViewHolder {
        ImageView imvCategoryThumb;
        TextView txtCategoryName;
    }
}
