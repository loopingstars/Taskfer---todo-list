package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import util.ConnectionDataBase;
import com.mysql.cj.protocol.Resultset;

//import controller.TableController;
import net.proteanit.sql.DbUtils;
import util.ConnectionDataBase;

public class InitialScreen {
	public Connection conect;
	//TableController tControl = new TableController();
	PreparedStatement stat;
	ResultSet rs;
	private JFrame initial;
	private JTable initialTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitialScreen window = new InitialScreen();
					window.initial.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InitialScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		initial = new JFrame();
		initial.setResizable(false);
		initial.setExtendedState(Frame.MAXIMIZED_VERT);
		initial.setBounds(100, 100, 734, 615);
		initial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initial.getContentPane().setLayout(null);
		
		JLabel title = new JLabel("Taskfer - Todo & Task");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Garuda", Font.BOLD, 56));
		title.setBounds(70, 12, 584, 58);
		initial.getContentPane().add(title);
		
		initialTable = new JTable();
		initialTable.setBounds(70, 225, 566, 309);
		initial.getContentPane().add(initialTable);
		initialTable.setDefaultEditor(Object.class,null);

		
		try {
			conect = ConnectionDataBase.ToConect();
			stat = conect.prepareStatement("SELECT `name`,`dateLimit`,`status` FROM `Task`");
			rs = stat.executeQuery();			
			initialTable.setModel(DbUtils.resultSetToTableModel((ResultSet) rs));
			initialTable.getColumnModel().getColumn(0).setPreferredWidth(300);
			initialTable.getColumnModel().getColumn(1).setPreferredWidth(20);
			initialTable.getColumnModel().getColumn(2).setPreferredWidth(20);
			if(conect != null) {
				conect.close();
		
			}
			if(stat != null) {
				stat.close();
			}
			
		} catch (SQLException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
	
		
		
		
		JLabel lblHoje = new JLabel("Tarefas de Hoje");
		lblHoje.setForeground(Color.WHITE);
		lblHoje.setFont(new Font("Dialog", Font.BOLD, 20));
		lblHoje.setBounds(271, 161, 181, 15);
		initial.getContentPane().add(lblHoje);
		
		JButton btnEditar = new JButton("Entrar");
		btnEditar.setBounds(307, 96, 117, 25);
		initial.getContentPane().add(btnEditar);
		
		JLabel lblTarefas = new JLabel("tarefas");
		lblTarefas.setForeground(Color.WHITE);
		lblTarefas.setBounds(70, 202, 70, 15);
		initial.getContentPane().add(lblTarefas);
		
		JLabel lblData = new JLabel("data");
		lblData.setForeground(Color.WHITE);
		lblData.setBounds(382, 202, 70, 15);
		initial.getContentPane().add(lblData);
		
		JLabel lblStatus = new JLabel("status");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setBounds(504, 202, 70, 15);
		initial.getContentPane().add(lblStatus);
		JLabel addTaskWalparper = new JLabel("New label");
		addTaskWalparper.setIcon(new ImageIcon("/home/fear404/Downloads/6457dc8f063f284fe17e519dc28b5437.jpg"));
		addTaskWalparper.setBounds(0, 0, 723, 599);
		initial.getContentPane().add(addTaskWalparper);
	}
}
