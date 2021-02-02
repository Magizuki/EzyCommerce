package com.example.ezycommerce.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ezycommerce.DatabaseHelper;
import com.example.ezycommerce.databinding.ActivityBookCartListBinding;
import com.example.ezycommerce.databinding.CartListRowBinding;
import com.example.ezycommerce.model.Cart;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    private Context ctx;
    private ArrayList<Cart> carts;
    private DatabaseHelper db;

    public CartListAdapter(Context ctx, ArrayList<Cart> carts, DatabaseHelper db){
        this.ctx = ctx;
        this.carts = carts;
        this.db = db;
    }

    public ArrayList<Cart> getCartList(){
        return carts;
    }

    public void update(ArrayList<Cart> newCart){
        carts.clear();
        carts.addAll(newCart);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CartListRowBinding databinding = CartListRowBinding.inflate(inflater, parent, false);
        return new ViewHolder(databinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart cart = carts.get(position);
        holder.binding.setCart(cart);
        holder.binding.bookPrice.setText("$ " + cart.getBookPrice());
        String quantity = String.valueOf(cart.getQuantity());
        holder.binding.quantity.setText(quantity);
        Glide.with(ctx).load(cart.getBookImage())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.binding.bookImg);

        holder.binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(holder.binding.quantity.getText().toString());
                quantity = quantity + 1;
                String newquantity = String.valueOf(quantity);
                holder.binding.quantity.setText(newquantity);
                carts.get(position).setQuantity(quantity);
                ((BookCartListActivity) ctx).updatePrice(carts);
                notifyDataSetChanged();
            }
        });

        holder.binding.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(holder.binding.quantity.getText().toString());
                quantity = quantity - 1;

                if(quantity == 0){
                    return;
                }

                String newquantity = String.valueOf(quantity);
                holder.binding.quantity.setText(newquantity);
                carts.get(position).setQuantity(quantity);
                ((BookCartListActivity) ctx).updatePrice(carts);
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CartListRowBinding binding;

        public ViewHolder(@NonNull CartListRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }



    }


}
