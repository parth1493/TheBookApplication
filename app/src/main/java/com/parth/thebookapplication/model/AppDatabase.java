package com.parth.thebookapplication.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.parth.thebookapplication.model.entity.Book;
import com.parth.thebookapplication.model.entity.Category;

@Database(entities = {Book.class, Category.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract BookDao bookDao();
    public abstract CategoryDao categoryDao();

    public static synchronized AppDatabase appDatabaseInstance(Context context){

        if (INSTANCE == null) {

            INSTANCE = Room.databaseBuilder(context,
                    AppDatabase.class, "bookCategorise-Database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();

        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitDataEntryAcyncTask(INSTANCE).execute();
        }
    };

    private static class InitDataEntryAcyncTask extends AsyncTask<Void,Void,Void>{

        AppDatabase appDatabase;
        public InitDataEntryAcyncTask(AppDatabase appDatabase){
            this.appDatabase = appDatabase;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            DataEntry dataEntry = new DataEntry(appDatabase);
            dataEntry.populateData();
            return null;
        }
    }
}
