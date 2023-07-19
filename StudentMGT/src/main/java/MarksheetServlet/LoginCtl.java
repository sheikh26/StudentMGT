package MarksheetServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit.BoldAction;

import Marksheet.Vo.LoginVo;
import MarksheetService.LoginService;

//import com.service.LoginService;
//import com.vo.LoginVo;

public class LoginCtl extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
	String op = request.getParameter("operation");
	HttpSession session = request.getSession();  
	LoginVo vo = new LoginVo() ;
			
       try {
				String userId = request.getParameter("userId");
				String password = request.getParameter("password");
				vo.setUserId(userId);
				vo.setPassword(password);
	LoginService service=new LoginService();
	boolean value = false;
				if ("login".equalsIgnoreCase(op)) {
				value=service.checkTable();
				System.out.println(value+"b----1--");
					if(value == true) {
						System.out.println(value+"b----2--");
						value = service.login(vo);
						System.out.println(value+"b----3--");
						System.out.println(value+"b----4--");
						System.out.println(value+"b----5--");
						session.setAttribute("user",userId);
						System.out.println("password is true");
						RequestDispatcher rd = request
								.getRequestDispatcher("/Layout.jsp?body=Marksheet.jsp");
						rd.forward(request, response);
					}
					
				else {
						System.out.println(value+"b----6--");
						System.out.println("password is invalide");
						RequestDispatcher rd = request
								.getRequestDispatcher("/Layout.jsp?body=Login.jsp");
						rd.forward(request, response);
					}

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
