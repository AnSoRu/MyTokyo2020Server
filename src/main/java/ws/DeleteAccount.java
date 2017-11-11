package ws;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Usuario;
import entities.UsuarioCompraEvento;
import error.MensajeApp;
import flexjson.JSONSerializer;
import model.UsuarioDAO;

/**
 * Servlet implementation class DeleteAccount
 */
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UsuarioDAO uDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAccount() {
        super();
        DeleteAccount.uDAO = new UsuarioDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MensajeApp respuesta = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if((username==null)&&(password==null)) {
			respuesta = new MensajeApp("error","missing");
		}else {
			List<Usuario> uAuxL = uDAO.getUsuarioByUsername(username);
			if(!uAuxL.isEmpty()) {
				Usuario uAux = uAuxL.get(0);
				Set<UsuarioCompraEvento> eventosUser = uAux.getUsuarioCompraEventos();
			}else {
				respuesta = new MensajeApp("error","noexists");
			}
		}
		response.setContentType("application/json");
		response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
	}

}
