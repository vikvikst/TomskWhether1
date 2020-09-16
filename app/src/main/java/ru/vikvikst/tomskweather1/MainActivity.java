package ru.vikvikst.tomskweather1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.w3c.dom.Document;

public class MainActivity extends AppCompatActivity {

    public String TAG = "mylog";

    private Document document;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}