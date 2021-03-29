package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;

public class lectorCSV {
    public ArrayList<ArrayList<String>> leerCSV(){
        ArrayList<ArrayList<String>> datos = new ArrayList<>();
        Path filePath = Paths.get("/media/jordan/DATOS/Programacion/Becario/IISyC-Selección proyectos de mayor interés.csv");
        try {
            BufferedReader br = Files.newBufferedReader(filePath);
            String linea;
            while ((linea = br.readLine()) != null){
                String[] datosDelinea = linea.split(",");
                ArrayList<String> datosTemporal = new ArrayList<>();
                Collections.addAll(datosTemporal, datosDelinea);
                datos.add(datosTemporal);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return datos;
    }

    public ArrayList<String> leerCSV_no_participantes(){
        ArrayList<String> datos = new ArrayList<>();
        Path filePath = Paths.get("/media/jordan/DATOS/Programacion/Becario/No_entregaron.csv");
        try {
            BufferedReader br = Files.newBufferedReader(filePath);
            String linea;
            while ((linea = br.readLine()) != null){
                datos.add(linea);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(datos);
        return datos;
    }
}

