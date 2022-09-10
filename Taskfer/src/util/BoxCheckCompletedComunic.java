package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Task;

public class BoxCheckCompletedComunic {
	Connection conect;
	PreparedStatement stat;
	ResultSet rs;
	
	public boolean ComunicCheck(Task task) throws SQLException {
	boolean bol = false;
	try {
		conect = ConnectionDataBase.ToConect();
		stat = conect.prepareStatement("SELECT completed FROM Task WHERE id = ?");
		stat.setInt(1, task.getID());
		rs = stat.executeQuery();
		while(rs.next()) {
			bol = rs.getBoolean(1);
		}
		

	}finally {
		 if (rs != null) {
             rs.close();
         }
         if (stat != null) {
             stat.close();
         }
         if (conect != null) {
             conect.close();
         }
	}
	if(bol == true) {
		return true;
	}else {
		return false;
	}
		
	}
	public void checkBooleanCompleted(Task task) throws SQLException {
		try {
			conect = ConnectionDataBase.ToConect();
			stat = conect.prepareStatement("UPDATE Task  SET completed = ?, status = ? WHERE id = ?;");
			stat.setBoolean(1, task.getCompleted());
			if(task.getCompleted() == true) {
				stat.setString(2, "concluído");
			}else 
			{
				stat.setString(2, task.getStatus());
			}
			
			
			stat.setInt(3, task.getID());
			stat.execute();
		}finally {
			 if (rs != null) {
	             rs.close();
	         }
	         if (stat != null) {
	             stat.close();
	         }
	         if (conect != null) {
	             conect.close();
	         }
		}
	
			
	}
	
}
