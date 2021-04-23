package project.screens;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import db.ConnectLogin;


public class LoginScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel cPaneLogin;
	private JTextField tfUser;
	private JPasswordField pfPassW;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
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
	public LoginScreen() {
		setResizable(false);
		setTitle("Enterprise System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 356);
		cPaneLogin = new JPanel();
		cPaneLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cPaneLogin);
		cPaneLogin.setLayout(null);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setForeground(Color.DARK_GRAY);
		lblUsurio.setFont(new Font("Dialog", Font.BOLD, 16));
		lblUsurio.setBounds(21, 78, 81, 34);
		cPaneLogin.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(Color.DARK_GRAY);
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSenha.setBounds(21, 149, 81, 34);
		cPaneLogin.add(lblSenha);
		
		tfUser = new JTextField();
		tfUser.setFont(new Font("Dialog", Font.PLAIN, 16));
		tfUser.setBounds(112, 82, 210, 27);
		cPaneLogin.add(tfUser);
		tfUser.setColumns(10);
		
		pfPassW = new JPasswordField();
		pfPassW.setBounds(112, 156, 210, 27);
		cPaneLogin.add(pfPassW);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBackground(UIManager.getColor("Button.shadow"));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = ConnectLogin.do_connection();
					
					String sql = "SELECT * FROM member WHERE id = ? and password = ?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfUser.getText());
					
					stmt.setString(2, new String(pfPassW.getPassword()));
					
					ResultSet rs = stmt.executeQuery();
					
					
					if(rs.next()) {
						Cadastro_Membros exibir = new Cadastro_Membros();
						exibir.setVisible(true);
						
						setVisible(false);
						
					} else {
						JOptionPane.showMessageDialog(null, "Nome de usuário e/ou senha incorreto(s)");
						
					}
					
					stmt.close();
					con.close();
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnEntrar.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnEntrar.setBounds(112, 248, 129, 34);
		cPaneLogin.add(btnEntrar);
	}
}