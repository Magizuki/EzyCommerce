package com.example.ezycommerce;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IBookService {

    @GET("book")
    Call<BooksResponse> getBooks(
            @Query(value="nim") String nim,
            @Query(value="nama") String nama
    );



}
