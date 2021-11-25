/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.interfaz.cliente;
import javax.swing.*;
/**
 * Clase que contiene algunas validaciones como la de un numero o el nombre
 * @author DMV
 */
public class Validaciones {
    public static boolean validarCantidad(JTextField cantidad){
        if(cantidad.getText() == "0" || cantidad.getText().isBlank()){
            return false;
        }else{
           return true; 
        }
    }
    public static boolean validarNombre(JTextField nombre){
        if(nombre.getText().isBlank()){
            return false;
        }else{
            return true;
        }
    }
}
