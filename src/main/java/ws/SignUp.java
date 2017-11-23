package ws;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Usuario;
import error.MensajeApp;
import flexjson.JSONSerializer;
import model.UsuarioDAO;

/**
 * Servlet implementation class SignUp
 */
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UsuarioDAO uDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUp() {
		super();
		SignUp.uDAO = new UsuarioDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MensajeApp respuesta = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String edad = request.getParameter("edad");
		if((username==null)||(password==null)) {
			respuesta = new MensajeApp("error","missing");
		}else {
			if(edad == null) {
				Usuario u = new Usuario(username, password,new Date());
				if(uDAO.insertUsuario(u)) {
					respuesta = new MensajeApp("ok","inserted");
				}else {
					respuesta = new MensajeApp("error","exists");
				}
			}else {
				Integer edadInteger = Integer.valueOf(edad);
				Usuario u = new Usuario(username,edadInteger,password, new Date(), null);
				if(uDAO.insertUsuario(u)) {
					respuesta = new MensajeApp("ok","inserted");
				}else {
					respuesta = new MensajeApp("error","exists");
				}
			}
		}
		response.setContentType("application/json");
		response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
	}

}
