package vistas.box;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Bienvenida extends JFrame {

	private JPanel container;
	private JTextField textFieldMensaje;
	private JTextField txtbienenido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bienvenida frame = new Bienvenida();
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
	public Bienvenida() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 436);
		container = new JPanel();
		container.setBackground(new Color(0, 191, 255));
		container.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(container);
		container.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 191, 255));
		panel.setBorder(null);
		panel.setBackground(new Color(0, 191, 255));
		container.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		txtbienenido = new JTextField();
		txtbienenido.setEditable(false);
		txtbienenido.setHorizontalAlignment(SwingConstants.CENTER);
		txtbienenido.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 60));
		txtbienenido.setText("\u00A1BIENVENIDO!");
		txtbienenido.setBackground(new Color(0, 191, 255));
		panel.add(txtbienenido);
		txtbienenido.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(0, 0, 0));
		panel_1.setBackground(new Color(0, 191, 255));
		container.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(0, 191, 255));
		container.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 5, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 191, 255));
		panel_6.add(panel_2);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(0, 191, 255));
		panel_6.add(panel_2_1);
		
		textFieldMensaje = new JTextField();
		panel_2_1.add(textFieldMensaje);
		textFieldMensaje.setForeground(new Color(0, 0, 0));
		textFieldMensaje.setEditable(false);
		textFieldMensaje.setBackground(new Color(0, 191, 255));
		textFieldMensaje.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 30));
		textFieldMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMensaje.setText("Pulse el bot\u00F3n para registrarse");
		textFieldMensaje.setColumns(1);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setBackground(new Color(0, 191, 255));
		panel_6.add(panel_2_2);
		
		JPanel panel_2_11 = new JPanel();
		panel_2_11.setBackground(new Color(0, 191, 255));
		panel_6.add(panel_2_11);
		
		JPanel panel_2_3 = new JPanel();
		panel_2_3.setBackground(new Color(0, 191, 255));
		panel_6.add(panel_2_3);
		
		JPanel panel_2_7 = new JPanel();
		panel_2_7.setBackground(new Color(0, 191, 255));
		panel_6.add(panel_2_7);
		
		JPanel panel_2_4 = new JPanel();
		panel_2_4.setBackground(new Color(0, 191, 255));
		panel_6.add(panel_2_4);
		
		JPanel panel_2_8 = new JPanel();
		panel_2_8.setBackground(new Color(0, 191, 255));
		panel_6.add(panel_2_8);
		panel_2_8.setLayout(new BorderLayout(0, 0));
		
		JButton btnRegistrarse = new JButton("Registrarse");
		panel_2_8.add(btnRegistrarse);
		
		JPanel panel_2_5 = new JPanel();
		panel_2_5.setBackground(new Color(0, 191, 255));
		panel_6.add(panel_2_5);
		
		JPanel panel_2_6 = new JPanel();
		panel_2_6.setBackground(new Color(0, 191, 255));
		panel_6.add(panel_2_6);
		
		JPanel panel_2_9 = new JPanel();
		panel_2_9.setBackground(new Color(0, 191, 255));
		panel_6.add(panel_2_9);
		
		JPanel panel_2_10 = new JPanel();
		panel_2_10.setBackground(new Color(0, 191, 255));
		panel_6.add(panel_2_10);
		
		JPanel panel_2_12 = new JPanel();
		panel_2_12.setBackground(new Color(0, 191, 255));
		panel_6.add(panel_2_12);
		
		JPanel panel_2_13 = new JPanel();
		panel_2_13.setBackground(new Color(0, 191, 255));
		panel_6.add(panel_2_13);
		
		JPanel panel_2_14 = new JPanel();
		panel_2_14.setBackground(new Color(0, 191, 255));
		panel_6.add(panel_2_14);
		
		JPanel panel_2_15 = new JPanel();
		panel_2_15.setBackground(new Color(0, 191, 255));
		panel_6.add(panel_2_15);
		
		JPanel panel_2_16 = new JPanel();
		panel_2_16.setBackground(new Color(0, 191, 255));
		panel_6.add(panel_2_16);
		
		JPanel panel_2_17 = new JPanel();
		panel_2_17.setBackground(new Color(0, 191, 255));
		panel_6.add(panel_2_17);
	}

}
