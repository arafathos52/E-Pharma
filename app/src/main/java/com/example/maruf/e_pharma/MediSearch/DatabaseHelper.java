package com.example.maruf.e_pharma.MediSearch;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME="dictionary.sqlite";
    public static final String DBLOCATION="/data/data/com.example.shuvo.medicare/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context){
        super(context,DBNAME,null,1);
        this.mContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void openDatabase(){
        String dbPath=mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase!=null && mDatabase.isOpen()){
            return;
        }
        mDatabase=SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);
    }
    public void closeDatabase(){
        if(mDatabase !=null){
            mDatabase.close();
        }
    }

    public List<DictionaryModel> getListWord(String wordSearch){
        DictionaryModel dictionaryModel=null;
        List<DictionaryModel> dictionaryModelList=new ArrayList<>();
        openDatabase();
        String[] args={"%"+wordSearch+"%"};

        Cursor cursor=mDatabase.rawQuery("Select * From tblWord Where Medicine Like ?",args);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            dictionaryModel=new DictionaryModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
            dictionaryModelList.add(dictionaryModel);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return dictionaryModelList;
    }

}
