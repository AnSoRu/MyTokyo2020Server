package ws;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Evento;
import error.MensajeApp;
import flexjson.JSONSerializer;
import model.EventoDAO;

/**
 * Servlet implementation class GetEventosByDate
 */
public class GetEventosByDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EventoDAO eDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetEventosByDate() {
		super();
		GetEventosByDate.eDAO = new EventoDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fecha = request.getParameter("date");
		MensajeApp respuesta = null;
		if(fecha == null) {
			respuesta = new MensajeApp("error","missing");
		}else {
			if (fecha.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date dAux;
				try {
					dAux = format.parse(fecha);
					List<Evento> eAuxL = eDAO.getEventosByDate(dAux);
					if(!eAuxL.isEmpty()) {
						response.setContentType("application/json");
						response.getWriter().print(new JSONSerializer().exclude("class").serialize(eAuxL));
					}else {
						respuesta = new MensajeApp("error","noevents");
						response.setContentType("application/json");
						response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
					}
				} catch (ParseException e) {
					respuesta = new MensajeApp("error","badformat1");
					response.setContentType("application/json");
					response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
				}
			}
			else {
				respuesta = new MensajeApp("error","badformat2");
				response.setContentType("application/json");
				response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
			}
		}
	}

}
