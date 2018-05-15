package com.ct.fitness.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elia on 12/19/2017.
 */

public class ExerciseSettings extends SQLiteOpenHelper {

    private  static final int dbVersion=1;
    private static final String dbName="exercisesettings.db";
    private static final String tableName="WorkoutDays";

    public ExerciseSettings(Context context) {
        super(context,dbName,null,dbVersion);


    }

    public int getSettingMode(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        SQLiteQueryBuilder sqLiteQueryBuilder=new SQLiteQueryBuilder();
        String[] sqlSelect={"Mode"};
        String sqlTable="Settings";
        sqLiteQueryBuilder.setTables(sqlTable);

        Cursor cursor=sqLiteQueryBuilder.query(sqLiteDatabase,sqlSelect,null,null,null,null,null);
        cursor.moveToFirst();

        return cursor.getInt(cursor.getColumnIndex("Mode"));
    }



    public void setSettingsMode(int value){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        String query="UPDATE Settings SET Mode ="+value;
        sqLiteDatabase.execSQL(query);

    }




    public List<String> getWorkoutDays(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        SQLiteQueryBuilder sqLiteQueryBuilder=new SQLiteQueryBuilder();
        String[] sqlSelect={"day"};
        String sqlTable="WorkoutDays";
        sqLiteQueryBuilder.setTables(sqlTable);

        Cursor cursor=sqLiteQueryBuilder.query(sqLiteDatabase,sqlSelect,null,null,null,null,null);
        List<String> result=new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
result.add(cursor.getString(cursor.getColumnIndex("day")));
            }while (cursor.moveToNext());
        }
        return result;
    }

    public void saveDay(String value){
        String sqlTable="WorkoutDays";
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        String query=String.format("INSERT INTO WorkoutDays(day) VALUES %s",value);
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query ="CREATE TABLE WorkoutDays(id INTEGER PRIMARY KEY AUTOINCREMENT , day TEXT )";

        String query2 ="CREATE TABLE Settings(id INTEGER PRIMARY KEY AUTOINCREMENT , Mode INTEGER )";
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(query2
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
