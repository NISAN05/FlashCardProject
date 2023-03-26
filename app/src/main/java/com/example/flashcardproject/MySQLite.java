package com.example.flashcardproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public  class MySQLite extends SQLiteOpenHelper {
    private String creation="create table flashcard("
        +"Id INTEGER PRIMARY KEY AUTOINCREMENT,"
        +"question TEXT NOT NULL,"
        +"answer TEXT NOT NULL,"
        +"wronganswer1 TEXT NOT NULL,"
        +"wronganswer2 TEXT NOT NULL);";
    /**
     * constructeur
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public MySQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * si changement de base de donnees
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(creation);
    }

    /**
     * si nouvelle version
     * @param sqLiteDatabase
     * @param i ancienne version
     * @param i1 nouvelle version
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
