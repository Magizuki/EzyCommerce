package com.example.ezycommerce.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.ezycommerce.BooksResponse;
import com.example.ezycommerce.IBookService;
import com.example.ezycommerce.R;
import com.example.ezycommerce.RetrofitAPIClient;
import com.example.ezycommerce.databinding.ActivityMainBinding;
import com.example.ezycommerce.model.Book;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Retrofit retrofit;
    private IBookService service;
    private ArrayList<Book> books;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        binding.loadingView.setVisibility(View.VISIBLE);

        retrofit = RetrofitAPIClient.getRetrofitInstance();
        service = retrofit.create(IBookService.class);
        BusinessBookFragment businessBookFragment = new BusinessBookFragment();
        Call<BooksResponse> responseCall = service.getBooks("2201727834","Christopher Irvine Sendjaya");

        responseCall.enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                binding.loadingView.setVisibility(View.INVISIBLE);
                books = response.body().getBooks();

                ArrayList<Book> BusinessBookList = new ArrayList<Book>() ;

                for (Book book: books) {
                    if(book.getCategory().equalsIgnoreCase("Business")){
                        BusinessBookList.add(book);
                    }
                }

                businessBookFragment.updateData(BusinessBookList);
                binding.BusinessBtn.setOnClickListener(MainActivity.this);
                binding.CookBooksBtn.setOnClickListener(MainActivity.this);
                binding.MysteryBtn.setOnClickListener(MainActivity.this);
                binding.SciFiBtn.setOnClickListener(MainActivity.this);
            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {

            }
        });

        binding.BusinessBtn.setTextColor(Color.YELLOW);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(binding.container.getId() , businessBookFragment).commit();

    }


    @Override
    public void onClick(View view) {

        if(view.getId() == binding.BusinessBtn.getId()){
            binding.loadingView.setVisibility(View.VISIBLE);
            BusinessBookFragment businessBookFragment = new BusinessBookFragment();

            binding.BusinessBtn.setTextColor(Color.YELLOW);
            binding.SciFiBtn.setTextColor(Color.WHITE);
            binding.MysteryBtn.setTextColor(Color.WHITE);
            binding.CookBooksBtn.setTextColor(Color.WHITE);

            Call<BooksResponse> responseCall = service.getBooks("2201727834","Christopher Irvine Sendjaya");

            responseCall.enqueue(new Callback<BooksResponse>() {
                @Override
                public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                    binding.loadingView.setVisibility(View.INVISIBLE);
                    books = response.body().getBooks();

                    ArrayList<Book> BusinessBookList = new ArrayList<Book>() ;

                    for (Book book: books) {
                        if(book.getCategory().equalsIgnoreCase("Business")){
                            BusinessBookList.add(book);
                        }
                    }

                    businessBookFragment.updateData(BusinessBookList);
                    binding.BusinessBtn.setOnClickListener(MainActivity.this);
                    binding.CookBooksBtn.setOnClickListener(MainActivity.this);
                    binding.MysteryBtn.setOnClickListener(MainActivity.this);
                    binding.SciFiBtn.setOnClickListener(MainActivity.this);
                }

                @Override
                public void onFailure(Call<BooksResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });

            ArrayList<Book> BusinessBookList = new ArrayList<Book>() ;

            for (Book book: books) {
                if(book.getCategory().equalsIgnoreCase("Business")){
                    BusinessBookList.add(book);
                }
            }

//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().add(binding.container.getId() , businessBookFragment).commit();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(binding.container.getId(), businessBookFragment);
            fragmentTransaction.commit();


        }
        else if(view.getId() == binding.CookBooksBtn.getId()){
            binding.loadingView.setVisibility(View.VISIBLE);
            CookBookFragment cookBookFragment = new CookBookFragment();
            binding.BusinessBtn.setTextColor(Color.WHITE);
            binding.SciFiBtn.setTextColor(Color.WHITE);
            binding.MysteryBtn.setTextColor(Color.WHITE);
            binding.CookBooksBtn.setTextColor(Color.YELLOW);

            Call<BooksResponse> responseCall = service.getBooks("2201727834","Christopher Irvine Sendjaya");

            responseCall.enqueue(new Callback<BooksResponse>() {
                @Override
                public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                    binding.loadingView.setVisibility(View.INVISIBLE);
                    books.clear();
                    books = response.body().getBooks();

                    ArrayList<Book> CookBookList = new ArrayList<Book>() ;

                    for (Book book: books) {
                        if(book.getCategory().equalsIgnoreCase("cookbooks")){
                            CookBookList.add(book);
                        }
                    }

                    cookBookFragment.updateData(CookBookList);

                }

                @Override
                public void onFailure(Call<BooksResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });


            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(binding.container.getId(), cookBookFragment);
            fragmentTransaction.commit();

        }
        else if(view.getId() == binding.MysteryBtn.getId()){
            binding.loadingView.setVisibility(View.VISIBLE);
            MysteryBookFragment mysteryBookFragment = new MysteryBookFragment();
            binding.BusinessBtn.setTextColor(Color.WHITE);
            binding.SciFiBtn.setTextColor(Color.WHITE);
            binding.MysteryBtn.setTextColor(Color.YELLOW);
            binding.CookBooksBtn.setTextColor(Color.WHITE);

            Call<BooksResponse> responseCall = service.getBooks("2201727834","Christopher Irvine Sendjaya");

            responseCall.enqueue(new Callback<BooksResponse>() {
                @Override
                public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                    binding.loadingView.setVisibility(View.INVISIBLE);
                    books = response.body().getBooks();

                    ArrayList<Book> MysteryBookList = new ArrayList<Book>() ;

                    for (Book book: books) {
                        if(book.getCategory().equalsIgnoreCase("mystery")){
                            MysteryBookList.add(book);
                        }
                    }

                    mysteryBookFragment.updateData(MysteryBookList);
                    binding.BusinessBtn.setOnClickListener(MainActivity.this);
                    binding.CookBooksBtn.setOnClickListener(MainActivity.this);
                    binding.MysteryBtn.setOnClickListener(MainActivity.this);
                    binding.SciFiBtn.setOnClickListener(MainActivity.this);
                }
                @Override
                public void onFailure(Call<BooksResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(binding.container.getId(), mysteryBookFragment);
            fragmentTransaction.commit();

        }
        else if(view.getId() == binding.SciFiBtn.getId()){
            binding.loadingView.setVisibility(View.VISIBLE);
            SciFiBookFragment sciFiBookFragment = new SciFiBookFragment();

            binding.BusinessBtn.setTextColor(Color.WHITE);
            binding.SciFiBtn.setTextColor(Color.YELLOW);
            binding.MysteryBtn.setTextColor(Color.WHITE);
            binding.CookBooksBtn.setTextColor(Color.WHITE);

            Call<BooksResponse> responseCall = service.getBooks("2201727834","Christopher Irvine Sendjaya");

            responseCall.enqueue(new Callback<BooksResponse>() {
                @Override
                public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                    binding.loadingView.setVisibility(View.INVISIBLE);
                    books = response.body().getBooks();

                    ArrayList<Book> SciFiBookList = new ArrayList<Book>() ;

                    for (Book book: books) {
                        if(book.getCategory().equalsIgnoreCase("scifi")){
                            SciFiBookList.add(book);
                        }
                    }

                    sciFiBookFragment.updateData(SciFiBookList);
                    binding.BusinessBtn.setOnClickListener(MainActivity.this);
                    binding.CookBooksBtn.setOnClickListener(MainActivity.this);
                    binding.MysteryBtn.setOnClickListener(MainActivity.this);
                    binding.SciFiBtn.setOnClickListener(MainActivity.this);
                }

                @Override
                public void onFailure(Call<BooksResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(binding.container.getId(), sciFiBookFragment);
            fragmentTransaction.commit();

        }

    }
}