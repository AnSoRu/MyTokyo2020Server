package ws;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Evento;
import entities.Usuario;
import entities.UsuarioCompraEvento;
import entities.UsuarioCompraEventoId;
import error.MensajeApp;
import flexjson.JSONSerializer;
import model.EventoDAO;
import model.UsuarioCompraEventoDAO;
import model.UsuarioDAO;

/**
 * Servlet implementation class BuyTicket
 */
public class BuyTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EventoDAO eDAO;
	private static UsuarioDAO uDAO;
	private static boolean yaComprado;
	private static UsuarioCompraEventoDAO uCDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyTicket() {
		super();
		BuyTicket.eDAO = new EventoDAO();
		BuyTicket.uDAO = new UsuarioDAO();
		BuyTicket.yaComprado = false;
		BuyTicket.uCDAO = new UsuarioCompraEventoDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String idEvento = request.getParameter("idEvento");
		MensajeApp respuesta = null;
		if(idEvento == null || email == null) {
			respuesta = new MensajeApp("error","missing");
			response.setContentType("application/json");
			response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
		}else {
			List<Evento> eAuxL = eDAO.getEventoByID(Integer.valueOf(idEvento));
			if(!eAuxL.isEmpty()) {
				List<Usuario> uAuxL = uDAO.getUsuarioByEmail(email);
				if(!uAuxL.isEmpty()) {
					Usuario uAux = uAuxL.get(0);
					Evento eAux = eAuxL.get(0);
					Set<UsuarioCompraEvento> eventosUser = uAux.getUsuarioCompraEventos();
					Set<UsuarioCompraEvento> usersEvento = eAux.getUsuarioCompraEventos();
					if(!eventosUser.isEmpty()) {
						for(UsuarioCompraEvento uce: eventosUser) {
							if((uce.getId().getUsuarioEmail().equals(uAux.getEmail()))&&
									(uce.getEvento().getIdEvento() == eAux.getIdEvento())) {
								yaComprado = true;
								break;
							}
						}
						if(yaComprado) {
							respuesta = new MensajeApp("error","yetbought");
							response.setContentType("application/json");
							response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
						}else {
							UsuarioCompraEventoId nuevoUCE = new UsuarioCompraEventoId(uAux.getEmail(),eAux.getIdEvento());
							UsuarioCompraEvento uCE = new UsuarioCompraEvento(nuevoUCE, eAux, uAux,new Random().nextFloat(),new Date(),new Date());
							if(uCDAO.insert(uCE)) {
								respuesta = new MensajeApp("ok","bought1");
								response.setContentType("application/json");
								response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
							}else {
								respuesta = new MensajeApp("error","noinserted");
								response.setContentType("application/json");
								response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
							}
						}
					}else {
						UsuarioCompraEventoId nuevoUCE = new UsuarioCompraEventoId(uAux.getEmail(),eAux.getIdEvento());
						UsuarioCompraEvento uCE = new UsuarioCompraEvento(nuevoUCE, eAux, uAux,new Random().nextFloat(), new Date(), new Date());
						eventosUser.add(uCE);
						usersEvento.add(uCE);
						eAux.setUsuarioCompraEventos(usersEvento);
						uAux.setUsuarioCompraEventos(eventosUser);
						if(uCDAO.insert(uCE)) {
							respuesta = new MensajeApp("ok","bought2");
							response.setContentType("application/json");
							response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
						}else {
							respuesta = new MensajeApp("error","noinserted");
							response.setContentType("application/json");
							response.getWriter().print(new JSONSerializer().exclude("class").serialize(respuesta));
						}
					}
				}else {
					respuesta = new MensajeApp("error","noexists");
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
