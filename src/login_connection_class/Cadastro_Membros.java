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
		setBounds(100, 100, 391, 494);
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
		
		JComboBox cbCurso = new JComboBox();
		cbCurso.setFont(new Font("Dialog", Font.PLAIN, 12));
		cbCurso.setModel(new DefaultComboBoxModel(new String[] {"Engenharia de computa\u00E7\u00E3o", "Sistemas de informa\u00E7\u00E3o"}));
		cbCurso.setBounds(110, 158, 217, 22);
		contentPane.add(cbCurso);
		
		JComboBox cbCategoria = new JComboBox();
		cbCategoria.setModel(new DefaultComboBoxModel(new String[] {"Trainee", "Membro Efetivo", "P\u00F3s-J\u00FAnior", "Convidado"}));
		cbCategoria.setFont(new Font("Dialog", Font.PLAIN, 12));
		cbCategoria.setBounds(162, 200, 165, 22);
		contentPane.add(cbCategoria);
		
		JComboBox cbDep = new JComboBox();
		cbDep.setModel(new DefaultComboBoxModel(new String[] {"Gest\u00E3o de Pessoas", "Marketing", "Presid\u00EAncia", "Projetos", "Qualidade", "Vendas"}));
		cbDep.setFont(new Font("Dialog", Font.PLAIN, 12));
		cbDep.setBounds(162, 238, 165, 22);
		contentPane.add(cbDep);
		
		tfUser = new JTextField();
		tfUser.setColumns(10);
		tfUser.setBounds(162, 96, 165, 20);
		contentPane.add(tfUser);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(54, 319, 261, 14);
		contentPane.add(separator);
		
		JTextPane txtpnAes = new JTextPane();
		txtpnAes.setText("A\u00E7\u00F5es");
		txtpnAes.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtpnAes.setBackground(SystemColor.menu);
		txtpnAes.setBounds(54, 291, 176, 27);
		contentPane.add(txtpnAes);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection con = ConnectLogin.do_connection();
					String sql = "insert into data_pass(nome, senha, curso, categoria, departamento) values (?, ?, ?, ?, ?)";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfUser.getText());
					stmt.setString(2, new String(pfPassW.getPassword()));
					stmt.setString(3, cbCurso.getActionCommand());
					stmt.setString(4, cbCategoria.getActionCommand());
					stmt.setString(5, cbDep.getActionCommand());
					
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
		btnSalvar.setBounds(54, 338, 89, 23);
		contentPane.add(btnSalvar);
		
		pfPassW = new JPasswordField();
		pfPassW.setBounds(162, 127, 165, 20);
		contentPane.add(pfPassW);
	}
}
