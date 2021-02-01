package com.example.ezycommerce.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ezycommerce.R;
import com.example.ezycommerce.model.Book;

import java.util.ArrayList;


public class SciFiBookFragment extends Fragment {

    private BookListAdapter adapter;
    private RecyclerView SciFiBookRV;

    public void updateData(ArrayList<Book> newBooks){
        adapter.updateData(newBooks);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sci_fi_book, container, false);
        SciFiBookRV = view.findViewById(R.id.SciFiBookRV);

        SciFiBookRV.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new BookListAdapter(view.getContext());
        SciFiBookRV.setAdapter(adapter);

        return view;
    }
}