package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;

@WebServlet("/user/update")
public class UpdateUserFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UpdateUserFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String userId = req.getParameter("userId");
    	log.debug("userId: " + userId);
    	
    	User user = DataBase.findUserById(userId);
    	
        if (user == null) {
//        	resp.sendRedirect("/user/login_failed.html");
        	return;
        }
        
        req.setAttribute("user", user);
        RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
        rd.forward(req, resp);
    	
    }
}
