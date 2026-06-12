package com.fitmanage.smartgym.controller;

import com.fitmanage.smartgym.model.RegistroCentral;
import com.fitmanage.smartgym.model.SensorHilo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class GymController {
    // Variables enlazadas automáticamente con el archivo FXML mediante sus fx:id
    @FXML
    private Label labelTemp;
    @FXML
    private Label labelHum;
    @FXML
    private Pane panelAlerta;

    private RegistroCentral modelo;

    @FXML
    public void initialize() {
        this.modelo = new RegistroCentral();
        SensorHilo tareaTemp = new SensorHilo("TEMPERATURA" , modelo , this);
        SensorHilo tareaHUM = new SensorHilo("HUMEDAD" , modelo , this);
        new Thread(tareaTemp).start();
        new Thread(tareaHUM).start();
    }


public void actualizarPantalla() {
     Platform.runLater(() -> {
         double temp = modelo.getUltimaTemperatura();
         double hum = modelo.getUltimaHumedad();
         labelTemp.setText(String.format(("Temperatura: %.1f °C") , temp));
         labelHum.setText(String.format(("Humedad: %.1f %") ,hum));
         if(temp>35 || hum>20){
             panelAlerta.setStyle("-fx-background-color: #FF0000 ; -fx-background-radius: 10;");
             ((Label)) panelAlerta.getChildren().get((0)).setText("Alertaa");
         }
         else {
             panelAlerta.setStyle("-fx-background-color: #00FF00 ; -fx-background-radius: 10;");
             ((Label)) panelAlerta.getChildren().get((0)).setText("Estado: Normal");
         }
     });
    }
}