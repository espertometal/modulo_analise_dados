package br.com.univolt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.univolt.modelo.TabelaCadastro;

public class TabelaCadastroDAO {
	// conexão com o banco de dados
		private Connection connection;
		
		public TabelaCadastroDAO() throws SQLException{
			this.connection = new ConnectionFactory().getConnection();
			System.out.println("Conexão aberta");
		}
		
		// adiciona novo campo na tabela
		public void adiciona(TabelaCadastro tabela){
			String sql = "insert into tabela_chuveiro_cadastro (nome,email,codigo_ch) values(?,?,?)";
			
			try{
				// prepared statement para inserção
				PreparedStatement stmt = this.connection.prepareStatement(sql);
				
				// seta os valores
				stmt.setString(1, tabela.getNome());
				stmt.setString(2, tabela.getEmail());
				stmt.setLong(3, tabela.getCodigo());
							
				// executa
				stmt.execute();
				stmt.close();
				
			} catch (SQLException e){
				throw new RuntimeException(e);
			}
		}
		
		// atualiza campo na tabela
		public void altera(TabelaCadastro tabela){
			String sql = "update tabela_chuveiro_cadastro set nome=?, emailr=? where codigo_ch=?";
			
			try{
				PreparedStatement stmt = this.connection.prepareStatement(sql);
				
				stmt.setString(1, tabela.getNome());
				stmt.setString(2, tabela.getEmail());
				stmt.setLong(3, tabela.getCodigo());
				
				stmt.execute();
				stmt.close();
				
			} catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		// remove campo da tabela mediante a serial escolhida
		public void remove(Long codigo){
			try {
				String sql = "delete from tabela_chuveiro_cadastro where codigo_ch=?";
				
				PreparedStatement stmt = this.connection.prepareStatement(sql);
				
				stmt.setLong(1, codigo);
				stmt.execute();
				stmt.close();
			
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
}
