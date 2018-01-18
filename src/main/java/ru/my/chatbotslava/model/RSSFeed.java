package ru.my.chatbotslava.model;


/**
 * Класс, описывающий сообщения
 */

public class RSSFeed {
   String link;
   String title;

   public RSSFeed(String link, String title) {
       this.link = link;
       this.title = title;
   }

   public String getLink() {
       return link;
   }

   public String getTitle() {
       return title;
   }

   @Override
   public String toString() {
       return "Item{" +
               "link='" + link + '\'' +
               ", title='" + title + '\'' +
               '}';
   }
}
