package MarksheetServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import Marksheet.Vo.RagistrationVo;
import MarksheetService.RagistrationService;


//import com.vo.Registration;

public class RagistrationCtl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");
		
		try {
			String firstName = request.getParameter("firstname");
			String lastName = request.getParameter("lastname");
			String fatherName = request.getParameter("fathername");
			String emailid = request.getParameter("emailid");
			String cAdd = request.getParameter("cAdd");
			String pAdd = request.getParameter("pAdd");
			String dob = request.getParameter("dob");
			String city = request.getParameter("city");
			String phoneNo = request.getParameter("phoneNo");
			String gender = request.getParameter("gender");
			String catagary = request.getParameter("catagary");
			String course = request.getParameter("course");
			String state = request.getParameter("state");			
			RagistrationVo vo = new RagistrationVo();
			vo.setFirstName(firstName);
			vo.setLastName(lastName);
			vo.setFatherName(fatherName);
			vo.setEmailId(emailid);
			vo.setCirruntAdd(cAdd);
			vo.setParmanentAdd(pAdd);
			vo.setPhoneNo(phoneNo);
			vo.setDob(dob);
			vo.setCity(city);
			vo.setState(state);
			vo.setGender(gender);
			vo.setCatagary(catagary);
			vo.setCourse(course);
RagistrationService service=new RagistrationService();
if("submit".equalsIgnoreCase(op)){
service.submit(vo);
RequestDispatcher rd=request.getRequestDispatcher("/Layout.jsp?body=Login.jsp");
rd.forward(request, response);
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
