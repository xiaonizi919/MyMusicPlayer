package com.example.administrator.mymusicplayer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private Context context;
    private static String dbName="Music.db";
    private static int dbVersion=1;
    private SQLiteDatabase db;

    public DbHelper(Context context) {
        super(context, dbName, null, dbVersion);
        db=getWritableDatabase();
    }

    public static void createTable(){

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS music " +
                "(_id INTEGER primary key, singer varchar(20), songName varchar(20)" +
                "path text,duration INTEGER,size long,album varchar(20),albumId varchar(10)" +
                "albumArt varchar(10),state INTEGER,bufferPercent INTEGER,progress float," +
                "totalTime varchar(20),currTime varchar(20),currPosition INTEGER)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
