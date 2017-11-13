package ws;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Disciplina;
import error.MensajeApp;
import flexjson.JSONSerializer;
import model.DisciplinaDAO;

/**
 * Servlet implementation class GetDisciplina
 */
public class GetDisciplina extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DisciplinaDAO dDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDisciplina() {
        super();
        GetDisciplina.dDAO = new DisciplinaDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MensajeApp respuesta = null;
		String nombre = request.getParameter("nombre");
		if(nombre == null) {
			respuesta = new MensajeApp("error","missing");
		}else {
			List<Disciplina> dispAuxL = dDAO.getDisciplinaByName(nombre);
			if(!dispAuxL.isEmpty()) {
				Disciplina dAux = dispAuxL.get(0);
				response.setContentType("application/json");
				response.getWriter().print(new JSONSerializer().exclude("class").exclude("eventos").serialize(dAux));
			}else {
				respuesta = new MensajeApp("error","noexists");
				response.setContentType("application/json");
				response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
			}
		}
	}

}
