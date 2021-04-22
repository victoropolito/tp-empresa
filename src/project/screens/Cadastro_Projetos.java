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

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import model.entities.*;
import model.dao.*;
import model.dao.impl.*;
import application.*;

public class Cadastro_Projetos extends JFrame {

	private JPanel cPaneProject;
	private JTextField tfIdP;
	private JTextField tfNomeP;
	private JTextField tfDesc;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro_Projetos frame = new Cadastro_Projetos();
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
	public Cadastro_Projetos() {
		setResizable(true);
		setTitle("Gerenciador de Projetos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 578);
		cPaneProject = new JPanel();
		cPaneProject.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cPaneProject);
		cPaneProject.setLayout(null);
		
		JTextPane txtpnCadastroDeProjetos = new JTextPane();
		txtpnCadastroDeProjetos.setFont(new Font("Dialog", Font.BOLD, 16));
		txtpnCadastroDeProjetos.setBackground(SystemColor.menu);
		txtpnCadastroDeProjetos.setText("Cadastro de projetos");
		txtpnCadastroDeProjetos.setBounds(98, 11, 176, 27);
		cPaneProject.add(txtpnCadastroDeProjetos);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setForeground(Color.DARK_GRAY);
		lblId.setFont(new Font("Dialog", Font.BOLD, 12));
		lblId.setBounds(54, 73, 46, 14);
		cPaneProject.add(lblId);
		
		JLabel lblNewLabel = new JLabel("Nome do projeto:");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(54, 98, 99, 14);
		cPaneProject.add(lblNewLabel);
		
		JLabel lblDescricao = new JLabel("Descricao:");
		lblDescricao.setForeground(Color.DARK_GRAY);
		lblDescricao.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDescricao.setBounds(54, 123, 99, 14);
		cPaneProject.add(lblDescricao);
		
		tfIdP = new JTextField();
		tfIdP.setEditable(false);
		tfIdP.setBounds(163, 71, 162, 20);
		cPaneProject.add(tfIdP);
		tfIdP.setColumns(10);
		
		tfNomeP = new JTextField();
		tfNomeP.setColumns(10);
		tfNomeP.setBounds(163, 96, 162, 20);
		cPaneProject.add(tfNomeP);
		
		tfDesc = new JTextField();
		tfDesc.setBounds(54, 145, 271, 82);
		cPaneProject.add(tfDesc);
		tfDesc.setColumns(10);
		
		JLabel lblTipoDoProjeto = new JLabel("Tipo do projeto:");
		lblTipoDoProjeto.setForeground(Color.DARK_GRAY);
		lblTipoDoProjeto.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTipoDoProjeto.setBounds(54, 246, 99, 14);
		cPaneProject.add(lblTipoDoProjeto);
		
		JTextPane txtpnAes = new JTextPane();
		txtpnAes.setText("A\u00E7\u00F5es");
		txtpnAes.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtpnAes.setBackground(SystemColor.menu);
		txtpnAes.setBounds(54, 424, 176, 27);
		cPaneProject.add(txtpnAes);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(54, 452, 261, 14);
		cPaneProject.add(separator);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnSalvar.setBounds(53, 462, 89, 23);
		cPaneProject.add(btnSalvar);
		
		JComboBox<String> cbTipoP = new JComboBox<String>();
		cbTipoP.setModel(new DefaultComboBoxModel(new String[] {"Sistema Web", "Website", "Ecommerce"}));
		cbTipoP.setFont(new Font("Dialog", Font.PLAIN, 12));
		cbTipoP.setBounds(160, 243, 165, 22);
		cPaneProject.add(cbTipoP);
		
		JLabel lblHabilidadesNecessarias = new JLabel("Habilidades Necessarias:");
		lblHabilidadesNecessarias.setForeground(Color.DARK_GRAY);
		lblHabilidadesNecessarias.setFont(new Font("Dialog", Font.BOLD, 12));
		lblHabilidadesNecessarias.setBounds(54, 295, 144, 14);
		cPaneProject.add(lblHabilidadesNecessarias);
		
		JCheckBox cbDesign = new JCheckBox("Design");
		cbDesign.setFont(new Font("Dialog", Font.PLAIN, 11));
		cbDesign.setBounds(54, 316, 97, 23);
		cPaneProject.add(cbDesign);
		
		JCheckBox cbBD = new JCheckBox("Banco de Dados");
		cbBD.setFont(new Font("Dialog", Font.PLAIN, 11));
		cbBD.setBounds(54, 342, 105, 23);
		cPaneProject.add(cbBD);
		
		JCheckBox cbSEO = new JCheckBox("SEO");
		cbSEO.setFont(new Font("Dialog", Font.PLAIN, 11));
		cbSEO.setBounds(54, 368, 97, 23);
		cPaneProject.add(cbSEO);
		
		JCheckBox cbFront = new JCheckBox("Front-end");
		cbFront.setFont(new Font("Dialog", Font.PLAIN, 11));
		cbFront.setBounds(163, 317, 73, 23);
		cPaneProject.add(cbFront);
		
		JCheckBox cbBack = new JCheckBox("Back-end");
		cbBack.setFont(new Font("Dialog", Font.PLAIN, 11));
		cbBack.setBounds(163, 343, 97, 23);
		cPaneProject.add(cbBack);
		
		JCheckBox cbPO = new JCheckBox("Product Owner");
		cbPO.setFont(new Font("Dialog", Font.PLAIN, 11));
		cbPO.setBounds(163, 369, 99, 23);
		cPaneProject.add(cbPO);
		
		JCheckBox cbSM = new JCheckBox("Scrum Master");
		cbSM.setFont(new Font("Dialog", Font.PLAIN, 11));
		cbSM.setBounds(254, 317, 97, 23);
		cPaneProject.add(cbSM);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAtualizar.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnAtualizar.setBounds(155, 462, 81, 23);
		cPaneProject.add(btnAtualizar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExcluir.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnExcluir.setBounds(251, 462, 74, 23);
		cPaneProject.add(btnExcluir);
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection con = ConnectLogin.do_connection();
					String sql = "insert into project(name, description, projectType, habDesign, habBD, habSEO, habFront, habBack, habPO, habSM) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfNomeP.getText());
					stmt.setString(2, tfDesc.getText());
					
					//pega item selecionado na combobox (Tipo de projeto)
					
					stmt.setString(3, (String) cbTipoP.getSelectedItem());
					
					//verifica se checkbox foram selecionadas
					
					stmt.setBoolean(4, cbDesign.isSelected());
			        stmt.setBoolean(5, cbBD.isSelected());
			        stmt.setBoolean(6, cbSEO.isSelected());
			        stmt.setBoolean(7, cbFront.isSelected());
			        stmt.setBoolean(8, cbBack.isSelected());
			        stmt.setBoolean(9, cbPO.isSelected());
			        stmt.setBoolean(10, cbSM.isSelected());
					stmt.execute();
					
					stmt.close(); //finaliza envio das informações
					con.close(); //finaliza conexão com o bd
					
					JOptionPane.showMessageDialog(null, "Projeto cadastrado com sucesso!");
					
					tfNomeP.setText("");
					tfDesc.setText("");
					
					
				} catch (SQLException e2) {
					
					e2.printStackTrace();
					
				}
				
			}
			
			
		});

	}
}
