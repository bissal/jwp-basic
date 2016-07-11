package next;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DataBase;
import model.User;
import util.HttpRequestUtils;
import util.IOUtils;

@WebServlet("/user/create")
public class UserCreateServlet extends HttpServlet {
	private static final Logger log = LoggerFactory.getLogger(WebServerLauncher.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.print("Hello World!");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String strContentLength = request.getHeader("Content-Length");
		int contentLength = Integer.parseInt(strContentLength);
		
		Map<String, String> userInfo = HttpRequestUtils.parseQueryString(readBody(br, contentLength));
		
		User user = new User(userInfo.get("userId"), userInfo.get("password"), userInfo.get("name"), userInfo.get("email"));
		DataBase.addUser(user);
		User findUserById = DataBase.findUserById(userInfo.get("userId"));
		log.debug("Added User: " + findUserById);
	}

	private String readBody(BufferedReader br, int contentLength) throws IOException {
		String requestBody;
		requestBody = IOUtils.readData(br, contentLength);
		log.debug("requestBody: " + requestBody);
		
		return requestBody;
	}
}