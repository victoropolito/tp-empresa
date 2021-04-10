package Login_Connection_Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectLogin {

	public static Connection do_connection() throws SQLException {
		
		
		//trata possíveis erros que podem acontecer na conexão com o bd
		
		try {
			
			//instrução para carregar driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//obter conexão através do drive manager: caminho do bd, usuário, senha
			return DriverManager.getConnection("jdbc:mysql://localhost/db_passwords","root"," ");
		
			
			
		} catch (ClassNotFoundException e) {
			
			throw new SQLException(e.getException());
			
		}
	}

}
