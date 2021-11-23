/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;
import javax.swing.*;
/**
 *
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
