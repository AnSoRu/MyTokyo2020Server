package ws;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Pais;
import flexjson.JSONSerializer;
import model.PaisDAO;

/**
 * Servlet implementation class GetMedalDisplayTable
 */
public class GetMedalDisplayTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PaisDAO pDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMedalDisplayTable() {
        super();
        GetMedalDisplayTable.pDAO = new PaisDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Pais> paises = pDAO.findAllPaises();
		response.setContentType("application/json");
		response.getWriter().print(new JSONSerializer().exclude("class").exclude("deportistas").serialize(paises));
	}

}
