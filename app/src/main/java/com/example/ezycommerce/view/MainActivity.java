package com.example.ezycommerce.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ezycommerce.BooksResponse;
import com.example.ezycommerce.IBookService;
import com.example.ezycommerce.R;
import com.example.ezycommerce.RetrofitAPIClient;
import com.example.ezycommerce.model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private IBookService service;
    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = RetrofitAPIClient.getRetrofitInstance();
        service = retrofit.create(IBookService.class);
        Call<BooksResponse> responseCall = service.getBooks("2201727834","Christopher Irvine Sendjaya");

        responseCall.enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                books = response.body().getBooks();
                

            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {

            }
        });


    }
}