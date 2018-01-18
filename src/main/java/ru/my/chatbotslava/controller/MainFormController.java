package ru.my.chatbotslava.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import ru.my.chatbotslava.model.Bot;

public class MainFormController {

    String botUser = "***";
    String botPassword = "***";
    String botHost = "***";
    String botDomain = "***";
    int botPort = 0000;
    private Bot bot = null;

    /** поле статуса */
    @FXML
    private Label statusText;

    /** кнопкка подключения к серверу (начать сессию) */
    @FXML
    private Button playButton;

    /** кнопкка отключения от сервера (завершить сессию) */
    @FXML
    private Button stopButton;

    /**
     * метод инициализации формы
     */
    @FXML
    private void initialize() {
        bot = Bot.getInstance(botUser, botPassword, botHost, botDomain, botPort);
    }

    /**
     * кнопка запуска
     */
    @FXML
    private void pressPlayButton(){
        if (bot.connect()){
            changeStatusClient();
        }else{
            System.err.println("error connection");
        }
    }

    /**
     * кнопка стоп
     */
    @FXML
    private void pressStopButton(){
        bot.disconnect();
        changeStatusClient();
        System.out.println("stop");
    }

    /**
     * меняем поле статуса бота и доступность кнопок
     */
    private final void changeStatusClient(){
        if (statusText.getText().equals("Disconnected")){
            statusText.setText("Connected");
            statusText.setTextFill(Color.rgb(57,201,50));
        }else{
            statusText.setText("Disconnected");
            statusText.setTextFill(Color.RED);
        }
        playButton.setDisable(!playButton.isDisable());
        stopButton.setDisable(!stopButton.isDisable());
    }
}
