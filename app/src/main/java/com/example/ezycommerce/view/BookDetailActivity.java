package com.example.ezycommerce.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ezycommerce.DatabaseHelper;
import com.example.ezycommerce.R;
import com.example.ezycommerce.databinding.ActivityBookDetailBinding;
import com.example.ezycommerce.model.Book;
import com.example.ezycommerce.model.Cart;

import java.util.ArrayList;

public class BookDetailActivity extends AppCompatActivity {

    private ActivityBookDetailBinding binding;
    private Book book;
    private DatabaseHelper db;
    private ArrayList<Cart> carts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(BookDetailActivity.this, R.layout.activity_book_detail);

        book = (Book) getIntent().getSerializableExtra("book");
        binding.setBook(book);
        binding.bookPrice.setText("$ " + book.getPrice());
        Glide.with(this).load(book.getImg())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(binding.bookImg);

        binding.BuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = new DatabaseHelper(BookDetailActivity.this);
                carts = db.getAllCart();

                if(carts.isEmpty()){
                    db.insertBookToCart(book);
                }
                else{
                    int flag = 0;
                    for (Cart item : carts) {
                        if(item.getBookName().equals(book.getName())){
                            db.addBookQuantity(book.getName(), item.getQuantity());
                            flag = 1;
                            break;
                        }
                    }

                    if(flag == 0){
                        db.insertBookToCart(book);
                    }

                }

                Intent intent = new Intent(BookDetailActivity.this, BookCartListActivity.class);
                startActivity(intent);
            }
        });

    }
}