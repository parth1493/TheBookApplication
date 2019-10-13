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

public class RepositoryRoomDataBase {

    private BookDao bookDao;
    private CategoryDao categoryDao;
    private LiveData<List<Book>> bookList;
    private LiveData<List<Category>> categoryList;

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

    public void insetCategory(Category category){
        new InsetCategoryAsyncTask(categoryDao).execute(category);
    }

    public void deleteCategory(Category category){
        new DeleteCategoryAsyncTask(categoryDao).execute(category);
    }

    public void updateCategory(Category category){
        new UpdateCategoryAsyncTask(categoryDao).execute(category);
    }

    public void deltebook(Book book){
        new DeleteBookAsyncTask(bookDao).execute(book);
    }

    public void updatebook(Book book){
        new UpdateBookAsyncTask(bookDao).execute(book);
    }

    public void insetbook(Book book){
        new InsetBookAsyncTask(bookDao).execute(book);
    }

    private class InsetCategoryAsyncTask extends AsyncTask<Category,Void,Void>{

        private CategoryDao categoryDao;

        public InsetCategoryAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            this.categoryDao.insert(categories[0]);
            return null;
        }
    }

    private class DeleteCategoryAsyncTask extends AsyncTask<Category,Void,Void>{

        private CategoryDao categoryDao;

        public DeleteCategoryAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            this.categoryDao.delete(categories[0]);
            return null;
        }
    }

    private class UpdateCategoryAsyncTask extends AsyncTask<Category,Void,Void>{

        private CategoryDao categoryDao;

        public UpdateCategoryAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            this.categoryDao.insert(categories[0]);
            return null;
        }
    }

    private class InsetBookAsyncTask extends AsyncTask<Book,Void,Void>{

        private BookDao bookDao;

        public InsetBookAsyncTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {
            this.bookDao.insert(books[0]);
            return null;
        }
    }

    private class DeleteBookAsyncTask extends AsyncTask<Book,Void,Void>{

        private BookDao bookDao;

        public DeleteBookAsyncTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {
            this.bookDao.delete(books[0]);
            return null;
        }
    }

    private class UpdateBookAsyncTask extends AsyncTask<Book,Void,Void>{

        private BookDao bookDao;

        public UpdateBookAsyncTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {
            this.bookDao.update(books[0]);
            return null;
        }
    }
}
