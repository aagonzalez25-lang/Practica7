package com.fitmanage.smartgym.model;

import com.fitmanage.smartgym.controller.GymController;
import java.util.Random;

// TODO: Issue #2 - Modificar la firma de la clase para que pueda ejecutarse en un hilo secundario

public class SensorHilo implements Runnable {
    private final String tipoSensor; // Recibe "TEMPERATURA" o "HUMEDAD"
    private final RegistroCentral modelo;
    private final GymController controlador;
    private final Random random = new Random();

    public SensorHilo(String tipoSensor, RegistroCentral modelo, GymController controlador) {
        this.tipoSensor = tipoSensor;
        this.modelo = modelo;
        this.controlador = controlador;
    }
    @Override
    public void run(){
        boolean ejecutar= true;
        while(ejecutar){
            try{
                if (tipoSensor.equals("TEMPERATURA")){
                    int  maximo=45;
                    int minimo=15;
                    int valor = random.nextInt((maximo - minimo) + 1) + minimo;
                    modelo.registrarTemperatura(valor);
                }
                else if (tipoSensor.equals("HUMEDAD")){
                    int  maximo=90;
                    int minimo=10;
                    int valor = random.nextInt((maximo - minimo) + 1) + minimo;
                    modelo.registrarHumedad(valor);
                }
                controlador.actualizarPantalla();
                Thread.sleep(2000);
            }catch (InterruptedException e){
                ejecutar = false;
                System.out.println("Hilo de " + tipoSensor + " detenido limpiamente.");
            }

        }
    }
}
