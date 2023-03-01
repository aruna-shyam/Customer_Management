package com.techpalle.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techpalle.dao.DataAccess;
import com.techpalle.model.Customer;
import com.techpalle.util.SuccessPage;


@WebServlet("/")
public class MyServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String path=request.getServletPath();
		
		switch(path)
		{
		case "/logCustomer" :
			validateCustomer(request,response);
			break;
		case "/regCustomer" :
			insertCustomer(request,response);
			break;
		//click signup go to reg page
		case "/reg":
			getRegistrationPage(request,response);
			break;
	    //click signin go to login page
		case "/log":
			getLoginPage(request,response);
			break;
		//starting page
		  default:
			  getStartUpPage(request,response);
			  break;
		}
	}

	
	private void validateCustomer(HttpServletRequest request, HttpServletResponse response) 
	{
		//read email and password from jsp page
		String e=request.getParameter("tbEmaillog");
		String p=request.getParameter("tbPasslog");
		
		//call the method present in dao
		boolean res=DataAccess.validateCustomer(e, p);
		
		//condition and redirect to success page
		if(res)
		{
			try 
			{
				RequestDispatcher rd=request.getRequestDispatcher("success.jsp");
				//store the SuccessPage class data inside RD
				SuccessPage sp=new SuccessPage();
				request.setAttribute("successDetails", sp);
				
				rd.forward(request, response);
			} 
			catch (ServletException e1) 
			{
				
				e1.printStackTrace();
			} 
			catch (IOException e1) 
			{
			   e1.printStackTrace();
			}
		}
		//if condition is not true(invalid data) redirected to login page
		else
		{
			/*try 
			{
				RequestDispatcher rd=request.getRequestDispatcher("customer_login.jsp");
				rd.forward(request, response);
			} 
			catch (ServletException e1) 
			{
				
				e1.printStackTrace();
			} 
			catch (IOException e1) 
			{
			   e1.printStackTrace();
			} */      //Or
			
			getLoginPage(request,response);
		}
	}


	
	private void insertCustomer(HttpServletRequest request, HttpServletResponse response) 
	{
		//read the data from reg jsp page
		String n=request.getParameter("tbName");
		String e=request.getParameter("tbEmail");
		long m=Long.parseLong(request.getParameter("tbMob"));
		String p=request.getParameter("tbPass");
		String s=request.getParameter("ddlstates");
		
		//store the data in customer obj
		Customer c=new Customer(n,e,m,p,s);
		
		//call insertCustomer method present in dao by passing above obj
		//iC is static method so call directly using classname
		DataAccess.insertCustomer(c);
		
		//redirected user to login page
		try 
		{
			RequestDispatcher rd=request.getRequestDispatcher("customer_login.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException e1) 
		{
			
			e1.printStackTrace();
		} 
		catch (IOException e1) 
		{
		   e1.printStackTrace();
		}
		
	}


	private void getRegistrationPage(HttpServletRequest request, HttpServletResponse response) 
	{
		try 
		{
			RequestDispatcher rd=request.getRequestDispatcher("customer_registration.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}


	private void getLoginPage(HttpServletRequest request, HttpServletResponse response) 
	{
		
		try 
		{
			RequestDispatcher rd=request.getRequestDispatcher("customer_login.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
  }


	private void getStartUpPage(HttpServletRequest request, HttpServletResponse response)
	{
		
		try 
		{
			RequestDispatcher rd=request.getRequestDispatcher("customer_management.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		doGet(request, response);
	}

}
