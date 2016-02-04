package rru.taweevat.sunantha.nattapat.enssystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class SingupActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText, nameEditText, surnameEditText,
            phoneEditText, emailEditText;
    private RadioGroup sexRadioGroup;

    private String userString, passwordString, nameString, surnameString,
            phoneString, emailString, sexString;
    private boolean statusABoolean = true;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        //Bind Widget
        bindWidget();
        //RaioGroup Controller
        ragController();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }//Main Method

    private void ragController() {

        sexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                statusABoolean = false;

                switch (checkedId) {
                    case  R.id.radioButton:
                        sexString = getResources().getString(R.string.male);

                         break;
                    case R.id.radioButton2:
                        sexString = getResources().getString(R.string.female);
                      break;
                }// switch

            }
        });


    }//ragController

    private void bindWidget() {
        userEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);
        nameEditText = (EditText) findViewById(R.id.editText5);
        surnameEditText = (EditText) findViewById(R.id.editText6);
        phoneEditText = (EditText) findViewById(R.id.editText8);
        emailEditText = (EditText) findViewById(R.id.editText9);


        sexRadioGroup = (RadioGroup) findViewById(R.id.ragSex);

    }   //bindWidget

    public void clickSignUpSave(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        nameString = nameEditText.getText().toString().trim();
        surnameString = surnameEditText.getText().toString().trim();

        phoneString = phoneEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();


        if (checkSpack() || statusABoolean) {

            //Have Space

            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.MyDialog(SingupActivity.this,
                    R.drawable.icon_question, "กรอกข้อมูลไม่ครบ",
                    "กรุณากรอกข้อมูล และ เลือกข้อมูลให้ครบ ค่ะ");


        } else {
        }   //No Space
        confirmSignUp();


    }//clickSignUpSave

    private void confirmSignUp() {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.logo48);
        objBuilder.setTitle("โปรดตรวจข้อมูล");
        objBuilder.setMessage("User = " + userString + "\n" +
                "Password = " + passwordString + "\n" +
                "ชื่อ = " + nameString + "\n" +
                "นามสกุล = " + surnameString + "\n" +
                "เพศ = " + sexString + "\n" +
                "เบอร์โทร = " + phoneString + "\n" +
                "Email = " + emailString);
    objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                updateValueToMySQL();

            }
        });

        objBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });




        objBuilder.show();


    }// confirmSignUp

    private void updateValueToMySQL() {
        //Set Policy
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        //Update to MySQL
        try {
            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();

            objNameValuePairs.add(new BasicNameValuePair("isAdd","true"));
            objNameValuePairs.add(new BasicNameValuePair("User",userString));
            objNameValuePairs.add(new  BasicNameValuePair("Password",passwordString));
            objNameValuePairs.add(new BasicNameValuePair("Name",nameString));
            objNameValuePairs.add(new BasicNameValuePair("Surname",surnameString));
            objNameValuePairs.add(new BasicNameValuePair("Sex",sexString));
            objNameValuePairs.add(new BasicNameValuePair("Phone",phoneString));
            objNameValuePairs.add(new BasicNameValuePair("Email",emailString));
            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://swiftcodingthai.com/ens/php_add_data_master.php");

            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs,"UTF-8"));
            objHttpClient.execute(objHttpPost);


            Toast.makeText(SingupActivity.this, "Update New Value Succuss",
                    Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Toast.makeText(SingupActivity.this,"Cannot Connect Internet",
                    Toast.LENGTH_SHORT).show();
        }


    }//updateValueToMySQL


    private boolean checkSpack() {
        return userString.equals("") ||
                passwordString.equals("") ||
                nameString.equals("") ||
                surnameString.equals("") ||
                phoneString.equals("") ||
                emailString.equals("");
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Singup Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://rru.taweevat.sunantha.nattapat.enssystem/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Singup Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://rru.taweevat.sunantha.nattapat.enssystem/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}   //Main Class

