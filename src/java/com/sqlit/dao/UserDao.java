/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sqlit.dao;

import com.sqlit.domain.User;
import com.sqlit.domain.UserList;
import com.sqlit.domain.UserLoginInfo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Aravind Sarma Yeluripati
 */
public class UserDao {    
    public UserList<User> selectAll(){
        UserList<User> userList = new UserList<>();
        String SELECT_USERS_SQL = "SELECT userid,password,firstName,lastName,email FROM USERS";        
        Connection conn = DBConnectionManager.getConnection();        
        ResultSet rset = null;                
        Statement stmt = null;              
        int row = 0;                       
        try{
            stmt = conn.createStatement();
            rset = stmt.executeQuery(SELECT_USERS_SQL);
            while(rset.next()){                                                
                User u = new User();                                               
                u.setUserId(rset.getString(1).trim());                
                u.setPassword(rset.getString(2).trim());                
                u.setFirstName(rset.getString(3).trim());                
                u.setLastName(rset.getString(4).trim());
                u.setEmail(rset.getString(5).trim());                
                userList.add(row,u);                                                                             
                row++;
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
        return userList;
    }
    
    public UserLoginInfo<String> selectLoginInfo(){
        UserLoginInfo<String> loginInfo = new UserLoginInfo<>();
        String SELECT_LOGIN_INFO_SQL = "SELECT userid,password FROM USERS";
        Connection conn = DBConnectionManager.getConnection();        
        ResultSet rset = null;                
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            rset = stmt.executeQuery(SELECT_LOGIN_INFO_SQL);
            while(rset.next()){                                                                               
                String userId,password;
                userId = rset.getString(1).trim();
                password = rset.getString(2).trim();                                                                                
                loginInfo.put(userId, password);                                
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
        return loginInfo;
    }
    
    public static void main(String[] args){                      
        UserList<User> userList;
        UserLoginInfo<String> loginInfo;
        UserDao ud = new UserDao();
        userList = ud.selectAll();                
        loginInfo = ud.selectLoginInfo();
        
        User u;
        for(int i=0; i<userList.size(); i++){
            u = (User) userList.get(i);
            System.out.println(i+" "+u.getUserId());
        }        
        System.out.println(loginInfo.toString());
        
        String userId="araindsarma";
        String password="saigra";
        if(loginInfo.containsKey(userId)){
            if(loginInfo.containsValue(password)){
                System.out.println("userId and password matched");
            }else{
                System.out.println("userId exists, password didn't match");
            }
        }else{
            System.out.println("userId doesn't exist");
        }
    }
}
