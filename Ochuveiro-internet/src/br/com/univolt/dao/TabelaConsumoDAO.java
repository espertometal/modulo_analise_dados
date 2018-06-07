package br.com.univolt.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.univolt.modelo.TabelaConsumo;

public class TabelaConsumoDAO {
	
	public static List<TabelaConsumo> lista;
	
	// conexão com o banco de dados
	private Connection connection;
	
	public TabelaConsumoDAO() throws SQLException{
		this.connection = new ConnectionFactory().getConnection();
		System.out.println("Conexão aberta");
	}
	
	// adiciona novo campo na tabela
	public void adiciona(TabelaConsumo tabela){
		String sql = "insert into tabela_consumo (data,tempo,valor,consumo) values(?,?,?,?)";
		
		try{
			// prepared statement para inserção
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setDate(1, new Date(tabela.getData().getTimeInMillis()));
			stmt.setString(2, tabela.getTempo());
			stmt.setString(3, tabela.getValor());
			stmt.setString(4, tabela.getConsumo());
			
			// executa
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	// atualiza campo na tabela
	public void altera(TabelaConsumo tabela){
		String sql = "update tabela_consumo set data=?, tempo=?, valor=?, consumo=? where serial=?";
		
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			
			stmt.setDate(1, new Date(tabela.getData().getTimeInMillis()));
			stmt.setString(2, tabela.getTempo());
			stmt.setString(3, tabela.getValor());
			stmt.setString(4, tabela.getConsumo());
			stmt.setLong(5, tabela.getSerial());
			
			stmt.execute();
			stmt.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// remove campo da tabela mediante a serial escolhida
	public void remove(Long serial){
		try {
			String sql = "delete from tabela_consumo where serial=?";
			
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			
			stmt.setLong(1, serial);
			stmt.execute();
			stmt.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// lista de consumo 
	public List<TabelaConsumo> getListaCompleta(){
		String sql ="select * from tabela_consumo";
		try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<TabelaConsumo> lista = new ArrayList<TabelaConsumo>();
			
			while(rs.next()){
				TabelaConsumo consumo = createContato(rs);
				lista.add(consumo);
			}
			rs.close();
			stmt.close();
			
			return lista;
			
		}catch(SQLException e){
			throw new DAOException(e);
		}
	}
	
	// busca pela serial
	public List<TabelaConsumo> buscaConsumo(Long serial) {
		String sql = "select * from tabela_consumo where serial=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, serial);
			
			ResultSet rs = stmt.executeQuery();
			
			List<TabelaConsumo> lista = new ArrayList<TabelaConsumo>();

			while (rs.next()) {
				TabelaConsumo consumo = createContato(rs);
				lista.add(consumo);
			}
			rs.close();
			stmt.close();

			return lista;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	private TabelaConsumo createContato(ResultSet rs){
		try{
			TabelaConsumo consumo = new TabelaConsumo();
			consumo.setSerial(rs.getLong("serial"));
			consumo.setTempo(rs.getString("tempo"));
			consumo.setValor(rs.getString("valor"));
			consumo.setConsumo(rs.getString("consumo"));
			
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data"));
			consumo.setData(data);

			return consumo;
		}catch(SQLException e){
			throw new DAOException(e);
		}
	}
}

