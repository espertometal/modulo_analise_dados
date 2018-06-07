package br.com.univolt.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.StringUtils;

import br.com.univolt.dao.TabelaConsumoDAO;

public class ListaServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("null")
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		
		String controle = request.getParameter("controle");
		
		TabelaConsumoDAO dao = null;
		if (controle.equals("completa")) {
			
			try {
				dao = new TabelaConsumoDAO();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			TabelaConsumoDAO.lista = dao.getListaCompleta();
		} else {
			if (controle.equals("busca")) {
				try {
					dao = new TabelaConsumoDAO();
					String serial = request.getParameter("serial");
					if (!StringUtils.isEmptyOrWhitespaceOnly(serial)) {
						TabelaConsumoDAO.lista = dao.buscaConsumo(Long.parseLong(serial));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}
}
