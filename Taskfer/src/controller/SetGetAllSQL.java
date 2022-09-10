package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import com.mysql.cj.protocol.Resultset;
import util.ConnectionDataBase;
import view.AddTasks;
import view.InitialScreen;
import view.Screen;

public class SetGetAllSQL {
	
	ConnectionDataBase tControl = new ConnectionDataBase();
	Connection conect;
	PreparedStatement stat;
	ResultSet rs;
	//////////////////////
	//GET ALL FUNCTIONS///
	/////////////////////
	public CachedRowSet GetAllTask() throws SQLException {
		CachedRowSet crs = tControl.ResultSetCache();
		try { 
			conect = ConnectionDataBase.ToConect();
			stat = conect.prepareStatement("SELECT id,name,dateLimit,description,status,idProject,idTag FROM Task WHERE id= ?");
			rs = stat.executeQuery();
			crs.populate(rs);
			
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
		return crs;	
	}
	public CachedRowSet GetAllProject() throws SQLException {
		CachedRowSet crs = tControl.ResultSetCache();
		try { 
			conect = ConnectionDataBase.ToConect();
			stat = conect.prepareStatement("SELECT id,name FROM Tag;");
			rs = stat.executeQuery();
			crs.populate(rs);
			
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
		return crs;	
	}
	public CachedRowSet GetAllTag() throws SQLException {
		CachedRowSet crs = tControl.ResultSetCache();
		try { 
			conect = ConnectionDataBase.ToConect();
			stat = conect.prepareStatement("SELECT id,name FROM Tag;");
			rs = stat.executeQuery();
			crs.populate(rs);
			
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
		return crs;	
	}
}
