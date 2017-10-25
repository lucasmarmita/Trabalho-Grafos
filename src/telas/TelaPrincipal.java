package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import expressão.Expressao;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField campoExpressao;
	private JTextField campoPolonesa;
	private JTextField campoPolonesaReversa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblConversorDeExpresses = new JLabel("Conversor de Expressões");
		lblConversorDeExpresses.setFont(new Font("Dialog", Font.BOLD, 17));
		contentPane.add(lblConversorDeExpresses, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblInformeAExpresso = new JLabel("Informe a expressão:");
		lblInformeAExpresso.setFont(new Font("Dialog", Font.BOLD, 14));
		
		campoExpressao = new JTextField();
		campoExpressao.setFont(new Font("Dialog", Font.PLAIN, 14));
		campoExpressao.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		
		JLabel lblRepresentaoDarvore = new JLabel("Representação da Árvore");
		lblRepresentaoDarvore.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JTextArea campoRepresentação = new JTextArea();
		campoRepresentação.setEditable(false);
		campoRepresentação.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JLabel lblNotaoPolonesa = new JLabel("Notação Polonesa:");
		lblNotaoPolonesa.setFont(new Font("Dialog", Font.BOLD, 14));
		
		campoPolonesa = new JTextField();
		campoPolonesa.setEditable(false);
		campoPolonesa.setFont(new Font("Dialog", Font.PLAIN, 14));
		campoPolonesa.setColumns(10);
		
		JLabel lblNotaoPolonesaReversa = new JLabel("Notação Polonesa Reversa:");
		lblNotaoPolonesaReversa.setFont(new Font("Dialog", Font.BOLD, 14));
		
		campoPolonesaReversa = new JTextField();
		campoPolonesaReversa.setEditable(false);
		campoPolonesaReversa.setFont(new Font("Dialog", Font.PLAIN, 14));
		campoPolonesaReversa.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(campoExpressao, GroupLayout.PREFERRED_SIZE, 466, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblInformeAExpresso)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(campoRepresentação, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRepresentaoDarvore))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(campoPolonesaReversa)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblNotaoPolonesaReversa)
									.addComponent(lblNotaoPolonesa)
									.addComponent(campoPolonesa, 257, 257, Short.MAX_VALUE)))))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblInformeAExpresso)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(campoExpressao, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOk))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRepresentaoDarvore)
						.addComponent(lblNotaoPolonesa))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(campoRepresentação, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(campoPolonesa, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addGap(37)
							.addComponent(lblNotaoPolonesaReversa)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(campoPolonesaReversa, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(48, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnSair = new JButton("< SAIR");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel_1.add(btnSair);
		
		JButton btnAjuda = new JButton("AJUDA");
		panel_1.add(btnAjuda);
		
		JButton btnLimparTudo = new JButton("LIMPAR TUDO");
		btnLimparTudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoExpressao.setText("");
				campoRepresentação.setText("");
				campoPolonesa.setText("");
				campoPolonesaReversa.setText("");
			}
		});
		panel_1.add(btnLimparTudo);
		
	}
}
