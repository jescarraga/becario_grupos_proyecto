package com.company;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static ArrayList<Integer[]> proyectosYvotos(ArrayList<ArrayList<String>> datos){

        //Agrega los numerales de los proyectos a un arraylist
        ArrayList<Integer> proyectos = new ArrayList<>();
        for (int i= 0 ; i < datos.size(); i++ ){
            for (int j= 1; j < 4; j++){
                proyectos.add(Integer.parseInt(datos.get(i).get(j)));
            }
        }

        //los ordena de menor a mayor
        Collections.sort(proyectos);



        //Los envia a un Arraylist con su número de proyecto y sus votos
        ArrayList<Integer[]> grupos_finales = new ArrayList<>();

        int i = 0;
        while (i != proyectos.size()) {
            Integer[] numeros = new Integer[2];
            int contador = 0;
            int j = i;
            System.out.println(i);
            do{ //99 se arregla
                if (((j != datos.size()*3)) && (proyectos.get(i) == proyectos.get(j))){
                    j++;
                    contador++;
                }else{
                    numeros[1]=contador;
                    numeros[0]= proyectos.get(i);
                    grupos_finales.add(numeros);
                    i = j;
                }
            }while(i!= j);
        }

        System.out.println("--------------------votos-------------------");
        for (int j = 0; j < grupos_finales.size(); j++) {
            System.out.print("grupo " + grupos_finales.get(j)[0]);
            System.out.println("votos " + grupos_finales.get(j)[1]);
        }
        System.out.println("--------------------votos_final-------------------");
        return grupos_finales;

    }

    public static  ArrayList<Integer[]> ordenamiento(ArrayList<Integer[]> vector){
        for (int i=1; i < vector.size(); i++) {
            //int aux = vector[i]; //valor
            Integer aux[] = vector.get(i);
            int j; //apuntador
            //for (j=i-1; j >= 0 && vector[j] > aux; j--){
            for (j=i-1; (j >= 0) && (vector.get(j)[1] > aux[1]); j--){
                //vector[j+1] = vector[j];
                vector.set(j+1,vector.get(j));
            }
            //vector[j+1] = aux;
            vector.set(j+1,aux);
        }

        ArrayList<Integer[]> ordenado = new ArrayList<>();

        for (int i = 0; i < vector.size(); i++) {
            ordenado.add(i, vector.get(vector.size()-1-i));
        }

        System.out.println("--------------------votos ordenasdos-------------------");
        for (int j = 0; j < ordenado.size(); j++) {
            System.out.print("grupo " + ordenado.get(j)[0]+" ");
            System.out.println("votos " + ordenado.get(j)[1]);
        }
        /*
        System.out.println("--------------------votos ordenasdos-------------------");
        for (int j = 0; j < vector.size(); j++) {
            System.out.print("grupo " + vector.get(j)[0]+" ");
            System.out.println("votos " + vector.get(j)[1]);
        }

         */
        System.out.println("--------------------votos ordenasdos-------------------");

        return  vector;
    }

    public static void main(String[] args) {
        lectorCSV lectorCSV = new lectorCSV();
        ArrayList<ArrayList<String>> datos = lectorCSV.leerCSV();

        ArrayList<Integer[]> lista_grupos = new ArrayList<>();
        lista_grupos= proyectosYvotos(datos);
        lista_grupos = ordenamiento(lista_grupos);

    }
}
