package ru.my.chatbotslava.model;/*
package ru.ocrv.aleynikov.slavabot.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;


*/
/**
 *  Получение погоды с OpenWeatherMap
 *  link: https://openweathermap.org/
 *//*


public class WeatherService {
    public static void main(String[] args) {
        getWeather();
    }
    public static void getWeather(){
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL("https://lenta.ru/rss");

            bufferedReader = new BufferedReader(new InputStreamReader(url.openConnection(
                    new Proxy(Proxy.Type.HTTP,
                            new InetSocketAddress("172.22.201.102",3128))).getInputStream()));
            JsonReader jsonReader = new JsonReader(bufferedReader);





        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}*/
