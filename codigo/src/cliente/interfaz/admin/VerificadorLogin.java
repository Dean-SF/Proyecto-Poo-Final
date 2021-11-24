/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.interfaz.admin;

import datos.IConstantes;

/**
 *
 * @author DMV
 */
public class VerificadorLogin {
    public static boolean validarAdm(String login, String pwd){
        return login.equals(IConstantes.admin) && pwd.equals(IConstantes.pass);
    }
}
