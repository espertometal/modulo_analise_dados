<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
	<head>
    	<title> | Cadastro </title>
    	<link rel="stylesheet" href="css/style.css">
	</head>

	<body>

    	<div class="container">
        	<div class="subhead">
           		 CHUVEIRO INFO
        	</div>
        		<form action="adicionaCadastro" method="post">
		        	<b>Nome:</b>
		        	<input type="text" name="nome" />
       
		        	<b>E-mail:</b>
		        	<input type="text" name="email"/>
		        
		        	<b>Código chuveiro:</b>
		        	<input type="text" name="codigo"/>
		         	
		         	<input type="submit" value="Gravar Dados"/>
		        </form>  	
    	</div>

	</body>
</html>



