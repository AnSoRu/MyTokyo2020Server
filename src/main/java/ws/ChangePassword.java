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
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static UsuarioDAO uDAO;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        ChangePassword.uDAO = new UsuarioDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MensajeApp respuesta = null;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		if((email == null)||(password == null) || (newPassword == null)) {
			respuesta = new MensajeApp("error","missing");
		}else {
			//Usuario existe?
			List<Usuario> uAuxL = uDAO.getUsuarioByEmail(email);
			if(!uAuxL.isEmpty()) {
				Usuario uAux = uAuxL.get(0);
				//El password que introduce es el mismo que está almacenado
				if(uAux.getPassword()!=null) {
					if(uAux.getPassword().equals(password)) {
						if(uAux.getPassword().equals(newPassword)) {
							respuesta = new MensajeApp("error","samepass");
						}else {
							uAux.setPassword(newPassword);
							uDAO.updateUsuario(uAux);
							respuesta = new MensajeApp("ok","updated");
						}
					}else {
						respuesta = new MensajeApp("error","badpass");
					}
				}
			}else {
				respuesta = new MensajeApp("error","noexists");
			}
		}
		response.setContentType("application/json");
		response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
	}
}