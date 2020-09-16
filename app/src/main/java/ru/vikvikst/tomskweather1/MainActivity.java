package ru.vikvikst.tomskweather1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "mylog";
    private final String SITE1 = "http://pogodavtomske.ru/forecast10.html";
    //StateOfADay stateOfADay;
    ArrayList<StateOfADay> stateOfADaysList;
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
            //Log.d(TAG, "getInfoFromSite1: " + document.text());
            Elements tables = document.getElementsByTag("tbody");
            Element table = tables.get(2);
            Elements elements1 = table.children().get(1).children();
            //++температура сегодня ночь день
            Log.d(TAG, "Температура сегодня ночью " + elements1.get(2).children().get(0).text() + ", Температура днем " + elements1.get(2).children().get(2).text());
            //++ветер
            Log.d(TAG, "wind " + elements1.get(3).children().text());

            String direction = elements1.get(3).getAllElements().text();
            String[] direcionWInd = getWindDireciton(direction);
            Log.d(TAG, "direction 1" + direcionWInd[0]+ "T");
            Log.d(TAG, "direction 2" + direcionWInd[1]+ "T");
            String pressure = elements1.get(4).text();
            Log.d(TAG, "давление " + pressure + "мм рт.с.");
            String temp = elements1.get(2).children().get(2).text();
            Log.d(TAG, "температура" + temp);
            stateOfADaysList = new ArrayList<>();
            //все поля
            for (int i = 0; i < 7; i++) {
                //Log.d(TAG, "item : " + i + " = " + elements1.get(i).text());
                StateOfADay stateOfADay = new StateOfADay();
                stateOfADay.setImgWeather(R.drawable.no_image);
                stateOfADay.setTemp(temp);
                stateOfADay.setWind(direcionWInd[0]);
                stateOfADay.setWind1(direcionWInd[1]);
                stateOfADay.setPressure(pressure);
                Log.d(TAG, "item: " + i + " " + stateOfADay.toString());
                stateOfADaysList.add(stateOfADay);
            }
//
//            for (StateOfADay item: stateOfADaysList) {
//                Log.d(TAG, "item: " + item.toString());
//            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //returns 2 values in string array . example: южный,юго-западный
    private String[] getWindDireciton(String direction) {
        String[] arr = direction.split("м/c");
        String text = arr[1].trim();
        text = text.substring(0, text.lastIndexOf(' '));
        text.replaceAll("\\s","");
        return text.split(",");
    }
}