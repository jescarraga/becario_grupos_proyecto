package com.company;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;



public class Main {


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

        /*
        System.out.println("--------------------votos ordenasdos-------------------");
        for (int j = 0; j < ordenado.size(); j++) {
            System.out.print("grupo " + ordenado.get(j)[0]+" ");
            System.out.println("votos " + ordenado.get(j)[1]);
        }
        System.out.println("--------------------votos ordenasdos-------------------");

         */

        return  ordenado;
    }

    //Crea grupos con los estudiantes que participaron solamente
    public static ArrayList<ArrayList<String>> crear_grupos(
            ArrayList<ArrayList<String>> datos,
            ArrayList<Integer[]> grupos,
            int num_estudiantes_participaron,
            double cantidad_estudiantes_grupo){

        double cantidad_grupos = Math.ceil((double)num_estudiantes_participaron / cantidad_estudiantes_grupo) ;
        System.out.println("cantidad de grupos con los estudiantes que si participaron= "+ cantidad_grupos);

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
                        //Estudiantes por gruṕo = 4, por lo tanto cada arraylist de grupo debe tener 5 estapcio
                        if ((j < 4)&&(datos.get(i).get(j).compareTo(String.valueOf(grupos_f.get(k).get(0))) == 0)
                                && (grupos_f.get(k).size() != (cantidad_estudiantes_grupo+1))){
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
                                if (grupos_f.get(numero).size() < (cantidad_estudiantes_grupo+1)) {
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

        /*
        for ( int i = 0; i < grupos_f.size(); i++) {
            System.out.println("---------------------Proyecto numero "+grupos_f.get(i).get(0)+"--------------------");
            System.out.println(grupos_f.get(i));
            System.out.println("  Tamaño del grupo "+ ((grupos_f.get(i).size())-1));
        }

         */
        return grupos_f;
    }

    public static void agregar_estudiantes_que_no_presentaron(ArrayList<ArrayList<String>> grupos_casi_formados,
                                                              ArrayList<Integer[]> grupos,
                                                              ArrayList<String> datos_no_participaron,
                                                              int cantidad_estudiantes_grupo){

        /*
        for (int i = 0; i< datos_no_participaron.size(); i++) {
            boolean asignado = false;
            while(asignado == false){
                //Crearemos un numero random de grupo asignaremos al estudiante en este grupo
                Random rn = new Random();
                int numero = (int) (Math.random() * grupos_casi_formados.size());
                if (grupos_casi_formados.get(numero).size() < (cantidad_estudiantes_grupo+1)) {
                    grupos_casi_formados.get(numero).add(datos_no_participaron.get(i));
                    asignado = true;
                    break;
                }
            }
        }
         */


        double cantidad_grupos = Math.ceil((double)datos_no_participaron.size() / cantidad_estudiantes_grupo) ;

        int tamaño_actual_grupos = grupos_casi_formados.size()-1;
        for (int i = (tamaño_actual_grupos); i < (tamaño_actual_grupos+cantidad_grupos); i++) {

            grupos_casi_formados.add(new ArrayList<String>());

            //Obtener numero grupo
            String num_grupo = String.valueOf(grupos.get(i+1)[0]);

            //ingresar numero del grupo a la primera componente de cada lista de grupo
            grupos_casi_formados.get(i+1).add(0,num_grupo);

        }

        //System.out.println(grupos_casi_formados);

        for (int i = 0; i< datos_no_participaron.size(); i++) {
            boolean asignado = false;
            while(asignado == false){
                //Crearemos un numero random de grupo asignaremos al estudiante en este grupo
                Random rn = new Random();
                int numero = (int) (Math.random() * ((grupos_casi_formados.size()) - (tamaño_actual_grupos-1)) + (tamaño_actual_grupos-1));
                if (grupos_casi_formados.get(numero).size() < (cantidad_estudiantes_grupo+1)) {
                    grupos_casi_formados.get(numero).add(datos_no_participaron.get(i));
                    asignado = true;
                    break;
                }
            }
        }

        System.out.println("cantidad de grupos de los estudiantes que no participaron = "+ cantidad_grupos);
        System.out.println("cantidad de grupos en total = "+ grupos_casi_formados.size());

        for ( int i = 0; i < grupos_casi_formados.size(); i++) {
            System.out.println("---------------------Proyecto numero "+grupos_casi_formados.get(i).get(0)+"--------------------");
            System.out.println(grupos_casi_formados.get(i));
            System.out.println("  Tamaño del grupo "+ ((grupos_casi_formados.get(i).size())-1));
        }

        int sum = 0;
        for (int i = 0; i < grupos_casi_formados.size(); i++) {
            sum = sum + grupos_casi_formados.get(i).size()-1;
        }
        System.out.println("---------------------------------------");
        System.out.println("cantidad de estudiantes en total (Encuestados o no)= "+sum);
        System.out.println();
        System.out.println();

    }


    public static void main(String[] args) {

        System.out.println("------------------Introduccion-------------");
        lectorCSV lectorCSV = new lectorCSV();
        ArrayList<ArrayList<String>> datos = lectorCSV.leerCSV("/media/jordan/DATOS/Programacion/Becario/introduccion_encuestados.csv");

        ArrayList<Integer[]> lista_grupos = new ArrayList<>();
        lista_grupos= proyectosYvotos(datos);
        lista_grupos = ordenamiento(lista_grupos);

        //Con 34 estudiantes los resultados son mas balanceados, 33 es el correcto pero deja resultados mas desbalanceados
        ArrayList<ArrayList<String>> grupos = crear_grupos(datos,lista_grupos,34,4.00);


        ArrayList<String> datos_no_participaron = lectorCSV.leerCSV_no_participantes("/media/jordan/DATOS/Programacion/Becario/introduccion_No_entregaron.csv");

       agregar_estudiantes_que_no_presentaron(grupos,lista_grupos,datos_no_participaron,4);


        System.out.println("------------------ED--------------------------------");

        ArrayList<ArrayList<String>> datos_ED = lectorCSV.leerCSV("/media/jordan/DATOS/Programacion/Becario/ED_estudiantes_participaron.csv");


        ArrayList<Integer[]> lista_grupos_ED = new ArrayList<>();
        lista_grupos_ED= proyectosYvotos(datos_ED);
        lista_grupos_ED = ordenamiento(lista_grupos_ED);

        ArrayList<ArrayList<String>> grupos_ED = crear_grupos(datos_ED,lista_grupos_ED,27,3.00);


        ArrayList<String> datos_no_participaron_ED = lectorCSV.leerCSV_no_participantes("/media/jordan/DATOS/Programacion/Becario/ED_no_entregaron.csv");

        agregar_estudiantes_que_no_presentaron(grupos_ED,lista_grupos,datos_no_participaron_ED,3);



    }
}
