package com.parth.thebookapplication.model;

import com.parth.thebookapplication.model.entity.Book;
import com.parth.thebookapplication.model.entity.Category;

public class DataEntry {

    private BookDao bookDao;
    private CategoryDao categoryDao;

    public DataEntry(AppDatabase appDatabase) {
        bookDao = appDatabase.bookDao();
        categoryDao = appDatabase.categoryDao();
    }

    public void populateData() {

        Category category1 = new Category();
        category1.setCategoryName("Parth");
        category1.setDescription("Parth1 Parth1 Parth1 Parth1 Parth1 ");

        Category category2 = new Category();
        category2.setCategoryName("Sunny");
        category2.setDescription("Sunny Sunny Sunny Sunny Sunny ");

        Category category3 = new Category();
        category3.setCategoryName("Amit");
        category3.setDescription("Amit Amit Amit Amit Amit ");

        categoryDao.insert(category1);
        categoryDao.insert(category2);
        categoryDao.insert(category3);

        Book book1 = new Book();
        book1.setBookName("Book1");
        book1.setUnitPrice(200);
        book1.setCategoryId(1);

        Book book2 = new Book();
        book2.setBookName("Book2");
        book2.setUnitPrice(180);
        book2.setCategoryId(1);

        Book book3 = new Book();
        book3.setBookName("Book3");
        book3.setUnitPrice(150);
        book3.setCategoryId(1);

        Book book4 = new Book();
        book4.setBookName("Book4");
        book4.setUnitPrice(90);
        book4.setCategoryId(1);

        Book book5 = new Book();
        book5.setBookName("Book5");
        book5.setUnitPrice(800);
        book5.setCategoryId(2);

        Book book6 = new Book();
        book6.setBookName("Book6");
        book6.setUnitPrice(230);
        book6.setCategoryId(2);

        Book book7 = new Book();
        book7.setBookName("Book7");
        book7.setUnitPrice(100);
        book7.setCategoryId(2);

        Book book8 = new Book();
        book8.setBookName("Book8");
        book8.setUnitPrice(140);
        book8.setCategoryId(2);

        Book book9 = new Book();
        book9.setBookName("Book9");
        book9.setUnitPrice(209);
        book9.setCategoryId(2);

        Book book10 = new Book();
        book10.setBookName("Book10");
        book10.setUnitPrice(200);
        book10.setCategoryId(3);

        Book book11 = new Book();
        book11.setBookName("Book11");
        book11.setUnitPrice(80);
        book11.setCategoryId(3);

        bookDao.insert(book1);
        bookDao.insert(book2);
        bookDao.insert(book3);
        bookDao.insert(book4);
        bookDao.insert(book5);
        bookDao.insert(book6);
        bookDao.insert(book7);
        bookDao.insert(book8);
        bookDao.insert(book9);
        bookDao.insert(book10);
        bookDao.insert(book11);
    }
}
