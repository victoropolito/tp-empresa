package login_connection_class;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class Tela_login extends JFrame {

	private JPanel contentPane;
	private JTextField tfUser;
	private JPasswordField pfPassW;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_login frame = new Tela_login();
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
	public Tela_login() {
		setTitle("Enterprise System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setForeground(Color.DARK_GRAY);
		lblUsurio.setFont(new Font("Dialog", Font.BOLD, 16));
		lblUsurio.setBounds(21, 78, 81, 34);
		contentPane.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(Color.DARK_GRAY);
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSenha.setBounds(21, 149, 81, 34);
		contentPane.add(lblSenha);
		
		tfUser = new JTextField();
		tfUser.setFont(new Font("Dialog", Font.PLAIN, 16));
		tfUser.setBounds(112, 82, 210, 27);
		contentPane.add(tfUser);
		tfUser.setColumns(10);
		
		pfPassW = new JPasswordField();
		pfPassW.setBounds(112, 156, 210, 27);
		contentPane.add(pfPassW);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.setBackground(UIManager.getColor("Button.shadow"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = ConnectLogin.do_connection();
					
					String sql = "select *from data_Pass where user=? and password=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfUser.getText());
					
					stmt.setString(2, new String(pfPassW.getPassword()));
					
					ResultSet rs = stmt.executeQuery();
					
					
					if(rs.next()) {
						Tela_Cadastro exibir = new Tela_Cadastro();
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
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnNewButton.setBounds(112, 248, 129, 34);
		contentPane.add(btnNewButton);
	}
}
