package br.com.univolt.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.univolt.dao.TabelaCadastroDAO;
import br.com.univolt.modelo.TabelaCadastro;


public class AdicionaCadastroServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
							throws IOException, ServletException{
		
		// buscando os parâmetros no request
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String codigo = request.getParameter("codigo");
		
		// objeto cadastro
		TabelaCadastro cadastro = new TabelaCadastro();
		
		cadastro.setNome(nome);
		cadastro.setEmail(email);
		cadastro.setCodigo(Long.parseLong(codigo));
		
		// salvar cadastro
		try {
			TabelaCadastroDAO dao = new TabelaCadastroDAO();
			dao.adiciona(cadastro);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}
}
