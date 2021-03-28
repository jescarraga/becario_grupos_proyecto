package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class lectortxt {
    public void leertxt(){
        StringBuilder sb = new StringBuilder();
        Path filePath = Paths.get("/media/jordan/DATOS/Programacion/Becario/prueba.txt");
        try {
            BufferedReader br = Files.newBufferedReader(filePath);
            String linea;
            while ((linea = br.readLine()) != null){
                sb.append(linea).append("\n");
            }
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
