package com.company;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;



public class Main {

    static final int num_estudiantes = 39;

    public static ArrayList<Integer[]> proyectosYvotos(ArrayList<ArrayList<String>> datos){

        //Agrega los numerales de los proyectos a un arraylist
        ArrayList<Integer> proyectos = new ArrayList<>();
        for (int i= 0 ; i < datos.size(); i++ ){

            proyectos.add(Integer.parseInt(datos.get(i).get(1)));

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
            do{
                if (((j != datos.size())) && (proyectos.get(i) == proyectos.get(j))){
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
        /*
        System.out.println("--------------------votos-------------------");
        for (int j = 0; j < grupos_finales.size(); j++) {
            System.out.print("grupo " + grupos_finales.get(j)[0]);
            System.out.println("votos " + grupos_finales.get(j)[1]);
        }
        System.out.println("--------------------votos_final-------------------");
         */
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
        System.out.println("--------------------votos ordenasdos-------------------");

        return  ordenado;
    }

    public static void crear_grupos(ArrayList<ArrayList<String>> datos,ArrayList<Integer[]> grupos){
        double cantidad_grupos = Math.ceil((double)num_estudiantes / 5.00) ;
        System.out.println(cantidad_grupos);

        //Crea la lista de los grupos
        ArrayList<ArrayList<String>> grupos_f = new ArrayList<>();

        //Crea una lista por cada grupo y le agrega el numero del grupo como primer elemento
        for (int i = 0; i < cantidad_grupos; i++) {
            grupos_f.add(new ArrayList<String>());

            //Obtener numero grupo
            String num_grupo = String.valueOf(grupos.get(i)[0]);

            //ingresar numero del grupo a la primera componente de cada lista de grupo
            grupos_f.get(i).add(0,num_grupo);

        }

        System.out.println(grupos_f);


        //Agregar estudiantes a la lista
        // Lo hace por cada estudiante
        for (int i = 0; i < datos.size(); i++) {
            // Revisa grupo a grupo por cada estudiante
            boolean asignado = false;

            for (int j = 1; j < 5; j++) {
                //Compara cada prioridad con la lista de grupos

                if (asignado == false) {
                    for (int k = 0; k < grupos_f.size(); k++) {

                        //Si encuentra una coincidencia agrega al estudiante en el grupo y sale del ciclo
                        if ((j < 4)&&(datos.get(i).get(j).compareTo(String.valueOf(grupos_f.get(k).get(0))) == 0) && (grupos_f.get(k).size() != 6)){
                            grupos_f.get(k).add(datos.get(i).get(0));
                            asignado = true;
                            break;
                        }

                        //EStudiante con prioridad fuera de los proyectos populares
                        if (j == 4) {
                            while(asignado == false){
                                //Crearemos un numero random de grupo asignaremos al estudiante en este grupo
                                Random rn = new Random();
                                int numero = (int) (Math.random() * grupos_f.size());
                                if (grupos_f.get(numero).size() < 6) {
                                    grupos_f.get(numero).add(datos.get(i).get(0));
                                    asignado = true;
                                    break;
                                }
                            }
                        }

                    }
                }else{
                    break;
                }

            }
        }
        System.out.println(grupos_f);
    }


    public static void main(String[] args) {
        lectorCSV lectorCSV = new lectorCSV();
        ArrayList<ArrayList<String>> datos = lectorCSV.leerCSV();

        ArrayList<Integer[]> lista_grupos = new ArrayList<>();
        lista_grupos= proyectosYvotos(datos);
        lista_grupos = ordenamiento(lista_grupos);

        crear_grupos(datos,lista_grupos);
    }
}
