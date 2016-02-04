package rru.taweevat.sunantha.nattapat.enssystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by User on 27/1/2559.
 */
public class ManageTABLE {
    //Explicit
    private  MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public  static  final  String TABLE_userTABLE  ="userTABLE ";

    public static  final String COLUMN_id = "_id";
    public static  final String COLUMN_User = "User";
    public static  final String COLUMN_Password = "Password";
    public static  final String COLUMN_Name = "Name";
    public static  final String COLUMN_Surname = "Surname";
    public static  final String COLUMN_Sex = "Sex";
    public static  final String COLUMN_Phone = "Phone";
    public static  final String COLUMN_Email = "Email";




    public  static  final  String TABLE_callTABLE ="callTABLE";
    public static  final String COLUMN_Name_Call = "Name_Call";
    public static  final String COLUMN_Phone_Call = "Phone_Call";




    public  static  final  String TABLE_newsTABLE ="newsTABLE";
    public static  final String COLUMN_Title_News = "Title_News";
    public static  final String COLUMN_Detail_News = "Detail_News";
    public static  final String COLUMN_Video_News = "Video_News";
    public static  final String COLUMN_Photo_News = "Photo_News";
    public static  final String COLUMN_Day_News = "Day_News";
    public static  final String COLUMN_User_Admin = "User_Admin";


    public ManageTABLE(Context context) {


        objMyOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();

    }   //Construtor

    //Search user on userTABLE
    public String []searchUser(String strUser){
        try {

            String[] resultStrings = null;
            Cursor objCursor = readSqLiteDatabase.query(TABLE_userTABLE,
                    new String[]{COLUMN_id, COLUMN_User ,COLUMN_Password ,COLUMN_Name , COLUMN_Surname},
                    COLUMN_User + "=?" ,
                    new  String[]{String.valueOf(strUser)},
            null , null , null , null);
            if(objCursor !=null) {
                if (objCursor.moveToFirst()){
                    resultStrings = new  String[objCursor.getColumnCount()];
                    for (int i=0;i<objCursor.getColumnCount();i++) {
                        resultStrings [i] = objCursor.getString(i);


                    }//for
                }//if2
            }//if1
            objCursor.close();
            return resultStrings;


        }catch (Exception e){
            return null;
        }

       // return new String[0];
    }

    ///Add New Value to callTABLE
    public long addCall(String strName_call,
                        String strPhone_call) {
        ContentValues objcontentValues = new ContentValues();
        objcontentValues.put(COLUMN_Name_Call, strName_call);
        objcontentValues.put(COLUMN_Phone_Call, strPhone_call);

        return  writeSqLiteDatabase.insert(TABLE_callTABLE , null, objcontentValues);
    }

    ///Add New Value to newsTABLE
    public long addNews(String strTitle_News ,
                        String strDetail_News ,
                        String strVideo_News ,
                        String strPhoto_News ,
                        String strDay_News ,
                        String strUser_Admin) {

        ContentValues objContentValues = new  ContentValues  ();
        objContentValues.put(COLUMN_Title_News , strTitle_News);
        objContentValues.put(COLUMN_Detail_News , strDetail_News);
        objContentValues.put(COLUMN_Video_News , strVideo_News);
        objContentValues.put(COLUMN_Photo_News , strPhoto_News);
        objContentValues.put(COLUMN_Day_News , strDay_News);
        objContentValues.put(COLUMN_User_Admin , strUser_Admin);

        return writeSqLiteDatabase.insert(TABLE_newsTABLE ,null,objContentValues);
    }

    //Add New Value to userTABLE
    public long addUser(String strUser,
                        String strPassword,
                        String strName,
                        String strSurname,
                        String strSex,
                        String strPhone,
                        String strEmail) {


        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_User,strUser);
        objContentValues.put(COLUMN_Password,strPassword);
        objContentValues.put(COLUMN_Name,strName);
        objContentValues.put(COLUMN_Surname,strSurname);
        objContentValues.put(COLUMN_Sex,strSex);
        objContentValues.put(COLUMN_Phone,strPhone);
        objContentValues.put(COLUMN_Email,strEmail);

        return writeSqLiteDatabase.insert(TABLE_userTABLE,null,objContentValues);
    }



}   //Main Class
