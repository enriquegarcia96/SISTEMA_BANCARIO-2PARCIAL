package com.GARCIA;

import com.GARCIA.modelos.Usuario;

import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Usuario nuevoUsuario = new Usuario("0301","enrique","garcia","ewhwjash@gmail.com");
        nuevoUsuario.getNombreUsuario();
        nuevoUsuario.guardar();
        nuevoUsuario.guardar();

        Scanner lector = new Scanner(System.in);

        System.out.println("ingrese un numero de identidad:  ");
        String identidad  = lector.next();
        Usuario usuarioEncontrado = Usuario.getUsuario(identidad);
        if (usuarioEncontrado != null){
            usuarioEncontrado.setNombre("allan");
            usuarioEncontrado.guardar();
            System.out.println(usuarioEncontrado.getNombreUsuario());
        }else {
            System.out.println("no se encontro un uaus");
        }



        boolean continuar = true;
        while (continuar) {

            System.out.println("~~ Menu ~~");
            System.out.println("1. Lista de usuarios");
            System.out.println("2. Busqueda de usuarios");
            int opcion = LectorDeDatos.solicitarEntero("Ingrese su opcion: ");

            switch (opcion) {
                case 1:
                    int mostrarLimite = LectorDeDatos.solicitarEntero("Ingrese un numero : ");
                    ArrayList<Usuario> lista = Usuario.getUsuarios(mostrarLimite);

                    for (Usuario u : lista) {
                        System.out.println(u.getNombreUsuario());
                    }
                    break;

                case 2:
                    System.out.println("ingrese la identidad a buscar: ");
                    String busqueda = lector.nextLine();
                    ArrayList<Usuario> lista2 = Usuario.getUsuario(busqueda);
                    for (Usuario u : lista2) {
                        System.out.println(u.getIdentidad());
                    }
                    break;

            }
        }
    }
}
