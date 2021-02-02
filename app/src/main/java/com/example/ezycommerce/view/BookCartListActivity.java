package com.example.ezycommerce.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ezycommerce.DatabaseHelper;
import com.example.ezycommerce.R;
import com.example.ezycommerce.databinding.ActivityBookCartListBinding;
import com.example.ezycommerce.model.Cart;

import java.util.ArrayList;

public class BookCartListActivity extends AppCompatActivity {

    private ActivityBookCartListBinding binding;
    private ArrayList<Cart> carts;
    private DatabaseHelper db;
    private CartListAdapter adapter;
    private int subtotal = 0, taxes = 0, totalprice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(BookCartListActivity.this, R.layout.activity_book_cart_list);
        db = new DatabaseHelper(this);
        carts = db.getAllCart();

        for(Cart cart: carts){
            int quantity = cart.getQuantity();
            int price = cart.getBookPrice();
            subtotal += (quantity * price);
        }

        taxes = subtotal / 10;
        totalprice = subtotal + taxes;

        binding.SubTotal.setText( "$ "+ subtotal);
        binding.Taxes.setText("$ " + taxes);
        binding.TotalPrice.setText("$ " + totalprice);

        binding.CartRV.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartListAdapter(this, carts, db);
        binding.CartRV.setAdapter(adapter);

        binding.NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Cart> carts1 = adapter.getCartList();

                for (Cart cart: carts1) {
                    db.updateCart(cart.getBookName(), cart.getQuantity());
                }

                Intent intent = new Intent(BookCartListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        binding.CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteAllCart();
                Intent intent = new Intent(BookCartListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void updatePrice(ArrayList<Cart> newcarts){
        subtotal = 0;
        for(Cart cart: newcarts){
            int quantity = cart.getQuantity();
            int price = cart.getBookPrice();
            subtotal += (quantity * price);
        }

        taxes = subtotal / 10;
        totalprice = subtotal + taxes;

        binding.SubTotal.setText( "$ "+ subtotal);
        binding.Taxes.setText("$ " + taxes);
        binding.TotalPrice.setText("$ " + totalprice);

    }

}