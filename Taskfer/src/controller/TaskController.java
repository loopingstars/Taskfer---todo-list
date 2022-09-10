package controller;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.protocol.Resultset;
import util.ConnectionDataBase;
import model.Task;

public class TaskController {
	Connection conect;
	PreparedStatement stat;
	

	
	public void insertTask (Task task) throws SQLException{		
		try {
		
			conect = ConnectionDataBase.ToConect();
			stat = conect.prepareStatement("INSERT INTO Task (`name`, `description`,`status`, `idProject`,`idTag`,`completed`,`dateCreate`,`dateLimit`) VALUES (?,?,?,?,?,?,?,?);");		
			stat.setString(1,task.getName());
			stat.setString(2,task.getDescription());
			stat.setString(3, task.getStatus());
			stat.setInt(4, task.getIdProject());
			stat.setInt(5,task.getIdTag());
			stat.setBoolean(6,task.getCompleted());
			stat.setDate(7, new java.sql.Date(task.getDateCreate().getTime()));
			stat.setDate(8, new java.sql.Date(task.getDateLimit().getTime()));
			stat.execute();
		
		}finally {
			
			conect.close();
			stat.close();
		}
		
	
	}
	public void UpdateTask (Task task) throws SQLException{		
		try {
		
			conect = ConnectionDataBase.ToConect();
			stat = conect.prepareStatement("UPDATE Task  SET name = ?, description = ?,idProject = ?,idTag = ?,dateLimit = ? WHERE id = ?;");		
			stat.setString(1,task.getName());
			stat.setString(2,task.getDescription());
			stat.setInt(3, task.getIdProject());
			stat.setInt(4,task.getIdTag());
			stat.setDate(5, new java.sql.Date(task.getDateLimit().getTime()));
			stat.setInt(6,task.getID());
			stat.execute();
		
		}finally {
			
			conect.close();
			stat.close();
		}
		
	
	}
	
}
