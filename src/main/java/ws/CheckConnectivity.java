package ws;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import error.MensajeApp;
import flexjson.JSONSerializer;

/**
 * Servlet implementation class CheckConnectivity
 */
public class CheckConnectivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckConnectivity() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MensajeApp res = new MensajeApp("ok","connected");
		response.setContentType("application/json");
		response.getWriter().write(new JSONSerializer().exclude("class").serialize(res));
	}

}
