package view;
import util.ConnectionDataBase;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.mysql.cj.protocol.Resultset;

import model.Task;
//import controller.TableController;
import net.proteanit.sql.DbUtils;
import view.AddTasks;
import java.awt.Frame;
import java.awt.Cursor;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import java.awt.Label;
import java.awt.ScrollPane;
import java.awt.SystemColor;
import javax.swing.JTree;
import java.awt.Canvas;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JEditorPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;

import controller.TableController;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBox;
import util.BoxCheckCompletedComunic;
public class Screen{
	
	//Jframe
	private JFrame frmTasfertodo;

	///Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Screen window = new Screen();
					window.frmTasfertodo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	///Valores dos Id Passados para o SQL
	int valueTask;
	int valueProject;
	int valueTag;
	///////////////////////
	
	String completedTest;
	boolean boolCompletedCahe;
	
	//Classes de Conexão
	ConnectionDataBase tControl = new ConnectionDataBase();
	TableController tblControl = new TableController();
	////////////////////
	
	///Classes Janelas e Componentes
	private JTable project_table;
	private JTable task_table;
	private JTable tag_table;
	//////////////////////
	
	
	/**
	 * Create the application.
	 */

	public Screen() {
		initialize();
		}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//CELL RENDER
		final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
		    private static final long serialVersionUID = 5137633061871165174L;

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        Component c = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 

		        String str = (String) value;
		        if ("ativo".equals(str)) {
		           c.setBackground(Color.yellow);
		        }
		        else if("atrasado".equals(str)) {
		        	c.setBackground(Color.red);
		        }
		        else if("concluído".equals(str)) {
		        	c.setBackground(Color.green);
		        }
		        else {
		        	c.setBackground(Color.gray);
		        }
		       
		        return c;
		    }
		};
		//
		frmTasfertodo = new JFrame();
		frmTasfertodo.setResizable(false);
		frmTasfertodo.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				//atualiza as tabelas sempre que a janela ganha foco
				tableTaskUpdate(task_table, tblControl);
				tableProjectUpdate(project_table, tblControl);
				tableTagUpdate(tag_table, tblControl);
				task_table.getColumnModel().getColumn(3).setCellRenderer(renderer);
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
		frmTasfertodo.setBackground(SystemColor.window);
		frmTasfertodo.getContentPane().setBackground(SystemColor.window);
		frmTasfertodo.setTitle("Tasfer-ToDo");
		frmTasfertodo.setBounds(100, 100, 620, 503);
		frmTasfertodo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTasfertodo.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(86, 59, 1, 1);
		frmTasfertodo.getContentPane().add(layeredPane);

				//////////////////
		//////////Project Table////////////////
				/////////////////
		project_table = new JTable();
		project_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		project_table.setSelectionBackground(SystemColor.window);
		project_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		project_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		project_table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		project_table.setBounds(12, 128, 189, 330);
		project_table.setDefaultEditor(Object.class,null);
		//frmTasfertodo.getContentPane().add(project_table);
		

		//cria o scrool da tabela!
		JScrollPane ScrollProjectTable = new JScrollPane();
		ScrollProjectTable.setBounds(12, 127, 201, 330);
		ScrollProjectTable.setViewportView(project_table);
		//impede a rolagem das colunas
		project_table.getTableHeader().setReorderingAllowed(false);
		frmTasfertodo.getContentPane().add(ScrollProjectTable);
		tableProjectUpdate(project_table, tblControl);
		
		////////////////////
		//LABEL PROJETO/////
		////////////////////
		JLabel lblProjetos = new JLabel("projetos");
		lblProjetos.setFont(new Font("FreeSans", Font.BOLD | Font.ITALIC, 15));
		lblProjetos.setForeground(SystemColor.text);
		lblProjetos.setBounds(135, 108, 66, 15);
		frmTasfertodo.getContentPane().add(lblProjetos);

		////////////////////
		//CRIAR PROJETO/////
		////////////////////
		JButton btnCriarProjeto = new JButton("");
		btnCriarProjeto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCriarProjeto.setForeground(Color.WHITE);
		btnCriarProjeto.setBackground(Color.WHITE);
		btnCriarProjeto.setIcon(new ImageIcon("/home/fear404/Área de Trabalho/iconeadd16.png"));
		btnCriarProjeto.setBounds(12, 100, 18, 19);
		frmTasfertodo.getContentPane().add(btnCriarProjeto);
		btnCriarProjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				AddProject winProject = new AddProject();
				winProject.frmCriarProjeto.setVisible(true);
			}
		});
		//
		////////////////////
		//DELETAR PROJETO/////
		////////////////////
		JButton btnDeletarProject = new JButton("");
		btnDeletarProject.setBackground(SystemColor.window);
		btnDeletarProject.setBounds(42, 100, 18, 19);
		frmTasfertodo.getContentPane().add(btnDeletarProject);
		btnDeletarProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		//
		////////////////////
		//EDITAR PROJETO/////
		////////////////////
		JButton btnEditarProject = new JButton("");
		btnEditarProject.setBackground(SystemColor.window);
		btnEditarProject.setBounds(72, 100, 18, 19);
		frmTasfertodo.getContentPane().add(btnEditarProject);
		btnEditarProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		//
		//////////////////////////
		/////////////////////////
		////////////////////////
	
		
				///////////////////
		//////////Task Table/////////////////
				//////////////////
		task_table = new JTable();
		task_table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		task_table.setShowGrid(false);
		task_table.setDefaultEditor(Object.class,null);
		final JCheckBox checkCompleted = new JCheckBox("");
		checkCompleted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//////////////////////////////
				//EVENTO DO BOTAO DE CHECKBOX/ 
				//////////////////////////////
				Task task = new Task("03/11/2001");
				BoxCheckCompletedComunic bcc = new BoxCheckCompletedComunic();
				boolean myBool;
				if(boolCompletedCahe == true) {
					myBool = false;
				}
				else {
					myBool = true;
					
				}
				task.setCompleted(myBool);
				task.setID(valueTask);
				try {
					bcc.checkBooleanCompleted(task);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				tableTaskUpdate(task_table,tblControl);
				task_table.getColumnModel().getColumn(3).setCellRenderer(renderer);
			}
		});
		
		checkCompleted.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		checkCompleted.setBounds(577, 100, 21, 23);
		frmTasfertodo.getContentPane().add(checkCompleted);
		task_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				///////////////////////////////////////////////
				// CAPTURA O VALOR AO CLICAR NA TABELA -> TASKS/// 
				//////////////////////////////////////////////
				valueTask = Integer.parseInt(task_table.getValueAt(task_table.getSelectedRow(), 0).toString());	
				///////////////
				
				//RENDER
				//atualiza a coluna status de acordo com o botão 
				try {
					Task task = new Task("03/11/2001");
					BoxCheckCompletedComunic bcc = new BoxCheckCompletedComunic();
					task.setID(valueTask);
				
					if(bcc.ComunicCheck(task) == true) {
						checkCompleted.setSelected(true);
					}
					else {
						checkCompleted.setSelected(false);
					}
					boolCompletedCahe = bcc.ComunicCheck(task);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}			
			}
		});
		
		
		
		task_table.setGridColor(SystemColor.window);
		task_table.setSelectionBackground(SystemColor.window);
		task_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		task_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		task_table.setBounds(284, 148, 314, 33);
		//cria o scrool da tabela!
		JScrollPane ScrollTaskTable = new JScrollPane();
		ScrollTaskTable.setBounds(223, 127, 375, 330);
		ScrollTaskTable.setViewportView(task_table);
		//impede a rolagem das colunas
		task_table.getTableHeader().setReorderingAllowed(false);
		//
		/////////////////
		//LABEL TASK///
		/////////////////
		JLabel lblId = new JLabel("Tarefas");
		lblId.setFont(new Font("FreeSans", Font.BOLD | Font.ITALIC, 15));
		lblId.setForeground(SystemColor.text);
		lblId.setBounds(380, 104, 66, 15);
		frmTasfertodo.getContentPane().add(lblId);
		/////////////////
		//DELETAR TASK///
		/////////////////
		JButton btnDeletarTask = new JButton("");
		btnDeletarTask.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeletarTask.setBackground(SystemColor.window);
		btnDeletarTask.setIcon(new ImageIcon("/home/fear404/Área de Trabalho/Materiais Compartilhados/resources/delete.png"));
		btnDeletarTask.setBounds(251, 100, 18, 19);
		frmTasfertodo.getContentPane().add(btnDeletarTask);
		btnDeletarTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
			}
		});
		//
		
		//////////////
		//CRIAR TASK//
		/////////////
		JButton btnCriarTask = new JButton("");
		btnCriarTask.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCriarTask.setIcon(new ImageIcon("/home/fear404/Área de Trabalho/iconeadd16.png"));
		btnCriarTask.setForeground(Color.WHITE);
		btnCriarTask.setBackground(Color.WHITE);
		btnCriarTask.setBounds(223, 100, 18, 19);
		frmTasfertodo.getContentPane().add(btnCriarTask);
		btnCriarTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				AddTasks winProject = new AddTasks(valueTask, false);
				winProject.frmCriarTarefa.setVisible(true);
			}
		});
		//
		///////////////
		//EDITAR TASK//
		//////////////
		JButton btnEditarTaks = new JButton("");
		btnEditarTaks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditarTaks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//EDITAR TASK
				AddTasks winProject = new AddTasks(valueTask,true);
				winProject.frmCriarTarefa.setVisible(true);
				tableTaskUpdate(task_table, tblControl);
				
				
			}	
		});
		
	
		
		//////////////////////////
		/////////////////////////
		////////////////////////
		
					////////////////////
		//////////////TAG TABLE////////////////
				////////////////////
		tag_table = new JTable();
		tag_table.setBounds(230, 30, 368, 62);
		tag_table.setSelectionBackground(SystemColor.window);
		tag_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tag_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tag_table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tag_table.setBounds(12, 128, 189, 330);
		tag_table.setDefaultEditor(Object.class,null);
		//frmTasfertodo.getContentPane().add(project_table);
		

		//cria o scrool da tabela!
		JScrollPane ScrollTagTable = new JScrollPane();
		ScrollTagTable.setBounds(223, 28, 375, 52);
		ScrollTagTable.setViewportView(tag_table);
		//impede a rolagem das colunas
		tag_table.getTableHeader().setReorderingAllowed(false);
		frmTasfertodo.getContentPane().add(ScrollTagTable);
		frmTasfertodo.getContentPane().add(ScrollTaskTable);
		//chamada para atualizar a tabela
		tableTaskUpdate(task_table,tblControl);
		
		//render na coluna 3 mudando a cor de todas as linhas da coluna 3
		task_table.getColumnModel().getColumn(3).setCellRenderer(renderer);
		//c = task_table.getValueAt(task_table.getSelectedRow(), 0);
		
		//
		//////////////
		//LABEL TAG///
		/////////////
		JLabel lblTags = new JLabel("Tags");
		lblTags.setFont(new Font("FreeSans", Font.BOLD | Font.ITALIC, 15));
		lblTags.setForeground(Color.WHITE);
		lblTags.setBounds(380, 13, 44, 15);
		frmTasfertodo.getContentPane().add(lblTags);
		tableTagUpdate(project_table, tblControl);
		
		//////////////
		//DELETAR TAG///
		/////////////
		JButton btnDeletarTag = new JButton("");
		btnDeletarTag.setBackground(SystemColor.window);
		btnDeletarTag.setBounds(263, 8, 18, 19);
		frmTasfertodo.getContentPane().add(btnDeletarTag);
		btnDeletarTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
			
		});
		//////////////
		//EDITAR TAG///
		/////////////
		JButton btnEditarTag = new JButton("");
		btnEditarTag.setBackground(SystemColor.window);
		btnEditarTag.setBounds(291, 8, 18, 19);
		frmTasfertodo.getContentPane().add(btnEditarTag);
		btnEditarTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		//////////////////////////
		/////////////////////////
		////////////////////////
		
		
		//////////////
		//CRIAR TAG///
		/////////////
		JButton btnCriarTag = new JButton("");
		btnCriarTag.setIcon(new ImageIcon("/home/fear404/Área de Trabalho/iconeadd16.png"));
		btnCriarTag.setForeground(Color.WHITE);
		btnCriarTag.setBackground(Color.WHITE);
		btnCriarTag.setBounds(233, 8, 18, 19);
		frmTasfertodo.getContentPane().add(btnCriarTag);
		btnCriarTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				AddTag winProject = new AddTag();
				winProject.frmCriarTag.setVisible(false);
			}
		});
	
		//////////////////////////
		/////////////////////////
		////////////////////////
		btnEditarTaks.setBackground(SystemColor.window);
		btnEditarTaks.setIcon(new ImageIcon("/home/fear404/Área de Trabalho/Materiais Compartilhados/resources/edit.png"));
		btnEditarTaks.setBounds(281, 100, 18, 19);
		frmTasfertodo.getContentPane().add(btnEditarTaks);
		
		
		
		JLabel lblConcludo = new JLabel("concluído");
		lblConcludo.setForeground(Color.WHITE);
		lblConcludo.setBounds(501, 104, 70, 15);
		frmTasfertodo.getContentPane().add(lblConcludo);
		
	
	
		
		

		
		
		
		JLabel addTaskWalparper = new JLabel("New label");
		addTaskWalparper.setIcon(new ImageIcon("/home/fear404/Downloads/6457dc8f063f284fe17e519dc28b5437.jpg"));
		addTaskWalparper.setBounds(-27, -33, 1431, 738);
		frmTasfertodo.getContentPane().add(addTaskWalparper);
			
		
	}
	//////////////////////////
	/////////////////////////
	////////////////////////
	////////////////
	//FUNCÕES///////
	////////////////
	public void tableTaskUpdate(JTable table,TableController tbl) {
		//atualiza o table
		try {
			table.setModel(DbUtils.resultSetToTableModel(tbl.UpdateTask()));
			task_table.getColumnModel().getColumn(0).setPreferredWidth(20);
			task_table.getColumnModel().getColumn(1).setPreferredWidth(220);
			
			
		} catch (SQLException e1) {
			// TODO Bloco catch gerado automaticamente
			e1.printStackTrace();
		}finally {
			
		}
	}
	public void tableTagUpdate(JTable table,TableController tbl) {
		try {
			tag_table.setModel(DbUtils.resultSetToTableModel((ResultSet) tblControl.UpdateTag()));
			tag_table.getColumnModel().getColumn(0).setPreferredWidth(1);
			tag_table.getColumnModel().getColumn(1).setPreferredWidth(500);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			
		}
	}
	public void tableProjectUpdate(JTable table,TableController tbl) {
		try {
			project_table.setModel(DbUtils.resultSetToTableModel((ResultSet) tblControl.UpdateProject()));
			project_table.getColumnModel().getColumn(0).setPreferredWidth(1);
			project_table.getColumnModel().getColumn(1).setPreferredWidth(200);
		} catch (SQLException e1) {
			// TODO Bloco catch gerado automaticamente
			e1.printStackTrace();
		}finally {
			
		}
	}
	
	
}
