/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.generate;

import java.util.Random;

/**
 *
 * @author onairo
 */
public class Code {
    public String generate(int length) {
        Random rnd = new Random();
        String digits = "1234567890"; // Dígitos posibles
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; // Letras posibles

        StringBuilder mixedCode = new StringBuilder();

        for (int i = 0; i < length/2 ; i++) {
            // Agrega un dígito aleatorio
            char digit = digits.charAt(rnd.nextInt(digits.length()));
            mixedCode.append(digit);

            // Agrega una letra aleatoria
            char letter = letters.charAt(rnd.nextInt(letters.length()));
            mixedCode.append(letter);
        }

        return mixedCode.toString();
    }
}
