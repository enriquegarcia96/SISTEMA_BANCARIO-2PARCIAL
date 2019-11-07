package com.GARCIA;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LectorDeDatos {


    public static int solicitarEntero(String mensaje){
        Scanner lector = new Scanner(System.in);
        System.out.print(mensaje);
        try{
            int numero = lector.nextInt();
            return numero;
        }catch (InputMismatchException e){
            System.out.println("El dato es invalido");
            return solicitarEntero(mensaje);

        }

    }

    public static String comprobarID(String mensaje){
        Scanner lector = new Scanner(System.in);
        System.out.print(mensaje);
        try{
            String numero = lector.nextLine();
            return numero;
        }catch (InputMismatchException e){
            System.out.println("El dato es invalido");
            return comprobarID(mensaje);

        }

    }



}
