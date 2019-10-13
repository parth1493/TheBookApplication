package com.parth.thebookapplication.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.parth.thebookapplication.R;
import com.parth.thebookapplication.Util.Util;
import com.parth.thebookapplication.databinding.ActivityAddBookOrUpdateBinding;
import com.parth.thebookapplication.model.entity.Book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import static com.parth.thebookapplication.Util.Util.BOOK_NAME;
import static com.parth.thebookapplication.Util.Util.UNIT_PRICE;

public class AddBookOrUpdateActivity extends AppCompatActivity {

    private Book book;
    private ActivityAddBookOrUpdateBinding activitySecondAcitivyBinding;
    private HandleClickEvent handleClickEvent;
    private String typeOfActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book_or_update);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        book = new Book();

        activitySecondAcitivyBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_book_or_update);


        handleClickEvent = new HandleClickEvent(this);
        activitySecondAcitivyBinding.setOnSubmit(handleClickEvent);

        Intent intent=getIntent();
        if(intent.hasExtra(Util.BOOK_ID)){

            typeOfActivity = "Update book detail";
            book.setBookName(intent.getStringExtra(BOOK_NAME));
            book.setUnitPrice(intent.getDoubleExtra(UNIT_PRICE,0));

        }else{

            typeOfActivity = "Add new book";

        }
        activitySecondAcitivyBinding.setAddBook(book);
        toolbar.setTitle(typeOfActivity);
    }

    public class HandleClickEvent {

        Context context;

        public HandleClickEvent(Context context) {
            this.context = context;
        }


        public void onSubmitClick(View view){

            if(book.getBookName()==null){

                Toast.makeText(context,"Name field cannot be empty",Toast.LENGTH_LONG).show();

            }else{

                Intent intent=new Intent();
                intent.putExtra(BOOK_NAME,book.getBookName());
                intent.putExtra(UNIT_PRICE,book.getUnitPrice());
                setResult(RESULT_OK,intent);
                Toast.makeText(context,"record " + typeOfActivity ,Toast.LENGTH_LONG).show();
                finish();

            }
        }
    }

}
