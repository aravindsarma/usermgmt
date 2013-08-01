/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sqlit.test;

import com.sqlit.domain.User;

/**
 *
 * @author Aravind Sarma Yeluripati
 */
public class UserTest {
public static void main(String[] args){                
         User u = new User();
         u.setUserId("aravind");
         u.setPassword("sarma");
         
         System.out.println(u.getUserId()+" "+u.getPassword());
     }
}
