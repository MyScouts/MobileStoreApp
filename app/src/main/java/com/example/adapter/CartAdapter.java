package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanandroid.CartActivity;
import com.example.doanandroid.R;
import com.example.handler.MyHander;
import com.example.model.Cart;

import java.util.ArrayList;


public class CartAdapter extends BaseAdapter {
    private CartActivity context;
    private int item_layout;
    private ArrayList<Cart> listCart;

    //    Hàm khởi tạo Addapter
    public CartAdapter(CartActivity context, int item_layout, ArrayList<Cart> listCart) {
        this.context = context;
        this.item_layout = item_layout;
        this.listCart = listCart;
    }

    @Override
    public int getCount() {
        return listCart.size();
    }

    @Override
    public Object getItem(int i) {
        return listCart.get(i);
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

            holder.imvMobile = view.findViewById(R.id.imvMobileCart);
            holder.txtName = view.findViewById(R.id.txtNameCart);
            holder.txtPrice = view.findViewById(R.id.txtPriceCart);
            holder.txtNum = view.findViewById(R.id.txtNumCart);
            holder.ibDelete = view.findViewById(R.id.ibDeleteCart);
            holder.ibEditNumCart = view.findViewById(R.id.ibEditNumCart);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Binding
        Cart cart = listCart.get(i);
        holder.imvMobile.setImageResource(cart.getImageThumb());
        holder.txtName.setText(cart.getMobileName());
        holder.txtPrice.setText(MyHander.formatPrice(String.format("%.0f", cart.getPrice())) + " VNĐ");
        holder.txtNum.setText("Số lượng: " + cart.getNum());
        // delete product in cart
        holder.ibDelete.setOnClickListener(view12 -> context.deleteProductInCart(cart));
        // edit num product in cart
        holder.ibEditNumCart.setOnClickListener(view1 -> context.editNumProductInCart(cart));

        return view;
    }

    public class ViewHolder {
        ImageView imvMobile;
        TextView txtName, txtPrice, txtNum;
        ImageButton ibDelete, ibEditNumCart;
    }
}
