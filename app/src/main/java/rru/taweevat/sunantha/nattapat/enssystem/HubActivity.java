package rru.taweevat.sunantha.nattapat.enssystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HubActivity extends AppCompatActivity {

    private TextView informTextView, newsTextView ,searchTextView , statisticTextView , callTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);

        //Bind Widget
        bindWidget();

    }

    //Main Method

    public void clickTHAI(View view) {

    }

    public void clickENG(View view) {

    }
    private void bindWidget(){
        informTextView = (TextView) findViewById(R.id.textView9);
        newsTextView = (TextView) findViewById(R.id.textView10);
        searchTextView = (TextView) findViewById(R.id.textView11);
        statisticTextView = (TextView) findViewById(R.id.textView12);
        callTextView = (TextView) findViewById(R.id.textView13);
    }

}//Main Class
