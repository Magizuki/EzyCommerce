package com.example.ezycommerce.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ezycommerce.R;
import com.example.ezycommerce.databinding.ActivityBookDetailBinding;
import com.example.ezycommerce.model.Book;

public class BookDetailActivity extends AppCompatActivity {

    private ActivityBookDetailBinding binding;
    private Book book;

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


    }
}