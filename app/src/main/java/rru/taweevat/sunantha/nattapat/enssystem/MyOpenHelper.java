package rru.taweevat.sunantha.nattapat.enssystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 27/1/2559.
 */
public class MyOpenHelper extends SQLiteOpenHelper {


    //Explicit
    public static final String DABATASE_NAME = "ens.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_USER_TABLE = "create table userTABLE (" +
            "_id integer primary key, " +
            "User text," +
            "Password text, " +
            "Name text, " +
            "Surname text, " +
            "Sex text, " +
            "Phone text, " +
            "Email text);";

    private static final String CREATE_CALL_TABLE = "create table callTABLE (" +
            "_id integer primary key, " +
            "Name_Call text, " +
            "Phone_Call text);";
    private static final String CREATE_NEWS_TABLE = "create table newsTABLE(" +
            "_id integer primary key, " +
            "Title_News text, " +
            "Detail_News text, " +
            "Video_News text, " +
            "Photo_News text, " +
            "Day_News text," +
            "User_Admin text);";

    public MyOpenHelper(Context context) {
        super(context, DABATASE_NAME, null, DATABASE_VERSION);
    }   //Constructor


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_CALL_TABLE);
        sqLiteDatabase.execSQL(CREATE_NEWS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}  // Main Class
