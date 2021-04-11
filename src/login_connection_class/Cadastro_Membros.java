package login_connection_class;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.AbstractListModel;
import java.awt.List;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Cadastro_Membros extends JFrame {

	private JPanel contentPane;
	private JTextField tfIdM;
	private JTextField tfUser;
	private JPasswordField pfPassW;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro_Membros frame = new Cadastro_Membros();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cadastro_Membros() {
		setResizable(false);
		setTitle("Gerenciador de membros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setForeground(Color.DARK_GRAY);
		lblId.setFont(new Font("Dialog", Font.BOLD, 12));
		lblId.setBounds(54, 73, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblNewLabel = new JLabel("Nome de usu\u00E1rio:");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(54, 98, 99, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1.setBounds(54, 129, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblCurso = new JLabel("Curso:");
		lblCurso.setForeground(Color.DARK_GRAY);
		lblCurso.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCurso.setBounds(54, 162, 46, 14);
		contentPane.add(lblCurso);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setForeground(Color.DARK_GRAY);
		lblCategoria.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCategoria.setBounds(54, 204, 64, 14);
		contentPane.add(lblCategoria);
		
		JLabel lblDepartamento = new JLabel("Departamento:");
		lblDepartamento.setForeground(Color.DARK_GRAY);
		lblDepartamento.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDepartamento.setBounds(54, 242, 92, 14);
		contentPane.add(lblDepartamento);
		
		tfIdM = new JTextField();
		tfIdM.setEditable(false);
		tfIdM.setBounds(162, 71, 165, 20);
		contentPane.add(tfIdM);
		tfIdM.setColumns(10);
		
		JTextPane txtpnCadastroDeMembros = new JTextPane();
		txtpnCadastroDeMembros.setFont(new Font("Dialog", Font.BOLD, 16));
		txtpnCadastroDeMembros.setBackground(SystemColor.menu);
		txtpnCadastroDeMembros.setText("Cadastro de membros");
		txtpnCadastroDeMembros.setBounds(98, 11, 176, 20);
		contentPane.add(txtpnCadastroDeMembros);
		
		JComboBox<String> cbCurso = new JComboBox<String>();
		cbCurso.setFont(new Font("Dialog", Font.PLAIN, 12));
		cbCurso.setModel(new DefaultComboBoxModel<String>(new String[] {"Engenharia de computa\u00E7\u00E3o", "Sistemas de informa\u00E7\u00E3o"}));
		cbCurso.setBounds(110, 158, 217, 22);
		contentPane.add(cbCurso);
		
		JComboBox<String> cbCategoria = new JComboBox<String>();
		cbCategoria.setModel(new DefaultComboBoxModel<String>(new String[] {"Trainee", "Membro Efetivo", "P\u00F3s-J\u00FAnior", "Convidado"}));
		cbCategoria.setFont(new Font("Dialog", Font.PLAIN, 12));
		cbCategoria.setBounds(162, 200, 165, 22);
		contentPane.add(cbCategoria);
		
		JComboBox<String> cbDep = new JComboBox<String>();
		cbDep.setModel(new DefaultComboBoxModel<String>(new String[] {"Gest\u00E3o de Pessoas", "Marketing", "Presid\u00EAncia", "Projetos", "Qualidade", "Vendas"}));
		cbDep.setFont(new Font("Dialog", Font.PLAIN, 12));
		cbDep.setBounds(162, 238, 165, 22);
		contentPane.add(cbDep);
		
		tfUser = new JTextField();
		tfUser.setColumns(10);
		tfUser.setBounds(162, 96, 165, 20);
		contentPane.add(tfUser);
		
		
		//checkbox habilidades
		
				JLabel lblHabilidades = new JLabel("Habilidades:");
				lblHabilidades.setForeground(Color.DARK_GRAY);
				lblHabilidades.setFont(new Font("Dialog", Font.BOLD, 12));
				lblHabilidades.setBounds(54, 277, 92, 14);
				contentPane.add(lblHabilidades);
				
				JCheckBox cbDesign = new JCheckBox("Design");
				cbDesign.setFont(new Font("Dialog", Font.PLAIN, 11));
				cbDesign.setBounds(54, 298, 97, 23);
				contentPane.add(cbDesign);
				
				JCheckBox cbBD = new JCheckBox("Banco de Dados");
				cbBD.setFont(new Font("Dialog", Font.PLAIN, 11));
				cbBD.setBounds(54, 324, 105, 23);
				contentPane.add(cbBD);
				
				JCheckBox cbSEO = new JCheckBox("SEO");
				cbSEO.setFont(new Font("Dialog", Font.PLAIN, 11));
				cbSEO.setBounds(54, 350, 97, 23);
				contentPane.add(cbSEO);
				
				JCheckBox cbFront = new JCheckBox("Front-end");
				cbFront.setFont(new Font("Dialog", Font.PLAIN, 11));
				cbFront.setBounds(162, 299, 97, 23);
				contentPane.add(cbFront);
				
				JCheckBox cbBack = new JCheckBox("Back-end");
				cbBack.setFont(new Font("Dialog", Font.PLAIN, 11));
				cbBack.setBounds(162, 325, 97, 23);
				contentPane.add(cbBack);
				
				JCheckBox cbPO = new JCheckBox("Product Owner");
				cbPO.setFont(new Font("Dialog", Font.PLAIN, 11));
				cbPO.setBounds(162, 350, 99, 23);
				contentPane.add(cbPO);
				
				JCheckBox cbSM = new JCheckBox("Scrum Master");
				cbSM.setFont(new Font("Dialog", Font.PLAIN, 11));
				cbSM.setBounds(261, 299, 97, 23);
				contentPane.add(cbSM);
				
				
		
		JSeparator separator = new JSeparator();
		separator.setBounds(54, 418, 261, 14);
		contentPane.add(separator);
		
		JTextPane txtpnAes = new JTextPane();
		txtpnAes.setText("A\u00E7\u00F5es");
		txtpnAes.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtpnAes.setBackground(SystemColor.menu);
		txtpnAes.setBounds(54, 393, 176, 27);
		contentPane.add(txtpnAes);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection con = ConnectLogin.do_connection();
					String sql = "insert into data_pass(nome, senha, curso, categoria, departamento, habdesign, habbd, habseo, habfront, habback, habpo, habsm) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfUser.getText());
					stmt.setString(2, new String(pfPassW.getPassword()));
					
					//pega item selecionado nas combobox
					
					stmt.setString(3, (String) cbCurso.getSelectedItem());
					stmt.setString(4, (String) cbCategoria.getSelectedItem());
					stmt.setString(5, (String) cbDep.getSelectedItem());
					
					//verifica se checkbox foram selecionadas
					
					stmt.setBoolean(6, cbDesign.isSelected());
			        stmt.setBoolean(7, cbBD.isSelected());
			        stmt.setBoolean(8, cbSEO.isSelected());
			        stmt.setBoolean(9, cbFront.isSelected());
			        stmt.setBoolean(10, cbBack.isSelected());
			        stmt.setBoolean(11, cbPO.isSelected());
			        stmt.setBoolean(11, cbSM.isSelected());
					
					stmt.execute();
					
					stmt.close(); //finaliza envio das informações
					con.close(); //finaliza conexão com o bd
					
					JOptionPane.showMessageDialog(null, "Membro cadastrado com sucesso!");
					
					tfUser.setText("");
					pfPassW.setText("");
					
					
				} catch (SQLException e2) {
					
					e2.printStackTrace();
					
				}
				
			}
			
			
		});
		btnSalvar.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnSalvar.setBounds(54, 433, 89, 23);
		contentPane.add(btnSalvar);
		
		pfPassW = new JPasswordField();
		pfPassW.setBounds(162, 127, 165, 20);
		contentPane.add(pfPassW);
	}
}
