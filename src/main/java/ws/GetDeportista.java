package ws;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DeportistaDAO;

import entities.Deportista;
import error.MensajeApp;
import flexjson.JSONSerializer;

/**
 * Servlet implementation class GetDeportista
 */
public class GetDeportista extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static DeportistaDAO dDAO;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDeportista() {
        super();
        GetDeportista.dDAO = new DeportistaDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		MensajeApp respuesta;
		if(nombre == null) {
			respuesta = new MensajeApp("error","missing");
			response.setContentType("application/json");
			response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
		}else {
			List<Deportista> dAuxL = dDAO.getDeportistaByName(nombre);
			if(!dAuxL.isEmpty()) {
				Deportista dAux = dAuxL.get(0);
				response.setContentType("application/json");
				response.getWriter().print(new JSONSerializer().exclude("class").exclude("eventoParticipaDeportistas").serialize(dAux));
			}else {
				respuesta = new MensajeApp("error","noexists");
				response.setContentType("application/json");
				response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));

			}
		}
	}

}
