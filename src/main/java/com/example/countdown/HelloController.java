package com.example.countdown;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController {
    @FXML
    public Button startBtn;
    @FXML
    public TextField dateTime;
    @FXML
    public Label timeLeftLbl;

    private Timer t;
    public LocalDateTime expiration;

    public void startBtnClick() {
        //expiration = LocalDateTime.of(2021, 4, 32, 5, 30, 0);
        String timeText = dateTime.getText();
        if (timeText.length() == 0) {
            expiration = LocalDateTime.now().plusMinutes(5);
        }
        test();
    }

    private void test() {
        LocalDateTime testTime = LocalDateTime.now().plusSeconds(12);
        t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> { setTime(testTime); });
            }
        }, 0, 1000);

    }

    private void setTime(LocalDateTime timeUntil) {
        if (timeUntil.isBefore(LocalDateTime.now().plusSeconds(1))) {
            t.cancel();
            timeLeftLbl.setText("Lejart");
        } else {
            Period p = Period.between(LocalDateTime.now().toLocalDate(), timeUntil.toLocalDate());
            Duration d = Duration.between(LocalDateTime.now(), timeUntil);

            int years = p.getYears();
            int months = p.getMonths();
            int days = p.getDays();

            int hours = d.toHoursPart();
            int minutes = d.toMinutesPart();
            int secs = d.toSecondsPart();

            String text = String.format("%d év %d hónap %d nap %02d:%02d:%02d", years, months, days, hours, minutes, secs);

            timeLeftLbl.setText(text);
        }
    }
}