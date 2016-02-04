package rru.taweevat.sunantha.nattapat.enssystem;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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

        int intTABLE = 1;   //intTABLE ==> amount of Table
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
            String strJSON = null;

            try {

                BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;
                while ((strLine = objBufferedReader.readLine()) != null) {
                    objStringBuilder.append(strLine);
                }   // while
                objInputStream.close();
                strJSON = objStringBuilder.toString();

            } catch (Exception e) {
                Log.d("ens", "JSON ==> " + e.toString());
            }

            //3. Update to SQLite
            try {

                JSONArray objJsonArray = new JSONArray(strJSON);
                for (int i = 0; i < objJsonArray.length(); i++) {

                    JSONObject jsonObject = objJsonArray.getJSONObject(i);

                    switch (intTABLE) {
                        case 1:

                            //userTABLE
                            String strUser = jsonObject.getString(ManageTABLE.COLUMN_User);
                            String strPassword = jsonObject.getString(ManageTABLE.COLUMN_Password);
                            String strName = jsonObject.getString(ManageTABLE.COLUMN_Name);
                            String strSurname = jsonObject.getString(ManageTABLE.COLUMN_Surname);
                            String strSex = jsonObject.getString(ManageTABLE.COLUMN_Sex);
                            String strPhone = jsonObject.getString(ManageTABLE.COLUMN_Phone);
                            String strEmail = jsonObject.getString(ManageTABLE.COLUMN_Email);

                            objManageTABLE.addUser(strUser, strPassword, strName, strSurname,
                                    strSex, strPhone, strEmail);

                            break;
                        case 2:

                            //newTABLE
                            String strTitle_News = jsonObject.getString(ManageTABLE.COLUMN_Title_News);
                            String strDetail_News = jsonObject.getString(ManageTABLE.COLUMN_Detail_News);
                            String strVideo_News = jsonObject.getString(ManageTABLE.COLUMN_Video_News);
                            String strPhoto_News = jsonObject.getString(ManageTABLE.COLUMN_Photo_News);
                            String strDay_News = jsonObject.getString(ManageTABLE.COLUMN_Day_News);
                            String strUser_Admin = jsonObject.getString(ManageTABLE.COLUMN_User_Admin);

                            objManageTABLE.addNews(strTitle_News, strDetail_News, strVideo_News,
                                    strPhoto_News, strDay_News, strUser_Admin);

                            break;
                        case 3:

                            //callTABLE
                            String strName_Call = jsonObject.getString(ManageTABLE.COLUMN_Name_Call);
                            String strPhone_Call = jsonObject.getString(ManageTABLE.COLUMN_Phone_Call);

                            objManageTABLE.addCall(strName_Call, strPhone_Call);

                            break;
                    }   // swirch

                }   // for

            } catch (Exception e) {
                Log.d("ens", "Update ==> " + e.toString());
            }


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

            CheckUserAndPassword();

        }   // if


    }   // clickSignIn

    private void CheckUserAndPassword() {
        try {
            String [] resultStrings = objManageTABLE.searchUser(userString);

            //Check Password
            if(passwordString.equals(resultStrings[2])) {

                //Password True
            } else {

                //Password False
                MyAlertDialog objMyAlertDialog = new MyAlertDialog();
                objMyAlertDialog.MyDialog(MainActivity.this, R.drawable.icon_question,
                        "Password False", "โปรดกรอก Password ใหม่ คุณกรอกผิด");
            }


        }catch (Exception e){
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.MyDialog(MainActivity.this, R.drawable.icon_question, "No This User",
                    "ไม่มี" + userString + "ในฐานข้อมูลของเรา");
        }
    }


    public void clickSignUp(View view) {
        startActivity(new Intent(MainActivity.this, SingupActivity.class));
    }


}   // Main Class