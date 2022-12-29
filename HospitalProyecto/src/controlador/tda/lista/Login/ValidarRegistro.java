/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.tda.lista.Login;

import javax.swing.JOptionPane;

/**
 *
 * @author johnny
 */
public class ValidarRegistro {

    public static boolean ValidarNums(String Numeros) {
        return Numeros.matches("[0-9]");
    }

    public void ValidarCaracteres(String txtCadena) {
        char caracter = 0;
        int ASCII = 0, cont = 0;
        for (int i = 0; i <= cont; i++) {
            if (cont > 0) {
                cont = 0;
                //JOptionPane.showMessageDialog(this, "Caracteres no Validos.");
            }

            for (int j = 0; j < txtCadena.length(); j++) {
                caracter = txtCadena.charAt(j);
                ASCII = (int) caracter;
                if (ASCII < 97 || ASCII > 122) {
                    cont++;

                }
            }
            if (cont == 0) {
                break;
            }
        }
    }
    
    
}
