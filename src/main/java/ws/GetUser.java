package ws;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Usuario;
import model.UsuarioDAO;
import error.MensajeApp;
import flexjson.JSONSerializer;

/**
 * Servlet implementation class GetUser
 */
public class GetUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UsuarioDAO uDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUser() {
        super();
        GetUser.uDAO = new UsuarioDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		MensajeApp respuesta = null;
		if(username == null) {
			respuesta = new MensajeApp("error","missing");
			response.setContentType("application/json");
			response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
		}else {
			List<Usuario> uAuxL = uDAO.getUsuarioByUsername(username);
			if(!uAuxL.isEmpty()) {
				Usuario uAux = uAuxL.get(0);
				response.setContentType("application/json");
				response.getWriter().print(new JSONSerializer().exclude("class").serialize(uAux));
			}else {
			   respuesta = new MensajeApp("error","noexists");
			   response.setContentType("application/json");
			   response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
			}			
		}
		
	}

}
