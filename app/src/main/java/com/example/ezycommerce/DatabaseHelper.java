package com.example.ezycommerce;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ezycommerce.model.Book;
import com.example.ezycommerce.model.Cart;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "EzyCommerceApps.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_CartTable_query = "CREATE TABLE carts ( id INTEGER PRIMARY KEY AUTOINCREMENT, BookName TEXT , BookAuthor TEXT, BookPrice DOUBLE, BookImage TEXT, Quantity INTEGER)";
        db.execSQL(create_CartTable_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS carts");
    }

    public void insertBookToCart(Book book){


        if(book.getName().equals("Harry Potter and the Sorcerer's Stone")){
            String insert_CartTable_query = "INSERT INTO carts(BookName, BookAuthor, BookPrice, BookImage, Quantity) VALUES ( 'Harry Potter and the Sorcerers Stone', '" +book.getAuthor()+ "', '" +book.getPrice()+ "', '" +book.getImg()+ "', 1); ";
            SQLiteDatabase db = this.getReadableDatabase();
            db.execSQL(insert_CartTable_query);
            db.close();
            return;
        }

        String insert_CartTable_query = "INSERT INTO carts(BookName, BookAuthor, BookPrice, BookImage, Quantity) VALUES ( '"+book.getName()+"', '" +book.getAuthor()+ "', '" +book.getPrice()+ "', '" +book.getImg()+ "', 1); ";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(insert_CartTable_query);

        db.close();

    }

    public void updateCart(String name, int quantity){

        String update_CartTable_query = "UPDATE carts SET Quantity = '" + quantity + "' WHERE BookName = '" + name + "' ;";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(update_CartTable_query);

        db.close();

    }

    public void addBookQuantity(String name, int quantity){

        quantity = quantity + 1;
        String update_CartTable_query = "UPDATE carts SET Quantity = '" + quantity + "' WHERE BookName = '" + name + "' ;";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(update_CartTable_query);

        db.close();

    }

    public ArrayList<Cart> getAllCart(){

        ArrayList<Cart> carts = new ArrayList<>();
        String query = "SELECT * FROM carts;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor;

        cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){

            do{
                int id = cursor.getInt(0);
                String Name = cursor.getString(1);
                String author = cursor.getString(2);
                double price = cursor.getDouble(3);
                String image = cursor.getString(4);
                int quantity = cursor.getInt(5);
                carts.add(new Cart(id, quantity, Name, price, author, image));

            }while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return carts;


    }

    public void deleteAllCart(){

        String Delete_CartTable_query = "DELETE FROM carts;";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(Delete_CartTable_query);

        db.close();

    }



}
