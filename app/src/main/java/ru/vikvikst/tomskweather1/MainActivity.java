package ru.vikvikst.tomskweather1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "mylog";
    private final String SITE1 = "http://pogodavtomske.ru/forecast10.html";

    private Document document;
    Runnable runnable;
    Thread threadForSite1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Log.d(TAG, "onCreate: ");
        setConnectToSite1();
    }

    private void setConnectToSite1() {
        runnable = (Runnable) new Runnable() {
            @Override
            public void run() {
                getInfoFromSite1();
            }
        };
        threadForSite1 = new Thread(runnable);
        threadForSite1.start();
    }


    // получаем данные с помощью jsoup
    private void getInfoFromSite1() {

        ImageView imgWeather = (ImageView) findViewById(R.id.img_weather);
        TextView tvTemp = (TextView) findViewById(R.id.tv_temp);
        TextView tvWind = (TextView) findViewById(R.id.tv_direction_wind);
        TextView tvPressure = (TextView) findViewById(R.id.tv_pressure);

        try {
            // TODO: 16.09.2020 обработать  сотояния отличные от  jn statusCode == 200
            // https://stackoverflow.com/questions/10245519/handling-connection-errors-and-jsoup
            document = Jsoup.connect(SITE1).get();
            Log.d(TAG, "getInfoFromSite1: " + document.text());
            Elements tables = document.getElementsByTag("tbody");
            Element table = tables.get(2);
            //Log.d(TAG, "getInfoFromSite1: " + tables.get(2).text());
            Elements elements = table.children();
            //Log.d(TAG, "getInfoFromSite1: " + elements.get(1).text());
            Elements elements1 = table.children().get(1).children();
            //все поля текущего дня
            Log.d(TAG, "getInfoFromSite1: " + elements1.get(0).text());
            for (int i = 0; i < 7; i++) {
                Log.d(TAG, "item : " + i + " = " + elements1.get(i).text());
            }
            //температура сегодня ночь день
            Log.d(TAG, "Температура сегодня ночью " + elements1.get(2).children().get(0).text() + ", Температура днем " + elements1.get(2).children().get(2).text());






        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}