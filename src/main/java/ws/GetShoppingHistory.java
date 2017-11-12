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
 * Servlet implementation class GetShoppingHistory
 */
public class GetShoppingHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UsuarioDAO uDAO;
	//private static UsuarioCompraEventoDAO ucDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetShoppingHistory() {
        super();
        GetShoppingHistory.uDAO = new UsuarioDAO();
        //GetShoppingHistory.ucDAO = new UsuarioCompraEventoDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		MensajeApp respuesta = null;
		if(username==null) {
			respuesta = new MensajeApp("error","missing");
			response.setContentType("application/json");
			response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
		}else {
			List<Usuario> uAuxL = uDAO.getUsuarioByUsername(username);
			if(!uAuxL.isEmpty()) {
				Usuario uAux = uAuxL.get(0);
				Set<UsuarioCompraEvento> eventosUser = uAux.getUsuarioCompraEventos();
				if(!eventosUser.isEmpty()) {
					response.setContentType("application/json");
					response.getWriter().print(new JSONSerializer().exclude("class").serialize(eventosUser));
				}
				else {
					respuesta = new MensajeApp("ok","noshoppings");
					response.setContentType("application/json");
					response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
				}				
			}else {
			   respuesta = new MensajeApp("error","noexists");
			   response.setContentType("application/json");
			   response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
			}	
		}
	}
}
