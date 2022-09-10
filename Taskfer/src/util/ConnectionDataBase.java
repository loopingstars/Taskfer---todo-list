package util;

//IMPORTS
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

//IMPORTS PARA USAR
//para usar o result
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.Resultset;
//para mostrar na tabela
import net.proteanit.sql.DbUtils;
import net.proteanit.sql.DbUtils;

//
//FORMA DE CONEXAO EXEMPLO
//cnt();
//stat = conect.prepareStatement("SELECT * FROM `pessoa`;");
//rs = (Resultset) stat.executeQuery();
//table.setModel(DbUtils.resultSetToTableModel((ResultSet) rs));
//

public class ConnectionDataBase{
static private String name = "root";
static private String pass = "";
static private String url = "com.mysql.cj.jdbc.Driver";
//GLOBAL VARIABLES
	public static  java.sql.Connection ToConect(){
		//conexão com o banco de dados e jbdc(driver)
		try {
			//conexao com driver jbdc
			Class.forName(getUrl());
			//Conexão com Banco de Dados
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/Taskfer",getName(),getPass());
			
		}catch(ClassNotFoundException ex) {
			
			System.out.print("driver not found");
			return null;
		}
		catch(SQLException ex) {
			
			System.out.print("exception"+ ex.getMessage());
			return null;

		}
		
	}
	public CachedRowSet ResultSetCache() {
		//Retorna um CachedRowSet Configurado
		try {
			RowSetFactory factory = RowSetProvider.newFactory();
			CachedRowSet crs = factory.createCachedRowSet();
			crs.setUsername(getName());
			crs.setPassword(getPass());
			crs.setUrl(getUrl());
			return crs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	
	
	}
	public static String getName() {
		return name;
	}
	public static  String getPass() {
		return pass;
	}
	
	public static String getUrl() {
		return url;
	}

	


}