package project.screens;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectLogin {

	public static Connection do_connection() throws SQLException {
		
		
		//trata poss�veis erros que podem acontecer na conex�o com o bd
		
		try {
			
			//instru��o para carregar driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//obter conex�o atrav�s do drive manager: caminho do bd, usu�rio, senha
			return DriverManager.getConnection("jdbc:mysql://localhost/db_passwords","root","");
		
			
			
		} catch (ClassNotFoundException e) {
			
			throw new SQLException(e.getException());
			
		}
	}

}
