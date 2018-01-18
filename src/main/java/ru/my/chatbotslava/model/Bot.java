package ru.my.chatbotslava.model;

import rocks.xmpp.addr.Jid;
import rocks.xmpp.core.XmppException;
import rocks.xmpp.core.session.TcpConnectionConfiguration;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.core.session.XmppSession;
import rocks.xmpp.core.stanza.model.Message;

/**
 * Класс реализации бота
 */
public class Bot {

    private String user;
    private String password;
    private String domain;
    private String host;
    private int port;

    /** инстанция бота */
    private static Bot instance;

    /** сессия бота на сервере XMPP */
    private XmppClient client = null;

    /**
     * Приватный конструктор
     * @param user String пользователь
     * @param password String пароль
     * @param host String хост сервера
     * @param domain String домент сервера
     * @param port String порт
     */
    private Bot(String user, String password, String host, String domain, int port) {
        this.user = user;
        this.password = password;
        this.host = host;
        this.domain = domain;
        this.port = port;
    }

    /**
     * Получение инстанции бота
     * @param user String пользователь
     * @param password String пароль
     * @param host String хост сервера
     * @param domain String домент сервера
     * @param port String порт
     * @return Bot инстанция
     */
    public static Bot getInstance(String user, String password, String host, String domain, int port){
        if (instance == null){
            instance = new Bot(user, password, host, domain, port);
        }
        return instance;
    }

    /**
     * Подключение к серверу
     * @return XmppClient сессия бота на сервере
     */
    public boolean connect(){
        boolean answer = false;
        try {

               TcpConnectionConfiguration tcpConfiguration = TcpConnectionConfiguration.builder()
                       .hostname(this.host)
                       .port(this.port)
                       .secure(false)
                       .build();

               this.client = XmppClient.create(this.domain, tcpConfiguration);
               this.client.connect(Jid.of(this.user + "@" + this.domain));
               this.client.login(this.user,this.password);
               if (this.client.getStatus() == XmppSession.Status.AUTHENTICATED){
                   answer = true;
               }

        }catch (XmppException e){
            e.printStackTrace();
        } finally {
            return answer;
        }
    }

    /**
     * Прекращение сессии пользователя на сервере
     */
    public void disconnect(){
        try {
            this.client.close();
        } catch (XmppException e) {
            e.printStackTrace();
        }
    }

    /**
     * получить инстанцию клиента (сессии на сервере)
     */
    public XmppClient getClient() {
        if (this.client.getStatus() == XmppSession.Status.AUTHENTICATED){
            return this.client;
        }else{
            return null;
        }
    }

    public void sendMessage(){
        client.sendMessage(new Message(Jid.of("***"), Message.Type.CHAT,"Hello World"));
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
