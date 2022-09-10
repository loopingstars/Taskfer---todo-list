package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.Resultset;
//para mostrar na tabela
import net.proteanit.sql.DbUtils;
import net.proteanit.sql.DbUtils;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
public class AddProject {
	
	JFrame frmCriarProjeto;
	private JTextField addTaskInputNome;
	private JLabel addTaskLabelDescri;
	private JLabel addTaskLabelColor;
	private JTextField addTaskFieldDateLimit;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProject window = new AddProject();
					window.frmCriarProjeto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddProject() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCriarProjeto = new JFrame();
		frmCriarProjeto.setBackground(Color.GRAY);
		frmCriarProjeto.setResizable(false);
		frmCriarProjeto.setTitle("criar tarefa");
		frmCriarProjeto.setBounds(100, 100, 620, 446);
		frmCriarProjeto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCriarProjeto.getContentPane().setLayout(null);
		
		JLabel addTaskLabelNome = new JLabel("nome");
		addTaskLabelNome.setForeground(Color.WHITE);
		addTaskLabelNome.setBackground(Color.WHITE);
		addTaskLabelNome.setBounds(285, 12, 39, 15);
		frmCriarProjeto.getContentPane().add(addTaskLabelNome);
		
		addTaskInputNome = new JTextField();
		addTaskInputNome.setColumns(10);
		addTaskInputNome.setBounds(133, 34, 343, 19);
		frmCriarProjeto.getContentPane().add(addTaskInputNome);
		
		JTextArea addTaskInputDescri = new JTextArea();
		addTaskInputDescri.setBounds(47, 105, 551, 142);
		frmCriarProjeto.getContentPane().add(addTaskInputDescri);
		addTaskLabelDescri = new JLabel("descrição");
		addTaskLabelDescri.setForeground(Color.WHITE);
		addTaskLabelDescri.setBounds(270, 78, 70, 15);
		frmCriarProjeto.getContentPane().add(addTaskLabelDescri);
		
		addTaskLabelColor = new JLabel("cor");
		addTaskLabelColor.setForeground(Color.WHITE);
		addTaskLabelColor.setBounds(133, 259, 22, 15);
		frmCriarProjeto.getContentPane().add(addTaskLabelColor);
		
		JButton addTaskButton = new JButton("criar");
		addTaskButton.setBounds(246, 357, 117, 25);
		frmCriarProjeto.getContentPane().add(addTaskButton);
		
		JLabel addTaskLabelDateLimit = new JLabel("data limite");
		addTaskLabelDateLimit.setForeground(Color.WHITE);
		addTaskLabelDateLimit.setBounds(383, 259, 93, 15);
		frmCriarProjeto.getContentPane().add(addTaskLabelDateLimit);
		
		addTaskFieldDateLimit = new JTextField();
		addTaskFieldDateLimit.setBounds(350, 289, 126, 19);
		frmCriarProjeto.getContentPane().add(addTaskFieldDateLimit);
		addTaskFieldDateLimit.setColumns(10);
		
		JCheckBox chckbxAzul = new JCheckBox("Azul");
		buttonGroup.add(chckbxAzul);
		chckbxAzul.setBounds(217, 282, 62, 23);
		frmCriarProjeto.getContentPane().add(chckbxAzul);
		
		JCheckBox chckbxVermelho = new JCheckBox("Vermelho");
		buttonGroup.add(chckbxVermelho);
		chckbxVermelho.setBounds(121, 282, 92, 23);
		frmCriarProjeto.getContentPane().add(chckbxVermelho);
		
		JCheckBox chckbxVerde = new JCheckBox("Verde");
		buttonGroup.add(chckbxVerde);
		chckbxVerde.setBounds(47, 282, 70, 23);
		frmCriarProjeto.getContentPane().add(chckbxVerde);
		
		JLabel addTaskWalparper = new JLabel("New label");
		addTaskWalparper.setIcon(new ImageIcon("/home/fear404/Downloads/6457dc8f063f284fe17e519dc28b5437.jpg"));
		addTaskWalparper.setBounds(0, 0, 610, 471);
		frmCriarProjeto.getContentPane().add(addTaskWalparper);
	}

}
