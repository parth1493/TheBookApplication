package com.parth.thebookapplication.view;

import android.content.Context;
import android.database.DatabaseUtils;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.parth.thebookapplication.R;
import com.parth.thebookapplication.adapter.BooksAdapter;
import com.parth.thebookapplication.databinding.ActivityMainBinding;
import com.parth.thebookapplication.model.entity.Book;
import com.parth.thebookapplication.model.entity.Category;
import com.parth.thebookapplication.viewmodel.MainActivityViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    private MainActivityOnClick clickHandler;
    private ArrayList<Category> categoryArrayList;
    private ArrayList<Book> booksList;
    private RecyclerView booksRecyclerView;
    private BooksAdapter booksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainActivityViewModel = new ViewModelProvider(MainActivity.this).get(MainActivityViewModel.class);

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

    public class MainActivityOnClick {

        private Category selectedCatagory;

        public void OnFabClick(View view){
            Toast.makeText(MainActivity.this,"Fab clicked",Toast.LENGTH_LONG).show();
        }

        public void onSetected(AdapterView<?> adapterView, View view , int pos, long id){

            selectedCatagory = (Category)adapterView.getItemAtPosition(pos);

            String message = "Id is " + selectedCatagory.getCategoryId() + "\n"
                    + selectedCatagory.getCategoryName();

            Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();

            loadBooksArrayList(selectedCatagory.getCategoryId());
        }
    }
}
