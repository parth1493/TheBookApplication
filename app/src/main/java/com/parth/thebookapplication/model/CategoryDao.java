package com.parth.thebookapplication.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.parth.thebookapplication.model.entity.Book;
import com.parth.thebookapplication.model.entity.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM category_table")
    LiveData<List<Category>> getAllCategory();

    @Insert
    void insert(Category... categories);

    @Update
    void update(Category... categories);

    @Delete
    void delete(Category category);
}
