package com.parth.thebookapplication.model.entity;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.parth.thebookapplication.BR;

@Entity(tableName = "category_table")
public class Category extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int categoryID;
    @ColumnInfo(name = "category_name")
    private String categoryName;
    @ColumnInfo(name = "description")
    private String description;

    @Ignore
    public Category() {
    }

    public Category(int categoryID, String categoryName, String description) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.description = description;
    }

    @Bindable
    public int getCategoryID() {
        return categoryID;
    }

    public void setCcategoryID(int categoryID) {
        this.categoryID = categoryID;
        notifyPropertyChanged(BR.categoryID);
    }

    @Bindable
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        notifyPropertyChanged(BR.categoryName);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @NonNull
    @Override
    public String toString() {
        return this.getCategoryName();
    }
}
