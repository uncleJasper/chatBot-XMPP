package ru.my.chatbotslava.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Класс, для парсинга новостей с RSS-каналов
 */

public class RSSReaderService {

    /**
     * Публичный метод, который в цикле проходит по списку каналов и возвращает распарсенные новости
     * @param urlList List<String> список RSS-каналов
     * @return List<RSSFeed> список новостей
     */

    public static List<RSSFeed> letsStartPars(List<String> urlList){
        List<RSSFeed> listFeed = new ArrayList<>();
        Iterator<String> iterator = urlList.iterator();

        while (iterator.hasNext()){
            urlPars(iterator.next(),listFeed);
        }

        return listFeed;
    }


    /**
     * Приватный метод, который получает данные с RSS-канала и складывает в список listFeed.
     * По умолчанию, берутся первые 5 новостей из каждого канала.
     * Поток открывается и использование прокси.
     * @param urlStr String url-адрес RSS-канала
     * @param listFeed List<RSSFeed> Список, куда будут складваться новости
     */

    private static void urlPars(String urlStr, List<RSSFeed> listFeed){
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(urlStr);
            bufferedReader = new BufferedReader(
                    new InputStreamReader(url.openConnection(
                            new Proxy(Proxy.Type.HTTP,
                                    new InetSocketAddress("172.22.201.102",3128))).getInputStream()));

            String line;
            boolean mark = false;
            int countFeed = 0;
            String tempLink = null;
            String tempTitle = null;

            while ((line = bufferedReader.readLine()) != null){
                if (line.contains("<item>")){
                    mark = true;
                    tempLink = "";
                    tempTitle = "";
                }
                if (line.contains("<title>") && mark){
                    tempTitle = getData(line, "<title>");
                }
                if (line.contains("<link>") && mark) {
                    tempLink = getData(line, "<link>");
                }
                if (line.contains("</item>") && !tempLink.isEmpty() && !tempTitle.isEmpty()){
                    mark = false;
                    countFeed++;
                    listFeed.add(new RSSFeed(tempLink, tempTitle));
                }
                if (countFeed == 5) break;
            }
        } catch (MalformedURLException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    /**
     * Приватный метод, првиедения строки в нужный вид
     * @param line String строка для преобразования в нужный формат
     * @param replWord String заменяемая строка
     * @return String строка, приведённая в нужный вид
     */

    private static String getData(String line, String replWord){
        line = line.replace(replWord, "");
        String invertReplWord = replWord.substring(0,1) + "/" + replWord.substring(1, replWord.length());
        line = line.replace(invertReplWord, "");
        line = line.replace("<![CDATA[", "");
        line = line.replace("]]>", "");
        return line.trim();
    }
}
