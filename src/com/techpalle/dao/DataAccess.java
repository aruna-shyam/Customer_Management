package com.techpalle.dao;

import java.sql.*;

import com.techpalle.model.Customer;

public class DataAccess 
{
	
	private static final String dbUrl="jdbc:mysql://localhost:3306/servlet";
	private static final String dbUsername="root";
	private static final String dbPassword="admin";
	
	private static Connection con=null;
	private static Statement s=null;
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	
	private static final String insertQry="insert into "
			+ "customer(name,email,mobile,password,state) values(?,?,?,?,?)";
	
	private static final String validateQry="select email,password from customer where email=? and password=?";
	
	public static boolean validateCustomer(String email,String password)
	{
		boolean b=false;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
			ps=con.prepareStatement(validateQry);
			ps.setString(1, email);
			ps.setString(2, password);
			
		    rs=ps.executeQuery();
		   //here we dont want loop we just retriving the data e,p data
		    //if data is present store into b variable and return b, if not present return false
		    b=rs.next();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
          try 
          {
        	  if(rs!=null)
        	  {
        		  rs.close();
        	  }
        	  if(ps!=null)
        	  {
			     ps.close();
        	  }
        	  if(con!=null)
        	  {
        		  con.close();
        	  }
          } 
          catch (SQLException e) 
          {
			e.printStackTrace();
		  }
		}
		return b;
	}
	public static void insertCustomer(Customer customer)
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
			ps=con.prepareStatement(insertQry);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getEmail());
			ps.setLong(3, customer.getMobile());
			ps.setString(4, customer.getPassword());
			ps.setString(5, customer.getState());
			
			ps.executeUpdate();
			
			
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
          try 
          {
        	  if(ps!=null)
        	  {
			     ps.close();
        	  }
        	  if(con!=null)
        	  {
        		  con.close();
        	  }
		  } 
          catch (SQLException e) 
          {
			e.printStackTrace();
		  }
		}
		
		
	}

}
