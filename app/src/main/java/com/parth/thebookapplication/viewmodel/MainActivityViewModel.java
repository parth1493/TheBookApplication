package com.parth.thebookapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.parth.thebookapplication.model.entity.Book;
import com.parth.thebookapplication.model.entity.Category;
import com.parth.thebookapplication.model.repository.RepositoryRoomDataBase;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private RepositoryRoomDataBase repositoryRoomDataBase;
    private LiveData<List<Book>> getAllBooks;
    private LiveData<List<Category>> getAllCategory;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repositoryRoomDataBase = new RepositoryRoomDataBase(application);
    }

    public LiveData<List<Book>> getGetAllBooks(int categoryId) {
       getAllBooks = repositoryRoomDataBase.getBookList(categoryId);
       return getAllBooks;
    }

    public LiveData<List<Category>> getGetAllCategory() {
        getAllCategory = repositoryRoomDataBase.getCategoryList();
        return getAllCategory;
    }

    public void addNewBook(Book book){
        repositoryRoomDataBase.insetbook(book);
    }

    public void updateBook(Book book){
        repositoryRoomDataBase.updatebook(book);
    }

    public void deleteBook(Book book){
        repositoryRoomDataBase.deltebook(book);
    }


}
