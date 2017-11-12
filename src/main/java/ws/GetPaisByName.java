package ws;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Pais;
import error.MensajeApp;
import flexjson.JSONSerializer;
import model.PaisDAO;

/**
 * Servlet implementation class GetPaisByName
 */
public class GetPaisByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PaisDAO pDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPaisByName() {
        super();
        GetPaisByName.pDAO = new PaisDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombrePais = request.getParameter("nombre");
		MensajeApp respuesta = null;
		if(nombrePais == null) {
			respuesta = new MensajeApp("error","missing");
			response.setContentType("application/json");
			response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
		}else {
			List<Pais> pAuxL = pDAO.getPaisByName(nombrePais);
			if(!pAuxL.isEmpty()) {
				response.setContentType("application/json");
				response.getWriter().print(new JSONSerializer().exclude("class").exclude("deportistas").serialize(pAuxL));
			}else {
				respuesta = new MensajeApp("error","noexists");
				response.setContentType("application/json");
				response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
			}
		}
	}

}
