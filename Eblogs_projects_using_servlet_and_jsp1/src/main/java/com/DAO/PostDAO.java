package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.User.Post;

public class PostDAO{
private  Connection conn;
public PostDAO(Connection conn) {
    super();
    this.conn = conn;
}

       //static
  public static int  AddBlog(String ti,String co,int ui )
  {
    //  boolean f=false;
      int i=0;
      try 
      {
        //  String qu ="insert into post(title,content,uid) values(?,?,?)";
         Connection conn=   UserDAO.getConn();
            
          PreparedStatement  ps=conn.prepareStatement("insert into post(title,content,uid) values(?,?,?)");
          
          ps.setString(1,ti);
          ps.setString(2,co);
     
          ps.setInt(3,ui);
          
       i =ps.executeUpdate();
        
          
      }
      catch(Exception e)
         {
             e.printStackTrace();
         }
         
      
      return i;
  }

   public static List<Post> getData(int id)
   {
       List<Post> list = new ArrayList<Post>();
       Post po = null;
       
       try 
       {
           Connection conn=   UserDAO.getConn();
             
          String qu= "select * from post where uid=? order by id DESC";
          PreparedStatement ps = conn.prepareStatement(qu);
          ps.setInt(1,id);
          
         ResultSet rs= ps.executeQuery();
          
    
          while(rs.next())
          {
              po=new Post();
              
              po.setId(rs.getInt(1));
              po.setTitle(rs.getString(2));
              po.setContent(rs.getString(3));
              po.setPdate(rs.getTimestamp(4));              
              list.add(po);
              
          }
       }
       catch(Exception e)
         {
             e.printStackTrace();
         }
       
       return list;
   }

  public static Post getDataById(int noteId) //4
  {
      Post p=null;
      try
      {
          String qu="select * from post where id=?";
          Connection conn=   UserDAO.getConn();
          PreparedStatement ps=conn.prepareStatement(qu);
          ps.setInt(1, noteId);
          
          ResultSet rs=ps.executeQuery();
          
          if(rs.next())
          {
              p=new Post();
              p.setId(rs.getInt(1));
              p.setTitle(rs.getString(2));
              p.setContent(rs.getString(3));
          }
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      
      return p;
  }
  
  public static boolean PostUpdate(int nid,String ti,String co)
  {
      boolean f= false;
      
      try
      {
          String qu="update post set title=?, content=? where id=?";
          Connection conn=   UserDAO.getConn();
          PreparedStatement ps= conn.prepareStatement(qu);
           ps.setString(1, ti);
           ps.setString(2, co);
           ps.setInt(3, nid);
           int i=ps.executeUpdate();
           
           if(i==1)
           {
               f= true;
           }
           
          
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      
      return f;
  }
  
  public static  boolean DeleteBlog(int nid)
  {
      boolean f=false;
      
      try
      {
          String qu="delete from post where id= ?";
          Connection conn=   UserDAO.getConn();
          PreparedStatement ps= conn.prepareStatement(qu);
          ps.setInt(1, nid);
          
          int i= ps.executeUpdate();
          
          if(i==1)
          {
             f= true;
          }
          
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      return f;
  }	  
}