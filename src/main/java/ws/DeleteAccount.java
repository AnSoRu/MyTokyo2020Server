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
import model.UsuarioCompraEventoDAO;
import model.UsuarioDAO;

/**
 * Servlet implementation class DeleteAccount
 */
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UsuarioDAO uDAO;
	private static UsuarioCompraEventoDAO ucDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAccount() {
        super();
        DeleteAccount.uDAO = new UsuarioDAO();
        DeleteAccount.ucDAO = new UsuarioCompraEventoDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MensajeApp respuesta = null;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if((email==null)&&(password==null)) {
			respuesta = new MensajeApp("error","missing");
		}else {
			List<Usuario> uAuxL = uDAO.getUsuarioByEmail(email);
			if(!uAuxL.isEmpty()) {
				Usuario uAux = uAuxL.get(0);
				Set<UsuarioCompraEvento> eventosUser = uAux.getUsuarioCompraEventos();
				if(!eventosUser.isEmpty()) {
					System.out.println("El usuario tiene eventos");
					for(UsuarioCompraEvento e: eventosUser) {
						ucDAO.delete(e);
					}
					eventosUser.clear();
				}
				uAux.setUsuarioCompraEventos(eventosUser);
				uDAO.updateUsuario(uAux);
				List<Usuario> uAuxL2 = uDAO.getUsuarioByEmail(email);
				Usuario uAux2 = uAuxL2.get(0);
				if(uDAO.deleteUsuario(uAux2)) {
					respuesta = new MensajeApp("ok","deleted");
				}else {
					respuesta = new MensajeApp("error","nodeleted");
				}
			}else {
				respuesta = new MensajeApp("error","noexists");
			}
		}
		response.setContentType("application/json");
		response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
	}

}
