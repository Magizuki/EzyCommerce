package com.example.ezycommerce.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;


import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ezycommerce.databinding.BookListRowBinding;
import com.example.ezycommerce.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    private ArrayList<Book> books;
    private Context ctx;

    public BookListAdapter(Context ctx){
        this.ctx = ctx;
        this.books = new ArrayList<>();
    }

    public void updateData(ArrayList<Book> newbooks){
        books.clear();
        books = newbooks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        BookListRowBinding databinding = BookListRowBinding.inflate(inflater, parent,false);
        return new ViewHolder(databinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.binding.setBook(book);
        holder.binding.bookPrice.setText( "$ " + book.getPrice().toString());
        Glide.with(ctx).load(book.getImg())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.binding.bookImg);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private BookListRowBinding binding;

        public ViewHolder(@NonNull BookListRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }


}
