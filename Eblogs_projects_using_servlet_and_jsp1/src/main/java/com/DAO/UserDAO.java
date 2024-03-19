package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.User.UserDetails;

public class UserDAO {


    public static Connection getConn(){  
        Connection conn=null;  
        try{  
        	Class.forName("com.mysql.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eblogs","root","root"); //2 
        }
        catch(Exception e)
        {System.out.println(e);}  
        return conn;  
    } 
 
 
    //static
	 public static  int addUser(UserDetails u) //
	 {
		 
		 int i=0;
		 try
		 {
			   Connection conn  =UserDAO.getConn();
			 
			 //String query="insert into user(name,email,password) values(?,?,?)";
			 
			 PreparedStatement ps=conn.prepareStatement("insert into user(name,email,password) values(?,?,?)");
			 ps.setString(1, u.getName());
			 ps.setString(2, u.getEmail());
			 ps.setString(3, u.getPassword());
			i= ps.executeUpdate();
			 //0==1
			
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 return i;
	 } 
		 public static UserDetails loginUser(UserDetails u)
		 {
			 UserDetails user=null;
			 Connection conn  =UserDAO.getConn();
			 
			 try
			 {                                         //priya    and 1234      
				 String query="select * from user where email=? and password=?";
				 PreparedStatement ps=conn.prepareStatement(query);
				 ps.setString(1, u.getEmail());
				 ps.setString(2, u.getPassword());
				 
				   ResultSet rs =ps.executeQuery();
				   
					 
				 while(rs.next())
				 {
					 user= new UserDetails();
					
					 
					 user.setId(rs.getInt("id"));				 
					 user.setName(rs.getString("name"));
					 user.setEmail(rs.getString("email"));
					 user.setPassword(rs.getString("password"));
				 }
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			 
			 
			 return user;
			 
		 
		 
	 }
	 
}
