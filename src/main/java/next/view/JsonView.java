package next.view;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonView implements View {

	public JsonView(String url) {
	}

	@Override
	public void render(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, Object> createModel = createModel(req);
		
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		out.print(mapper.writeValueAsString(createModel));
	}
	
	private Map<String, Object> createModel(HttpServletRequest req) {
		Enumeration<String> names = req.getAttributeNames();
		Map<String, Object> model = new HashMap<>();
		while (names.hasMoreElements()) {
	        String name = names.nextElement();
	        model.put(name, req.getAttribute(name));
	    }
		return model;
	}
}
