/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sqlit.test;

import com.sqlit.dao.DBConnectionManager;
import com.sqlit.domain.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Aravind Sarma Yeluripati
 */
public class UserDaoTest {
static ArrayList<User> userList = new ArrayList<>();
    static HashMap<String,String> loginInfo = new HashMap<>();
    public static void selectAll(){
        String SELECT_USERS_SQL = "SELECT userid,password,firstName,lastName,email FROM USERS";        
        Connection conn = DBConnectionManager.getConnection();        
        ResultSet rset = null;                
        Statement stmt = null;      
        
        int rows = 0;               
        String userid,password,firstName,lastName,email;
        try{
            stmt = conn.createStatement();
            rset = stmt.executeQuery(SELECT_USERS_SQL);
            while(rset.next()){                                                
                User u = new User();
                userid = rset.getString(1).trim();                
                u.setUserId(userid);
                password = rset.getString(2).trim();
                u.setPassword(password);
                firstName = rset.getString(3).trim();
                u.setFirstName(firstName);
                lastName = rset.getString(4).trim();
                u.setLastName(lastName);
                email = rset.getString(5).trim();   
                u.setEmail(email);
                System.out.println(rows+" "+String.format("%-20s", userid)+String.format("%-20s",password)
                        +String.format("%-20s",firstName)+String.format("%-20s",lastName)
                        +String.format("%-20s",email));                
                userList.add(rows,u);                
                System.out.println(rows+" "+String.format("%-20s", userList.get(rows).getUserId())
                        +String.format("%-20s",userList.get(rows).getPassword())
                        +String.format("%-20s",userList.get(rows).getFirstName())
                        +String.format("%-20s",userList.get(rows).getLastName())
                        +String.format("%-20s",userList.get(rows).getEmail())
                );
                for(int i=0;i<=rows;i++){
                    System.out.println(i+" "+userList.get(i).getUserId());
                }
                loginInfo.put(userid, password);                
                rows++;
            }        
            
        
            rset.close();
            rset = null;
            stmt.close();            
            stmt = null;
        }catch(SQLException e){
            
        }finally{
            if(rset!=null)try{rset.close();}catch(Exception i){}
            if(stmt!=null)try{stmt.close();}catch(Exception i){}
        }                
    }
    
    public static void main(String[] args){                      
        UserDaoTest.selectAll();                
        User u;
        for(int i=0; i<userList.size(); i++){
            u = (User) userList.get(i);
            System.out.println(i+" "+u.getUserId());
        }
        System.out.println(loginInfo.toString());
    }
}
