package com.parth.thebookapplication.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.parth.thebookapplication.model.entity.Category;
import com.parth.thebookapplication.model.entity.Book;

import java.util.List;

@Dao
public interface BookDao {
    @Query("SELECT * FROM book_table")
    LiveData<List<Book>> getAllBook();

    @Query("SELECT * FROM book_table WHERE category_id IN (:categoryId)")
    LiveData<List<Book>> loadAllByIds(int categoryId);

    @Insert
    void insert(Book... books);

    @Update
    void update(Book... books);

    @Delete
    void delete(Book book);
}
