package dao;

//Importa a classe Connection do pacote java.sql
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexaoJdbc.SingleConnection;
//Importa a classe SingleConnection do pacote conexaoJdbc
import model.MinhaUserPosJava;

//Cria a classe UserPosJavaDao
public class MinhaDao {
	// Declara a variável connection como uma Connection
	private Connection connection;

	// Cria o construtor da classe
	public MinhaDao() {
		// Atribui à variável connection uma instância de Connection obtida através do método getConnection() da classe SingleConnection
		connection = SingleConnection.getConnection();
	}
	public void salvar(MinhaUserPosJava minhaUserPosJava) {
	    try {
	        String sql = "insert into cadastro_de_macacos (id, nome, email) values (?, ?, ?)";
	        PreparedStatement insert = connection.prepareStatement(sql);
	        insert.setLong(1, (minhaUserPosJava.getId()+1));
	        insert.setString(2, minhaUserPosJava.getNome());
	        insert.setString(3, minhaUserPosJava.getEmail());
	        insert.execute();
	        connection.commit(); //salva no banco
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	private Long getNextId() throws SQLException {
	    String sql = "select max(id) from cadastro_de_macacos";
	    PreparedStatement statement = connection.prepareStatement(sql);
	    ResultSet resultSet = statement.executeQuery();
	    if (resultSet.next()) {
	        return resultSet.getLong(1) + 1;
	    } else {
	        return 1L;
	    }
	}
}