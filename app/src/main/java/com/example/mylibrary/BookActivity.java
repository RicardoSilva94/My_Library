 package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

 public class BookActivity extends AppCompatActivity {

     public static final String BOOK_ID_KEY = "bookId"; // A constant key for passing book IDs between activities

    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAddToWantToRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavorite;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book); // Set the layout for this activity

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable the back button in the action bar

        initViews(); // Initialize UI components


        Intent intent = getIntent(); // Get the intent that started this activity
        if (intent != null) {
            int bookId = intent.getIntExtra(BookActivity.BOOK_ID_KEY, -1);
           // Log.d("BookActivity", "Received bookId: " + bookId); // log para verificar o bookId
            if (bookId != -1) { // Check if a valid book ID is received
                // Retrieve the book using the book ID from shared preferences
                // Prossegue com a busca do livro no Utils
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if (incomingBook != null) {
                    //  Log.d("BookActivity", "Found book: " + incomingBook.getName()); // log para verificar o livro encontrado
                    // Define os dados do livro e manipula os botões aqui
                    setData(incomingBook);
                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);
                } else{
                    Log.e("BookActivity", "Book not found for bookId: " + bookId); // log de erro se o livro não for encontrado
                }
            }
        }

    }

    private void handleCurrentlyReadingBooks (final Book book){
        // Retrieve the list of currently reading books

        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean existInCurrentlyReadingBooks = false;

        for (Book b: currentlyReadingBooks){
            if (b.getId() == book.getId()) {
                existInCurrentlyReadingBooks = true;
            }
        }
        if (existInCurrentlyReadingBooks) {
            btnAddToCurrentlyReading.setEnabled(false);
        }else {
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Display a message and navigate to the "Currently Reading" activity
                    if(Utils.getInstance(BookActivity.this).addToCurrentlyReading(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

     private void handleFavoriteBooks(final Book book){
         // Retrieve the list of favorite books
         ArrayList<Book> favoriteBooks = Utils.getInstance(this).getFavoriteBooks();

         boolean existInFavoriteBooks = false;

         for (Book b: favoriteBooks){
             if (b.getId() == book.getId()) {
                 existInFavoriteBooks = true;
             }
         }
         if (existInFavoriteBooks) {
             btnAddToFavorite.setEnabled(false);
         }else {
             btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if(Utils.getInstance(BookActivity.this).addToFavorite(book)){
                         // Display a message and navigate to the "Favorite" activity
                         Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(BookActivity.this, FavoriteActivity.class);
                         startActivity(intent);

                     }else{
                         Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                     }
                 }
             });
         }
     }

    private void handleWantToReadBooks (final Book book){
        // Retrieve the list of "Want to Read" books
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantToReadBooks();

        boolean existInWantToReadBooks = false;

        for (Book b: wantToReadBooks){
            if (b.getId() == book.getId()) {
                existInWantToReadBooks = true;
            }
        }
        if (existInWantToReadBooks) {
            btnAddToWantToRead.setEnabled(false);
        }else {
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToWantToRead(book)){
                        // Display a message and navigate to the "Want to Read" activity
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


     }



    /* Enable and disable button
    * Add the book to Already Read Book ArrayList
    */

    private void handleAlreadyRead(Book book){
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for (Book b: alreadyReadBooks){
            if (b.getId() == book.getId()) {
                existInAlreadyReadBooks = true;
            }
        }
        if (existInAlreadyReadBooks) {
            btnAddToAlreadyRead.setEnabled(false);
        }else {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
    private void setData(Book book){
        // Set book information on UI components
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(bookImage);

    }

    private void initViews(){
        // Initialize UI components
        txtAuthor = findViewById(R.id.txtAuthorName);
        txtBookName = findViewById(R.id.txtBookName);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescription);

        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyReadList);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToFavorite = findViewById(R.id.btnAddToFavorite);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToReadList);

        bookImage = findViewById(R.id.bookImage);
    }

     @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         // Handle the back button in the action bar
         switch (item.getItemId()){
             case android.R.id.home:
                 onBackPressed();
                 break;
             default:
                 break;
         }
         return super.onOptionsItemSelected(item);
     }
}

