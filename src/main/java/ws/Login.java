package ws;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Usuario;
import error.MensajeApp;
import flexjson.JSONSerializer;
import model.UsuarioDAO;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UsuarioDAO uDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        Login.uDAO = new UsuarioDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		MensajeApp respuesta = null;
		if(email == null || password == null) {
			respuesta = new MensajeApp("error","missing");
			response.setContentType("application/json");
			response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
		}else {
			List<Usuario> uAuxL = uDAO.getUsuarioByEmail(email);
			if(!uAuxL.isEmpty()) {
				Usuario uAux = uAuxL.get(0);
				if(uAux.getPassword()!=null) {
					if(uAux.getPassword().equals(password)) {
						respuesta = new MensajeApp("ok","user");
						response.setContentType("application/json");
						response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
					}else {
						respuesta = new MensajeApp("error","password");
						response.setContentType("application/json");
						response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
					}
				}
			}else {
				respuesta = new MensajeApp("error","user");
				response.setContentType("application/json");
				response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
			}
		}
	}
}
