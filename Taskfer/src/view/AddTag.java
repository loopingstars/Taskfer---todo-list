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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class AddTag {
	
	JFrame frmCriarTag;
	private JTextField addTagInputNome;
	private JLabel addTaskLabelColor;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTag window = new AddTag();
					window.frmCriarTag.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddTag() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCriarTag = new JFrame();
		frmCriarTag.setBackground(Color.GRAY);
		frmCriarTag.setResizable(false);
		frmCriarTag.setTitle("criar Tag");
		frmCriarTag.setBounds(100, 100, 620, 329);
		frmCriarTag.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCriarTag.getContentPane().setLayout(null);
		
		JLabel addTaskLabelNome = new JLabel("nome");
		addTaskLabelNome.setForeground(Color.WHITE);
		addTaskLabelNome.setBackground(Color.WHITE);
		addTaskLabelNome.setBounds(285, 84, 39, 15);
		frmCriarTag.getContentPane().add(addTaskLabelNome);
		
		addTagInputNome = new JTextField();
		addTagInputNome.setColumns(10);
		addTagInputNome.setBounds(133, 111, 343, 19);
		frmCriarTag.getContentPane().add(addTagInputNome);
		
		addTaskLabelColor = new JLabel("cor");
		addTaskLabelColor.setForeground(Color.WHITE);
		addTaskLabelColor.setBounds(294, 142, 22, 15);
		frmCriarTag.getContentPane().add(addTaskLabelColor);
		
		JButton addTagButton = new JButton("criar");
		addTagButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addTagButton.setBounds(246, 258, 117, 25);
		frmCriarTag.getContentPane().add(addTagButton);
		
		JCheckBox chckbxSgs = new JCheckBox("Azul");
		buttonGroup.add(chckbxSgs);
		chckbxSgs.setBounds(246, 165, 129, 23);
		frmCriarTag.getContentPane().add(chckbxSgs);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Vermelho");
		buttonGroup.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setBounds(246, 205, 129, 23);
		frmCriarTag.getContentPane().add(chckbxNewCheckBox);
		
		JCheckBox chckbxRosa = new JCheckBox("Rosa");
		buttonGroup.add(chckbxRosa);
		chckbxRosa.setBounds(113, 165, 129, 23);
		frmCriarTag.getContentPane().add(chckbxRosa);
		
		JCheckBox chckbxAmarelo = new JCheckBox("Amarelo");
		buttonGroup.add(chckbxAmarelo);
		chckbxAmarelo.setBounds(113, 205, 129, 23);
		frmCriarTag.getContentPane().add(chckbxAmarelo);
		
		JCheckBox chckbxVerde = new JCheckBox("Verde");
		buttonGroup.add(chckbxVerde);
		chckbxVerde.setBounds(379, 165, 129, 23);
		frmCriarTag.getContentPane().add(chckbxVerde);
		
		JCheckBox chckbxAzul = new JCheckBox("Azul");
		buttonGroup.add(chckbxAzul);
		chckbxAzul.setBounds(379, 205, 129, 23);
		frmCriarTag.getContentPane().add(chckbxAzul);
		
		JLabel addTaskWalparper = new JLabel("New label");
		addTaskWalparper.setIcon(new ImageIcon("/home/fear404/Downloads/6457dc8f063f284fe17e519dc28b5437.jpg"));
		addTaskWalparper.setBounds(0, 0, 610, 471);
		frmCriarTag.getContentPane().add(addTaskWalparper);
	}

}
