package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.mylibrary.BookRecViewAdapter;

public class AddBookActivity extends AppCompatActivity {

    private EditText edtTitle, edtAuthor, edtPages, edtDescription, edtLongDescription, edtImageUrl;
    private Button btnSaveBook;

    private BookRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText edtTitle = findViewById(R.id.edtTitle);
        EditText edtAuthor = findViewById(R.id.edtAuthor);
        EditText edtPages = findViewById(R.id.edtPages);
        EditText edtDescription = findViewById(R.id.edtDescription);
        EditText edtLongDescription = findViewById(R.id.edtLongDescription);
        EditText edtImageUrl = findViewById(R.id.edtImageUrl);

        Button btnSaveBook = findViewById(R.id.btnSaveBook);
        adapter = new BookRecViewAdapter(this, "allBooks");

        btnSaveBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the info from the user
                String title = edtTitle.getText().toString();
                String author = edtAuthor.getText().toString();
                int pages = Integer.parseInt(edtPages.getText().toString());
                String imageUrl = edtImageUrl.getText().toString();
                String shortDescription = edtDescription.getText().toString();
                String longDescription = edtLongDescription.getText().toString();



                // Create a new Book object
                Book newBook = new Book(title, author, pages, imageUrl, shortDescription, longDescription);

                Log.d("AddBookActivity", "ID do novo livro: " + newBook.getId());

                // Add the new book to the list of all books
                Utils.getInstance(AddBookActivity.this).addToAllBooks(newBook);

                Log.d("AddBookActivity", "ID do livro após adicionar: " + newBook.getId());

                // Notify the adapter of the changes in the book list
                adapter.setBooks(Utils.getInstance(AddBookActivity.this).getAllBooks());

                // Show a toast message to confirm the book was added
                Toast.makeText(AddBookActivity.this, "Book added successfully", Toast.LENGTH_SHORT).show();

                // Optionally, clear the input fields for the next entry
                edtTitle.setText("");
                edtAuthor.setText("");
                edtPages.setText("");
                edtDescription.setText("");
                edtLongDescription.setText("");
                edtImageUrl.setText("");


            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        //Utils.writeToStorage();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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