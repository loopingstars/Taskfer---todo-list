package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.Resultset;
//para mostrar na tabela
import net.proteanit.sql.DbUtils;
import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.util.Date;
//IMPORTS
import model.Task;
import controller.SetGetAllSQL;
import controller.TableController;
import controller.TaskController;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
//import controller.TableController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

import util.ConnectionDataBase;
import util.Dati;

public class AddTasks {
	Connection conect = null;
	PreparedStatement stat = null;
	ResultSet rs = null;
	JFrame frmCriarTarefa;
	//BOOLEANS
	private static boolean edit = false;
	
	//IDS
	private static int TaksID = 1;
	int idProject = 1;
	int idTag = 1;
	SetGetAllSQL SGALL = new SetGetAllSQL();
	TableController tblControl = new TableController();
	private JTextField addTaskInputNome;
	private JLabel addTaskLabelDescri;
	private JTextField addTaskInputDateLimit;
	private JTable tableAddTaskProject;
	private JTable tableAddTaskTag;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTasks window = new AddTasks(TaksID, edit);
					window.frmCriarTarefa.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddTasks(int num,boolean ed) {
		TaksID = num;
		edit = ed;
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCriarTarefa = new JFrame();
		frmCriarTarefa.setBackground(Color.WHITE);
		frmCriarTarefa.setResizable(false);
		frmCriarTarefa.setTitle("criar tarefa");
		frmCriarTarefa.setBounds(100, 100, 620, 493);
		frmCriarTarefa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCriarTarefa.getContentPane().setLayout(null);
		final JLabel addTaskLabelNome = new JLabel("nome");
		addTaskLabelNome.setForeground(Color.WHITE);
		addTaskLabelNome.setBackground(Color.WHITE);
		addTaskLabelNome.setBounds(285, 12, 39, 15);
		frmCriarTarefa.getContentPane().add(addTaskLabelNome);
		addTaskInputNome = new JTextField();
		addTaskInputNome.setColumns(10);
		addTaskInputNome.setBounds(133, 34, 343, 19);
		frmCriarTarefa.getContentPane().add(addTaskInputNome);

		final JTextArea addTaskInputDescri = new JTextArea();
		addTaskInputDescri.setBorder(new CompoundBorder());
		addTaskInputDescri.setBounds(12, 105, 586, 142);
		frmCriarTarefa.getContentPane().add(addTaskInputDescri);
		addTaskLabelDescri = new JLabel("descrição");
		addTaskLabelDescri.setForeground(Color.WHITE);
		addTaskLabelDescri.setBounds(270, 78, 70, 15);
		frmCriarTarefa.getContentPane().add(addTaskLabelDescri);

		
		JLabel addTaskFieldTag = new JLabel("Tag");
		addTaskFieldTag.setForeground(Color.WHITE);
		addTaskFieldTag.setBounds(84, 264, 31, 15);
		frmCriarTarefa.getContentPane().add(addTaskFieldTag);
		
		JLabel addTaskLabelDateLimit = new JLabel("data limite");
		addTaskLabelDateLimit.setForeground(Color.WHITE);
		addTaskLabelDateLimit.setBounds(264, 264, 81, 15);
		frmCriarTarefa.getContentPane().add(addTaskLabelDateLimit);
			
		addTaskInputDateLimit = new JTextField();
		addTaskInputDateLimit.setBounds(238, 291, 126, 19);
		frmCriarTarefa.getContentPane().add(addTaskInputDateLimit);
		addTaskInputDateLimit.setColumns(10);
		
		JButton addTaskButton = new JButton("criar");
		addTaskButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TaskController tcontrol = new TaskController();
					String stringName = addTaskInputNome.getText();
					String stringDesc = addTaskInputDescri.getText();
					String stringDateLimit = addTaskInputDateLimit.getText();	
					int idTag = (int) tableAddTaskTag.getValueAt(tableAddTaskTag.getSelectedRow(), 0);
					int idProject = (int) tableAddTaskProject.getValueAt(tableAddTaskProject.getSelectedRow(), 0);
					Task task = new Task(stringDateLimit);	
					task.setName(stringName);
					task.setDescription(stringDesc);
					task.setIdProject(idProject);
					task.setIdTag(idTag);
					tcontrol.insertTask(task);	
					
				} 
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}	
		});
		addTaskButton.setBounds(247, 422, 117, 25);
		frmCriarTarefa.getContentPane().add(addTaskButton);

		
		JLabel addTaskFieldProjeto = new JLabel("Projeto");
		addTaskFieldProjeto.setForeground(Color.WHITE);
		addTaskFieldProjeto.setBounds(474, 264, 52, 15);
		frmCriarTarefa.getContentPane().add(addTaskFieldProjeto);
		
		tableAddTaskProject = new JTable();
		tableAddTaskProject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//PROJETO CLICADO VAMOS MUDA O VALOR DA VARIAVEL
				idProject = (int) tableAddTaskProject.getValueAt(tableAddTaskProject.getSelectedRow(), 0);
			}
		});
		tableAddTaskProject.setColumnSelectionAllowed(true);
		tableAddTaskProject.setCellSelectionEnabled(true);
		
		tableAddTaskProject.setBounds(403, 293, 195, 154);
		frmCriarTarefa.getContentPane().add(tableAddTaskProject);
		tableAddTaskProject.setDefaultEditor(Object.class,null);
		

			try {
				tableAddTaskProject.setModel(DbUtils.resultSetToTableModel(tblControl.UpdateProject()));
			} catch (SQLException e2) {
				// TODO Bloco catch gerado automaticamente
				e2.printStackTrace();
			}
			tableAddTaskProject.getColumnModel().getColumn(0).setPreferredWidth(1);
			tableAddTaskProject.getColumnModel().getColumn(1).setPreferredWidth(200);
	
		
	
		tableAddTaskTag = new JTable();
		tableAddTaskTag.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//QUANDO CLICA NA TABElA TAG ATuaLIZA O VALOR PADRAO dE IDTAG
				idTag = (int) tableAddTaskTag.getValueAt(tableAddTaskTag.getSelectedRow(), 0);
			}
			
		});
		tableAddTaskTag.setBounds(12, 291, 195, 156);
		frmCriarTarefa.getContentPane().add(tableAddTaskTag);

			try {
				tableAddTaskTag.setModel(DbUtils.resultSetToTableModel(tblControl.UpdateTag()));
			} catch (SQLException e2) {
				// TODO Bloco catch gerado automaticamente
				e2.printStackTrace();
			}
			tableAddTaskTag.getColumnModel().getColumn(0).setPreferredWidth(1);
			tableAddTaskTag.getColumnModel().getColumn(1).setPreferredWidth(200);
	
		/////////////////////////////////////////
		JButton btnAtualizar = new JButton("atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//EVENTO DE ATUALIZAR
				try {
					//PEGA TODOS OS DADOS SELECIONADOS E ATUALIZA
					TaskController tcontrol = new TaskController();
					String stringName = addTaskInputNome.getText();
					String stringDesc = addTaskInputDescri.getText();
					String stringDateLimit = addTaskInputDateLimit.getText();	
					
					
					Task task = new Task(stringDateLimit);
					task.setName(stringName);
					task.setID(TaksID);
					task.setDescription(stringDesc);
					task.setIdProject(idProject);
					task.setIdTag(idTag);
					tcontrol.UpdateTask(task);
					JOptionPane.showMessageDialog(null, "Tarefa Atualizada!");
					frmCriarTarefa.dispose();					
				}catch (SQLException e1) {
					e1.printStackTrace();
			
				}
		
			}
		});
		btnAtualizar.setBounds(247, 385, 117, 25);
		frmCriarTarefa.getContentPane().add(btnAtualizar);
		JLabel addTaskWalparper = new JLabel("New label");
		addTaskWalparper.setIcon(new ImageIcon("/home/fear404/Downloads/6457dc8f063f284fe17e519dc28b5437.jpg"));
		addTaskWalparper.setBounds(0, 0, 610, 471);
		frmCriarTarefa.getContentPane().add(addTaskWalparper);
		//Screen screen = new Screen();
		if(edit == true) {
			//PEGA OS DADOS DO BANCO 
			addTaskButton.setEnabled(false);
			String retur = null;
			String desc = null;
			Date date = null;
			try {
				conect = ConnectionDataBase.ToConect();
				stat = conect.prepareStatement("SELECT id,name,dateLimit,description,status,idProject,idTag FROM Task WHERE id= ?");
				stat.setInt(1, (int)TaksID);
				rs = stat.executeQuery();
				while(rs.next()){
					retur = rs.getString("name");
					desc = rs.getString("description");
					date = rs.getDate("dateLimit");
					idProject = rs.getInt("idProject");
					idTag = rs.getInt("idTag");
				}
				if(conect != null) {
					conect.close();
				}
				if(stat != null) {
					stat.close();
				}
					
		
			} catch (SQLException e1) {
				// TODO Bloco catch gerado automaticamente

				e1.printStackTrace();
			}
			
			java.util.Date dbSqlDateConverted = new java.util.Date(date.getTime());
			addTaskInputDateLimit.setText(Dati.convertString(dbSqlDateConverted));
			addTaskInputDescri.setText(desc);//
			addTaskInputNome.setText(retur);
		
		
		}
		else {
			btnAtualizar.setEnabled(false);
		}
	}
}
