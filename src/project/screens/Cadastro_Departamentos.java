package project.screens;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import db.ConnectLogin;

public class Cadastro_Departamentos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel cPaneDepartamentos;
	private JTextField tfidDep;
	private JTextField tfnomeDep;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro_Departamentos frame = new Cadastro_Departamentos();
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
	public Cadastro_Departamentos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 300);
		cPaneDepartamentos = new JPanel();
		cPaneDepartamentos.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cPaneDepartamentos);
		cPaneDepartamentos.setLayout(null);

		JTextPane txtpnCadastroDeDepartamentos = new JTextPane();
		txtpnCadastroDeDepartamentos.setText("Cadastro de departamentos");
		txtpnCadastroDeDepartamentos.setFont(new Font("Dialog", Font.BOLD, 16));
		txtpnCadastroDeDepartamentos.setBackground(SystemColor.menu);
		txtpnCadastroDeDepartamentos.setBounds(64, 11, 219, 27);
		cPaneDepartamentos.add(txtpnCadastroDeDepartamentos);

		JLabel lblId = new JLabel("Id:");
		lblId.setForeground(Color.DARK_GRAY);
		lblId.setFont(new Font("Dialog", Font.BOLD, 14));
		lblId.setBounds(46, 69, 46, 14);
		cPaneDepartamentos.add(lblId);

		JLabel lblNomeDoDepartamento = new JLabel("Nome:");
		lblNomeDoDepartamento.setForeground(Color.DARK_GRAY);
		lblNomeDoDepartamento.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNomeDoDepartamento.setBounds(46, 94, 99, 14);
		cPaneDepartamentos.add(lblNomeDoDepartamento);

		tfidDep = new JTextField();
		tfidDep.setEditable(false);
		tfidDep.setColumns(10);
		tfidDep.setBounds(121, 68, 162, 20);
		cPaneDepartamentos.add(tfidDep);

		tfnomeDep = new JTextField();
		tfnomeDep.setColumns(10);
		tfnomeDep.setBounds(121, 93, 162, 20);
		cPaneDepartamentos.add(tfnomeDep);

		JTextPane txtpnAes = new JTextPane();
		txtpnAes.setText("A\u00E7\u00F5es");
		txtpnAes.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtpnAes.setBackground(SystemColor.menu);
		txtpnAes.setBounds(46, 160, 176, 27);
		cPaneDepartamentos.add(txtpnAes);

		JSeparator separator = new JSeparator();
		separator.setBounds(46, 188, 261, 14);
		cPaneDepartamentos.add(separator);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Connection conn = ConnectLogin.do_connection();
					String sql = "insert into department(name) value (?)";

					PreparedStatement stmt = conn.prepareStatement(sql);

					stmt.setString(1, tfnomeDep.getText());

					stmt.execute();

					stmt.close(); // finaliza envio das informações
					conn.close(); // finaliza conexão com o bd

					JOptionPane.showMessageDialog(null, "Departamento cadastrado com sucesso!");

					tfnomeDep.setText("");

				} catch (SQLException e2) {

					e2.printStackTrace();

				}

			}

		});
		btnSalvar.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnSalvar.setBounds(46, 199, 89, 23);
		cPaneDepartamentos.add(btnSalvar);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnAtualizar.setBounds(145, 199, 81, 23);
		cPaneDepartamentos.add(btnAtualizar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnExcluir.setBounds(233, 199, 74, 23);
		cPaneDepartamentos.add(btnExcluir);
	}
}