package rru.taweevat.sunantha.nattapat.enssystem;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    //Explicit
    private EditText userEditText,passEditText;
    private  String userString,passString;
    private ManageTABLE objManageTABLE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Request Database
        objManageTABLE = new ManageTABLE(this);



        //Test Add Value
        //testAddValue ();

        //Delete All data
        deleteAllData();


        //Synchronize JSON to SQLite
        synJSONtoSQLite();



    } //Main Method

    private void deleteAllData() {

        SQLiteDatabase objSqlSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DABATASE_NAME,
                MODE_PRIVATE ,null);
        objSqlSqLiteDatabase.delete(ManageTABLE.TABLE_callTABLE,null,null);
        objSqlSqLiteDatabase.delete(ManageTABLE.TABLE_newsTABLE ,null,null);
        objSqlSqLiteDatabase.delete(ManageTABLE.TABLE_userTABLE ,null,null);

    }//deleteAllData

    private void testAddValue() {

        objManageTABLE.addUser("user","pass","name","Sur","male","123456","abc@gmail.com");
        objManageTABLE.addNews("title" ,"detail" , "video" , "photo" , "day_news", "user_admin");
        objManageTABLE.addCall("Name Call" ,"123456" );
    }

    private void synJSONtoSQLite() {

        //Chang Policy
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        int intTABLE = 1;     //intTABLE ==>amount of Table
        while (intTABLE <=3) {

            String strURLuser = "http://swiftcodingthai.com/ens/php_get_user_aom.php";
            String strURLnews = "http://swiftcodingthai.com/ens/php_get_news_aom.php";
            String strURLcall = "http://swiftcodingthai.com/ens/php_get_call.php";

            //1.Create InputStream


            // 2.Create JSON String


            //3.Update to SQLite

            intTABLE +=1;
        }   //while

    }   //synJSONtoSQLite

    private void bindWidget() {
        userEditText = (EditText) findViewById(R.id.editText);
        passEditText = (EditText) findViewById(R.id.editText2);

    }//bindwidget

    public  void clickSignIN(View view) {

        userString = userEditText.getText().toString().trim();
        passString = passEditText.getText().toString().trim();

        if (userString.equals("") || passString.equals("")) {
            //Have Space
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.MyDialog(MainActivity.this, R.drawable.icon_question,
                    "Have Space", "กรุณากรอกข้อมูลให้ครบทุกช่องค่ะ");

        }else {
            //No Space


        }//if


    }//clickSignIn

    public  void clickSignUp(View view) {
        startActivity(new Intent(MainActivity.this,SingupActivity.class));
    }



}  //Main Class

