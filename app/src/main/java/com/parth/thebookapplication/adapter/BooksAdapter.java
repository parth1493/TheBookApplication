package com.parth.thebookapplication.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.parth.thebookapplication.R;
import com.parth.thebookapplication.Util.BooksDiffCallback;
import com.parth.thebookapplication.databinding.BookListBinding;
import com.parth.thebookapplication.model.entity.Book;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder>{
    private OnItemClickListener listener;
    private ArrayList<Book> books=new ArrayList<>();

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        BookListBinding bookListBinding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.book_list,viewGroup,false);
        return new BookViewHolder(bookListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, int i) {
         Book book=books.get(i);
         bookViewHolder.bookListBinding.setBooks(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> newBooks) {
        
        final DiffUtil.DiffResult result= DiffUtil.calculateDiff(new BooksDiffCallback(books,newBooks),false);
        books = newBooks;
        result.dispatchUpdatesTo(BooksAdapter.this);

    }

    class BookViewHolder extends RecyclerView.ViewHolder{
    private BookListBinding bookListBinding;

    public BookViewHolder(@NonNull BookListBinding bookListBinding) {
        super(bookListBinding.getRoot());
        this.bookListBinding=bookListBinding;
        bookListBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              int clickedPosition=getAdapterPosition();

              if(listener!=null && clickedPosition!=RecyclerView.NO_POSITION) {
                  listener.onItemClick(books.get(clickedPosition));
              }
            }
        });

    }

}

public interface OnItemClickListener{
    void onItemClick(Book book);
}

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
