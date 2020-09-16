package ru.vikvikst.tomskweather1;

public class StateOfADay {
    private int imgWeather;
    private String temp;
    private String wind;
    private String wind1;

    @Override
    public String toString() {
        return "StateOfADay{" +
                "imgWeather=" + imgWeather +
                ", temp='" + temp + '\'' +
                ", wind='" + wind + '\'' +
                ", wind1='" + wind1 + '\'' +
                ", pressure='" + pressure + '\'' +
                '}';
    }

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

    public String getWind1() {
        return wind1;
    }

    public void setWind1(String wind1) {
        this.wind1 = wind1;
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
