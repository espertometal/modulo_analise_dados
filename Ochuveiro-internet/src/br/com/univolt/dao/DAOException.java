package br.com.univolt.dao;

import java.sql.SQLException;

public class DAOException extends RuntimeException{

	/**
	 * classe para tratamento de erro de conexão
	 */
	private static final long serialVersionUID = -2836755488053678205L;

	public DAOException(SQLException e) {
		System.out.println("Error DAO "+ e.getMessage());
	}
	

}
