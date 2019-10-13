package com.parth.thebookapplication.model.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.parth.thebookapplication.model.AppDatabase;
import com.parth.thebookapplication.model.BookDao;
import com.parth.thebookapplication.model.CategoryDao;
import com.parth.thebookapplication.model.entity.Book;
import com.parth.thebookapplication.model.entity.Category;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RepositoryRoomDataBase {

    private BookDao bookDao;
    private CategoryDao categoryDao;

    public RepositoryRoomDataBase(Application application) {

        AppDatabase appDatabase = AppDatabase.appDatabaseInstance(application);

        this.bookDao = appDatabase.bookDao();
        this.categoryDao = appDatabase.categoryDao();

    }

    public LiveData<List<Book>> getBookList(int categoryId) {
        return bookDao.loadAllByIds(categoryId);
    }

    public LiveData<List<Category>> getCategoryList() {
        return categoryDao.getAllCategory();
    }

    public void insetCategory(final Category category){
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDao.insert(category);
            }
        });
    }

    public void deleteCategory(final Category category){

        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDao.insert(category);
            }
        });
    }

    public void updateCategory(final Category category){

        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDao.insert(category);
            }
        });
    }

    public void delteBook(final Book book){

        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bookDao.insert(book);
            }
        });
    }

    public void updateBook(final Book book){

        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bookDao.insert(book);
            }
        });
    }

    public void insetBook(final Book book){

        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bookDao.insert(book);
            }
        });
    }

}
