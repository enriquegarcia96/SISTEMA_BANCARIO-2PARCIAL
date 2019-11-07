package com.GARCIA.modelos;
import com.GARCIA.LectorDeDatos;
import com.GARCIA.libs.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Usuario {
    private static ArrayList<Usuario> id = new ArrayList<>();

    private int codigo;
    private String identidad;
    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String correoElectronico;
    private String telefono;
    private Date creacion;

    //constructor
    public Usuario(String identidad, String nombre, String apellido, String correoElectronico) {
        this.setIdentidad(identidad);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCorreoElectronico(correoElectronico);

    }


    public int getCodigo() {
        return codigo;
    }

    public String getIdentidad() {
        return identidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }


    //se va a enccargar si el nombre esta nulo
    public String getNombreUsuario() {
        if (this.nombreUsuario == null) {
            this.nombreUsuario = this.generarNombreUsuario(0);

        }
        return this.nombreUsuario;
    }

    //metodo privado, para uso privado de la clase
    //
    private String generarNombreUsuario(int contador) {
        String temNombreUsuario =
                this.nombre.toLowerCase() + "." + this.apellido.toLowerCase();
        // ^para convertir la cadena de caracter en minuscula
        if (contador > 0) {
            temNombreUsuario += "." + String.valueOf(contador);
        }

        PreparedStatement sentencia = null;
        try {
            sentencia = Conexion.abrirConexion().prepareStatement(
                    "select nombre_usuario,creacion from usuarios where nombre_usuario = ?"
            );
            sentencia.setString(1, temNombreUsuario);
            ResultSet resultado = sentencia.executeQuery();

            boolean encontrado = false;
            while (resultado.next()) {
                encontrado = true;
            }
            if (encontrado) {
                generarNombreUsuario(contador + 1);
            }
        } catch (SQLException e) {
            System.err.println("Error al generar el nombre de usuario " + e.getMessage());
        }
        return temNombreUsuario;
    }


    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public Date getCreacion() {

        return creacion;
    }


    public void setIdentidad(String identidad) {
        this.identidad = identidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //metodo.
    public boolean guardar() {

            try {
                PreparedStatement  sentencia = null;
                if (this.codigo == 0) {
                    sentencia = Conexion.abrirConexion().prepareStatement(
                            "insert into usuario (identidad, nombre_usuario, primer_nombre, apellido, correo_electronico, telefono)" +
                                    " values (?,?,?,?,?,?)"

                    );
                }else {
                    sentencia = Conexion.abrirConexion().prepareStatement(
                            " update usuario set identidad = ? , nombre_usuario = ? , primer_nombre = ? , apellido = ?, correoelectronico = ?, telefono = ?, where codigo = ?"
                    );
                    sentencia.setInt(7,this.getCodigo());

                }
                sentencia.setString(1, this.getIdentidad());
                sentencia.setString(2, this.getNombreUsuario());
                sentencia.setString(3, this.getNombre());
                sentencia.setString(4, this.getApellido());
                sentencia.setString(5, this.getCorreoElectronico());
                sentencia.setString(6, this.getTelefono());
                return sentencia.execute();
                //que funcione y el 2
                /** que metodo obtebner el codigo autogenrado que se crea en creacion y codigo
                 * como conseguir la creacion de codigo y creacion cuando se guarda un nuevo usuario**/
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }else {
            /**actualizar**/
            PreparedStatement sentencia = Conexion.abrirConexion().prepareStatement(
                    ""
            );
            sentencia.setString(1,this.setIdentidad());
            sentencia.setString(1,this.setIdentidad());
            sentencia.setString(1,this.setIdentidad());
            sentencia.setString(1,this.setIdentidad());
            sentencia.setString(1,this.setIdentidad());
            sentencia.setString(1,this.setIdentidad());
            sentencia.setString(1,this.setIdentidad());
        }
        return false;
    }


    public static Usuario  getUsuario(String IDen){

        try {
            PreparedStatement busqueda = Conexion.abrirConexion().prepareStatement(
                    "select * from usuario \n" +
                            "where identidad = ?"
            );
            busqueda.setString(1,IDen);
            ResultSet resultado = busqueda.executeQuery();
                while (resultado.next()){
                    return Usuario.crearInstancia(resultado);
                }
                //if (IDen.length() == 13){
                //if (IDen.matches("[0-9]{13}")){
                    //while (resultado.next()){
                    //return Usuario.crearInstancia(resultado);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return null;
    }



    public static ArrayList<Usuario> getUsuarios(int limiteDeRegistro) {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        PreparedStatement sentencia = null;
        try {
            sentencia = Conexion.abrirConexion().prepareStatement(
                    "select * from usuario limit ?"
            );
            //sentencia.setMaxRows(limiteDeRegistro);
            sentencia.setInt(1,limiteDeRegistro);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                listaUsuarios.add(Usuario.crearInstancia(resultado));
            }
        } catch (SQLException e) {
            System.err.println("algo salio mal " + e.getMessage());
        }
        return listaUsuarios;
    }

    private static Usuario crearInstancia(ResultSet resultado){
        Usuario usuario = null;
        try {
             usuario = new Usuario(
                    resultado.getString("identidad"),
                    resultado.getString("nombre_usuario"),
                    resultado.getString("apellido"),
                    resultado.getString("correo_electronico"));
            usuario.codigo = resultado.getInt("codigo");
            usuario.nombreUsuario = resultado.getString("nombre_usuario");
            usuario.creacion = resultado.getDate("creacion");
        }catch (SQLException e){
            System.err.println("algo salio mal "+e.getMessage());

        }
            return usuario;
    }


}






