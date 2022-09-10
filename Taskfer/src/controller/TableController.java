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



public class TableController {
	ConnectionDataBase tControl = new ConnectionDataBase();
	Connection conect;
	PreparedStatement stat;
	ResultSet rs;
	public CachedRowSet UpdateTask() throws SQLException {
		CachedRowSet crs = tControl.ResultSetCache();
		try { 
			conect = ConnectionDataBase.ToConect();
			stat = conect.prepareStatement("SELECT `id`,`name`,`dateLimit`,`status` FROM `Task`;");
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
	public CachedRowSet UpdateProject() throws SQLException {
		CachedRowSet crs = tControl.ResultSetCache();
		try { 
			conect = ConnectionDataBase.ToConect();
			stat = conect.prepareStatement("SELECT `id`,`name` FROM `Project`;");
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
	public CachedRowSet UpdateTag() throws SQLException {
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