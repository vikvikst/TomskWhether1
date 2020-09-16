package ru.vikvikst.tomskweather1;

public class StateOfADay {
    private int imgWeather;
    private String temp;
    private String wind;
    private String pressure;


    public int getImgWeather() {
        return imgWeather;
    }

    public String getTemp() {
        return temp;
    }

    public String getWind() {
        return wind;
    }

    public String getPressure() {
        return pressure;
    }

    public void setImgWeather(int imgWeather) {
        this.imgWeather = imgWeather;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
}
