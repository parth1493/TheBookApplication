package com.parth.thebookapplication.view;

import android.content.Intent;
import android.os.Bundle;

import com.parth.thebookapplication.R;
import com.parth.thebookapplication.adapter.BooksAdapter;
import com.parth.thebookapplication.databinding.ActivityMainBinding;
import com.parth.thebookapplication.model.entity.Book;
import com.parth.thebookapplication.model.entity.Category;
import com.parth.thebookapplication.viewmodel.MainActivityViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.parth.thebookapplication.Util.Util.BOOK_ID;
import static com.parth.thebookapplication.Util.Util.BOOK_NAME;
import static com.parth.thebookapplication.Util.Util.UNIT_PRICE;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    private MainActivityOnClick clickHandler;
    private ArrayList<Category> categoryArrayList;
    private ArrayList<Book> booksList;
    private RecyclerView booksRecyclerView;
    private BooksAdapter booksAdapter;
    private Category selectedCatagory;
    private static final int ADD_BOOK_REQUEST_CODE = 1;
    public static final int EDIT_BOOK_REQUEST_CODE=2;
    private int selectedBookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mainActivityViewModel.getGetAllCategory().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryArrayList = (ArrayList<Category>)categories;
                for(Category c:categories){
                    Log.i(TAG, "list of category: "+c.getCategoryName());
                }

                showSpinner();
            }
        });

       

        //data binding
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        clickHandler = new MainActivityOnClick();
        activityMainBinding.setClickHandlers(clickHandler);

    }

    private void showSpinner() {

        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<Category>(this,R.layout.spinner_view,categoryArrayList);
        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_view);
        activityMainBinding.setSpinnerAdaptor(categoryArrayAdapter);
    }

    private void loadBooksArrayList(int categoryId){
        mainActivityViewModel.getGetAllBooks(categoryId).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                booksList=(ArrayList<Book>) books;
                loadRecyclerView();
            }
        });

    }

    private void loadRecyclerView(){

        booksRecyclerView=activityMainBinding.contentMain.rvBook;
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        booksRecyclerView.setHasFixedSize(true);

        booksAdapter=new BooksAdapter();
        booksRecyclerView.setAdapter(booksAdapter);

        booksAdapter.setBooks(booksList);
        booksAdapter.setListener(new BooksAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                selectedBookId = book.getBookId();
                Log.i("BookIdTest"," at 1 id is "+selectedBookId);
                Intent intent=new Intent(MainActivity.this, AddBookOrUpdateActivity.class);
                intent.putExtra(BOOK_ID,selectedBookId);
                Log.i("BookIdTest"," at 2 id is "+selectedBookId);
                intent.putExtra(BOOK_NAME,book.getBookName());
                intent.putExtra(UNIT_PRICE,book.getUnitPrice());
                startActivityForResult(intent,EDIT_BOOK_REQUEST_CODE);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Book bookToDelete=booksList.get(viewHolder.getAdapterPosition());
                mainActivityViewModel.deleteBook(bookToDelete);
            }
        }).attachToRecyclerView(booksRecyclerView);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("BookIdTest"," at 4 top id is "+selectedBookId);
        int selectedCategoryId = selectedCatagory.getCategoryID();

        if(requestCode==ADD_BOOK_REQUEST_CODE && resultCode==RESULT_OK){
            Log.i("BookIdTest"," at 4 wrong 2 id is "+selectedBookId);
            Book book = new Book(0,data.getStringExtra(BOOK_NAME),
                               data.getDoubleExtra(UNIT_PRICE,0),
                               selectedCategoryId);
//            book.setCategoryId();
//            book.setBookName();
//            book.setUnitPrice();
            mainActivityViewModel.addNewBook(book);



        } else if (requestCode == EDIT_BOOK_REQUEST_CODE && resultCode == RESULT_OK) {

            Book book = new Book(selectedBookId,
                                 data.getStringExtra(BOOK_NAME),
                                 data.getDoubleExtra(UNIT_PRICE,0),
                                 selectedCategoryId);
//            Book book=new Book();
//            book.setCategoryId(selectedCategoryId);
//            book.setBookName(data.getStringExtra(BOOK_NAME));
//            book.setUnitPrice(data.getDoubleExtra(UNIT_PRICE,0));
//            Log.i("BookIdTest"," at 4 id is "+selectedBookId);
//            book.setBookId(selectedBookId);
            mainActivityViewModel.updateBook(book);


        }
    }

    public class MainActivityOnClick {



        public void OnFabClick(View view){
            Intent intent = new Intent(MainActivity.this, AddBookOrUpdateActivity.class);
            startActivityForResult(intent, ADD_BOOK_REQUEST_CODE);
        }

        public void onSetected(AdapterView<?> adapterView, View view , int pos, long id){

            selectedCatagory = (Category)adapterView.getItemAtPosition(pos);

            String message = "Id is " + selectedCatagory.getCategoryID() + "\n"
                    + selectedCatagory.getCategoryName();

            Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();

            loadBooksArrayList(selectedCatagory.getCategoryID());
        }
    }
}
