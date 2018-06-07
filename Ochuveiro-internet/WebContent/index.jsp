	<%@page import="com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="br.com.univolt.dao.*, br.com.univolt.modelo.*" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Univolt - Uninove</title>
		<meta charset="utf-8">
        <link rel="icon" href="imagens/volt.jpg">
		<link rel="stylesheet" href="css/reset.css">
		<link rel="stylesheet" href="css/site.css">
		
		<script type="text/javascript">
			function cadastro()	{
				location.href="cadastro.jsp"
			}
			
			function lista(defineLocal) {
				
				if(defineLocal == 'completa') {
					location.href="defineLista?controle=completa";
				}
				
				if(defineLocal == 'busca') {
					var campo = document.getElementById('serial');
					location.href="defineLista?controle=busca&serial=" + campo.value;
				}
			}
		</script>
	</head>
	<body>
        <main>
			<h1 class="titulo-principal">Controle Consumo Chuveiro</h1>
            <div>
            <table border="0" width="70%">
            		<tr><td class="titulo">Serial</td>
                        <td class="titulo">Dia</td>
                        <td class="titulo">Tempo</td>
                        <td class="titulo">Centavos</td>
                        <td class="titulo">Consumo</td>
                    </tr>
            		
            		<% 
            			List<TabelaConsumo> lista = TabelaConsumoDAO.lista;
            			if(lista != null) {
            			for (TabelaConsumo table : lista){
            		%>
            	
            	
            	<tr>
            		<td><%=table.getSerial() %></td>
            		<td>
            			<%
            				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            				String strData = dateFormat.format(table.getData().getTime());
            				out.println(strData);
            			%>
            	   </td>
            		<td><%=table.getTempo() %></td>
            		<td><%=table.getValor() %></td>
            		
            			<%
            				if (table.getValor().compareTo("20.00") <= 0) {
            					out.println("<td class=\"consumo-baixo\">");	
            				} else {
            					if (table.getValor().compareTo("20.00") > 0 && table.getValor().compareTo("50.00") < 0) {
            						out.println("<td class=\"consumo-medio\">");
            					} else {
            						out.println("<td class=\"consumo-alto\">");
            					}
            				}
            				out.println(table.getConsumo()); 
            			%>
            		</td>
            	</tr>
            	<%}
            	  }		
            	  TabelaConsumoDAO.lista = null;%>
            </table>
			</div>
			
			<div>
                <input type="button" name="botao-busca" value="Buscar Serial" onclick="lista('busca')">
                <input type="text" name="text-serial" id="serial">
                <input style="margin-left: 20px" type="button" name="lista-completa" value="Lista Completa" onclick="lista('completa')">
                <input type="button" name="botao-cadastrar" value="Cadastrar" onclick="cadastro()">
            </div>
		</main>
		
		<footer class="rodape-pagina">
            <ul class="icones-redes-sociais">
				<li>
					<a class="github" href="https://github.com">
						Github
					</a>
				</li>
				<li>
					<a class="twitter" href="https://twitter.com">
						Twitter
					</a>
				</li>
				<li>
					<a class="linkedin" href="https://br.linkedin.com">
						LinkedIn
					</a>
				</li>
			</ul>
            &copy; Uninove 2018
		</footer>
	</body>
</html>