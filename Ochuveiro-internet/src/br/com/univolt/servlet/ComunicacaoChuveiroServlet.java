package br.com.univolt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import br.com.univolt.chuveiroRN.Calculo;
import br.com.univolt.dao.TabelaConsumoDAO;
import br.com.univolt.modelo.TabelaConsumo;

public class ComunicacaoChuveiroServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{ 
		
		String tempo = request.getParameter("tempo");
		String codigo = request.getParameter("codigo");
				
		Calculo consumo = new Calculo(tempo);
		
		Date data = new Date();
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			formato.format(data);
		} catch (ParseException e) {
			e.printStackTrace();			
		}
		
		TabelaConsumo tabela = new TabelaConsumo();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		tabela.setData(cal);
		tabela.setTempo(consumo.msToHourSecond());
		
		DecimalFormat df = new DecimalFormat("0.##");
		String valor = df.format(consumo.getCusto());
		tabela.setValor(valor);
		
		if (valor.compareTo("20.00") <= 0) {
			tabela.setConsumo("Baixo");
		} else {
			if (valor.compareTo("20.00") > 0 && valor.compareTo("50.00") < 0) {
				tabela.setConsumo("Médio");
			} else {
				tabela.setConsumo("Alto");
			}
		}
		
		TabelaConsumoDAO dao;
		try {
			dao = new TabelaConsumoDAO();
			dao.adiciona(tabela);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("tempo: " + consumo.msToHourSecond() + " valor: " + consumo.getCusto());
	}
}
