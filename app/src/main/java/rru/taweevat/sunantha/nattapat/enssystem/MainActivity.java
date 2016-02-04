package rru.taweevat.sunantha.nattapat.enssystem;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;
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
        //testAddValue();

        //Delete All data
        deleteAllData();

        //Synchronize JSON to SQLite
        synJSONtoSQLite();


    }   // Main Method

    private void deleteAllData() {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DABATASE_NAME,
                MODE_PRIVATE, null);
        objSqLiteDatabase.delete(ManageTABLE.TABLE_callTABLE, null, null);
        objSqLiteDatabase.delete(ManageTABLE.TABLE_newsTABLE, null, null);
        objSqLiteDatabase.delete(ManageTABLE.TABLE_userTABLE, null, null);

    }   // deleteAllData

    private void testAddValue() {

        objManageTABLE.addUser("user", "pass", "name", "Sur", "male", "123456", "abc@gmail.com");

        objManageTABLE.addNews("title", "detail", "video", "photo", "day news", "user admin");

        objManageTABLE.addCall("Name Call", "123456");

    }   // testAddValue

    private void synJSONtoSQLite() {

        //Change Policy
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        int intTABLE = 1 ;   //intTABLE ==> amount of Table
        while (intTABLE <= 3) {

            String strURLuser = "http://swiftcodingthai.com/ens/php_get_user_master.php";
            String strURLnews = "http://swiftcodingthai.com/ens/php_get_news_master.php";
            String strURLcall = "http://swiftcodingthai.com/ens/php_get_call.php";

            //1. Create InputStream
            InputStream objInputStream = null;
            HttpPost objHttpPost = null;

            try {

                HttpClient objHttpClient = new DefaultHttpClient();

                switch (intTABLE) {
                    case 1:
                        objHttpPost = new HttpPost(strURLuser);
                        break;
                    case 2:
                        objHttpPost = new HttpPost(strURLnews);
                        break;
                    case 3:
                        objHttpPost = new HttpPost(strURLcall);
                        break;
                }   // switch

                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();

            } catch (Exception e) {
                Log.d("ens", "InputStream ==> " + e.toString());
            }


            //2. Create JSON String

            //3. Update to SQLite



            intTABLE += 1;
        }   // while



    }   // synJSONtoSQLite

    private void bindWidget() {

        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);

    }   // bindWidget

    public void clickSignIn(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        if (userString.equals("") || passwordString.equals("")) {
            //Have Space
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.MyDialog(MainActivity.this, R.drawable.icon_question,
                    "Have Space", "Please Fill All Every Blank");

        } else {
            //No Space

        }   // if


    }   // clickSignIn


    public void clickSignUp(View view) {
        startActivity(new Intent(MainActivity.this, SingupActivity.class));
    }


}   // Main Class