package com.example.flashcardproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AccesBase {
   private String nombase="bdFlashcard.sqlite" ;
   private int versionbase=1;
   private MySQLite accesBD;
   private SQLiteDatabase bd;
   public AccesBase(Context context){
       accesBD=new MySQLite(context, nombase, null, versionbase);
   }

    public void ajout(Flashcard flashcard){
       bd=accesBD.getWritableDatabase();
       String req="insert into flashcard (question,answer,wronganswer1,wronganswer2) values";
       req += "(\""+flashcard.getQuestion()+"\",\""+flashcard.getAnswer()+"\",\""+flashcard.getWronganswer1()+"\",\""+flashcard.getWronganswer2()+"\")";
       bd.execSQL(req);
    }
    public void modifier(Flashcard flashcard){
        bd=accesBD.getWritableDatabase();
        String req="UPDATE flashcard SET question=?,answer=?,wronganswer1=?,wronganswer2=? WHERE Id=?";
        String[] args= new String[]{String.valueOf(flashcard.getId())};
        bd.execSQL(req,new String[]{flashcard.getQuestion(), flashcard.getAnswer(), flashcard.getWronganswer1(), flashcard.getWronganswer2(),args[0]});
    }
    public void delete(int id){
        bd=accesBD.getWritableDatabase();
        String req="DELETE FROM flashcard WHERE Id=?";
        String[] args= new String[]{String.valueOf(id)};
        bd.execSQL(req,args);
    }
    public List<Flashcard> recuperer(){
        bd=accesBD.getWritableDatabase();
        List<Flashcard> flashcard=new ArrayList<>();
        Flashcard flashcard1=null;
        String req="SELECT * FROM flashcard";
        Cursor cursor=bd.rawQuery(req,null);
        if(cursor.moveToFirst()){
            do {
                flashcard1 = new Flashcard(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(0));
                flashcard.add(flashcard1);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return flashcard;
    }

}
