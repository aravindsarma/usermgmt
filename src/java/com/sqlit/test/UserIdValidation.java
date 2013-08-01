/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sqlit.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Aravind Sarma Yeluripati
 */
public class UserIdValidation {

    public static void main(String[] args) {
        String input;
        Pattern p = Pattern.compile("\\p{Alpha}\\p{Alnum}*");
        Matcher m = p.matcher("1");
        boolean b = m.matches();
        if(b){
            System.out.println("pattern matched");
        }else{
            System.out.println("pattern didn't match");
        }
    }
}
