package rru.taweevat.sunantha.nattapat.enssystem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

/**
 * Created by User on 19/1/2559.
 */
public class MyAlertDialog {

    public void MyDialog(Context context,
                         int intIcon,
                         String strTitle,
                         String strMessage){
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(context);
        objBuilder.setIcon(intIcon);
        objBuilder.setTitle(strTitle);
        objBuilder.setMessage(strMessage);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        objBuilder.show();



    }



}   //Main Class
