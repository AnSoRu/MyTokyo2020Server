package ws;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Disciplina;
import entities.Evento;
import error.MensajeApp;
import flexjson.JSONSerializer;
import model.DisciplinaDAO;
//import model.EventoDAO;

/**
 * Servlet implementation class GetEventosDisciplina
 */
public class GetEventosDisciplina extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DisciplinaDAO dDAO;
	//private static EventoDAO eDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEventosDisciplina() {
        super();
        GetEventosDisciplina.dDAO = new DisciplinaDAO();
        //GetEventosDisciplina.eDAO = new EventoDAO();        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String disciplina = request.getParameter("disciplina");
		MensajeApp respuesta = null;
		if(disciplina == null) {
			respuesta = new MensajeApp("error","noexists");
			response.setContentType("application/json");
			response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
		}else {
			List<Disciplina> dAuxL = dDAO.getDisciplinaByName(disciplina);
			if(!dAuxL.isEmpty()) {
				Disciplina d = dAuxL.get(0);
				Set<Evento> evDisp = d.getEventos();
				if(!evDisp.isEmpty()) {
					response.setContentType("application/json");
					response.getWriter().print(new JSONSerializer().exclude("class").exclude("usuarioCompraEventos").exclude("disciplina").serialize(evDisp));
				}else {
					respuesta = new MensajeApp("error","noevents");
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
